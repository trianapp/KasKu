package app.trian.kasku.data.repository

import androidx.compose.runtime.internal.updateLiveLiteralValue
import app.trian.kasku.common.DispatcherProvider
import app.trian.kasku.data.local.entity.User
import app.trian.kasku.data.repository.design.UserRepository
import app.trian.kasku.domain.DataState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

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

    override suspend fun getCurrentUserProfile(): Flow<User?> = flow<User?> {

    }.flowOn(dispatcher.io())

    override suspend fun loginBasic(
        email: String,
        password: String
    ): Flow<DataState<FirebaseUser>> =flow{
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
                    emit(DataState.OnData(authenticate.user!!))
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
            val authenticate = firebaseAuth
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

    override suspend fun loginGoogle(idToken: String): Flow<DataState<FirebaseUser>> =flow{
        emit(DataState.OnLoading)
        try {
            val credential = GoogleAuthProvider
                .getCredential(idToken,null)
            val authenticate = firebaseAuth
                .signInWithCredential(credential)
                .await()
            emit(DataState.OnData(authenticate.user!!))
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

    override suspend fun signOut(callback: () -> Unit) = withContext(dispatcher.io()){
        firebaseAuth.signOut()

        callback.invoke()
    }

}