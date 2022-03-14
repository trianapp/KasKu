package app.trian.kasku.data.repository

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.internal.updateLiveLiteralValue
import app.trian.kasku.common.DispatcherProvider
import app.trian.kasku.common.toOffsetDateTime
import app.trian.kasku.data.local.dao.UserDao
import app.trian.kasku.data.local.entity.User
import app.trian.kasku.data.repository.design.UserRepository
import app.trian.kasku.domain.DataState
import app.trian.kasku.domain.models.AuthenticationResult
import app.trian.kasku.domain.models.CurrentUserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream

/**
 *
 * author Trian Damai
 * created_at 13/03/22 - 19.01
 * site https://trian.app
 */
class UserRepositoryImpl(
    private val dispatcher: DispatcherProvider,
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val firebaseMessaging: FirebaseMessaging,
    private val firebaseStorage: FirebaseStorage,
    private val userDao:UserDao
) :UserRepository{
    override suspend fun checkIsUserLogin(): Flow<Boolean> =flow{
        val currentUser = firebaseAuth.currentUser
        if(currentUser == null){
            emit(false)
        }else{
            emit(true)
        }
    }.flowOn(dispatcher.io())

    override suspend fun getCurrentUser(): Flow<FirebaseUser?> =flow{
        val user = firebaseAuth.currentUser
        emit(user)
    }.flowOn(dispatcher.io())

    override suspend fun getCurrentUserProfile(): Flow<CurrentUserModel?> = flow {
        try {
            val user = firebaseAuth.currentUser
            val getCurrentUserLocal = userDao.getUser(user?.uid.toString())
            emit(
                CurrentUserModel(
                    user = getCurrentUserLocal,
                    authUser = user
                )
            )
        }catch (e:Exception){
            emit(null)
        }
    }.flowOn(dispatcher.io())

    override suspend fun loginBasic(
        email: String,
        password: String
    ): Flow<DataState<AuthenticationResult>> =flow{
        emit(DataState.OnLoading)
        try {
            val authenticate = firebaseAuth
                .signInWithEmailAndPassword(
                    email, password
                )
                .await()
            val user = authenticate.user
            if(user == null){
                emit(DataState.OnFailure("Login failed!"))
                firebaseAuth.signOut()
            }else{
                if(user.isEmailVerified){
                    emit(DataState.OnData(AuthenticationResult(
                        isNewUser = authenticate.additionalUserInfo?.isNewUser ?: false,
                        firebaseUser = authenticate.user!!
                    )))
                }else{
                    emit(DataState.OnFailure("Email not verify, Check your email inbox for verification"))
                }
            }
        }catch (e:Exception){
            emit(DataState.OnFailure("authentication failed because ${e.message}"))
        }
    }.flowOn(dispatcher.io())

    override suspend fun registerBasic(
        name: String,
        email: String,
        password: String
    ): Flow<DataState<FirebaseUser>> =flow{
        emit(DataState.OnLoading)
        try {
            firebaseAuth
                .createUserWithEmailAndPassword(
                    email, password
                )
                .await()
            val user = firebaseAuth.currentUser
            val profileChangeRequest = userProfileChangeRequest {
                displayName = name
            }
            user?.updateProfile(profileChangeRequest)
            user?.sendEmailVerification()?.await()
        }catch (e:Exception){
            emit(DataState.OnFailure("Register failed ${e.message}"))
        }
    }.flowOn(dispatcher.io())

    override suspend fun loginGoogle(idToken: String): Flow<DataState<AuthenticationResult>> =flow{
        emit(DataState.OnLoading)
        try {
            val credential = GoogleAuthProvider
                .getCredential(idToken,null)
            val authenticate = firebaseAuth
                .signInWithCredential(credential)
                .await()

            emit(DataState.OnData(
                AuthenticationResult(
                    isNewUser = authenticate.additionalUserInfo?.isNewUser ?: false,
                    firebaseUser = authenticate.user!!
                )
            ))
        }catch (e:Exception){
            emit(DataState.OnFailure(e.message.toString()))
        }
    }.flowOn(dispatcher.io())

    override suspend fun changePassword(newPassword: String): Flow<DataState<Boolean>> =flow{
        emit(DataState.OnLoading)
        val user = firebaseAuth.currentUser
        if(user == null){
            emit(DataState.OnFailure("Cannot change password because user is not logged in!"))
        }else{
            try {
                user.updatePassword(newPassword)
                    .await()
                emit(DataState.OnData(true))
            }catch (e:Exception){
                emit(DataState.OnFailure(e.message.toString()))
            }
        }
    }.flowOn(dispatcher.io())

    override suspend fun resetPasswordWithEmail(email: String): Flow<DataState<Boolean>> =flow{
        emit(DataState.OnLoading)
        try {
            firebaseAuth.sendPasswordResetEmail(email)
                .await()

            emit(DataState.OnData(true))
        }catch (e:Exception) {
            emit(DataState.OnFailure(e.message.toString()))
        }
    }.flowOn(dispatcher.io())

    override suspend fun updatePictureProfile(image: Bitmap): Flow<DataState<Boolean>> = flow<DataState<Boolean>> {
        emit(DataState.OnLoading)
        try {
            val user = firebaseAuth.currentUser
            if(user != null){
                //make image become byte
                val baos = ByteArrayOutputStream()
                image.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                val data = baos.toByteArray()

                //upload to cloud and get url
                val storageRef = firebaseStorage
                    .reference
                    .child("PROFILE/${user.uid}.jpg")
                val result = storageRef.putBytes(data)
                    .continueWithTask { task ->
                        if (!task.isSuccessful) {
                            task.exception?.let {
                                throw it
                            }
                        }
                        storageRef.downloadUrl
                    }.await()


                //update current user profile
                val updateProfileChangeRequest = userProfileChangeRequest {
                    photoUri = result
                }
                firebaseAuth.currentUser?.updateProfile(updateProfileChangeRequest)?.await()
            }else{
                emit(DataState.OnFailure("Cannot update,User not logged in!"))
            }

        }catch (e:Exception){
            emit(DataState.OnFailure(e.message.toString()))
        }
    }.flowOn(dispatcher.io())

    override suspend fun updateDateOfBirth(date: String): Flow<DataState<Boolean>> =flow<DataState<Boolean>> {
        emit(DataState.OnLoading)
        try {
            val user = firebaseAuth.currentUser

            if(user != null){
                //insert to local
                userDao.insert(
                    User(
                        uid = user.uid,
                        name = user.displayName!!,
                        email=user.email!!,
                        dateOfBirth = date.toOffsetDateTime()!!
                    )
                )
                //insert to cloud
                firestore.collection("USER")
                    .document(user.uid)
                    .set(
                        hashMapOf(
                            "dateOfBirth" to date
                        ),
                        SetOptions.merge()

                    ).await()

                emit(DataState.OnData(true))
            }else{
                emit(DataState.OnFailure("Cannot update, User not logged in!"))
            }

        }catch (e:Exception){
            emit(DataState.OnFailure(e.message.toString()))
        }
    }.flowOn(dispatcher.io())

    override suspend fun signOut(callback: () -> Unit) = withContext(dispatcher.io()){
        firebaseAuth.signOut()

        callback.invoke()
    }

}