package app.trian.kasku.data.repository.design

import app.trian.kasku.data.local.entity.User
import app.trian.kasku.domain.DataState
import app.trian.kasku.domain.models.AuthenticationResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun checkIsUserLogin():Flow<Boolean>
    suspend fun getCurrentUser():Flow<FirebaseUser?>
    suspend fun getCurrentUserProfile():Flow<User?>

    suspend fun loginBasic(email:String,password:String):Flow<DataState<AuthenticationResult>>
    suspend fun registerBasic(name:String,email:String,password: String):Flow<DataState<FirebaseUser>>

    suspend fun loginGoogle(idToken:String):Flow<DataState<AuthenticationResult>>

    suspend fun changePassword(newPassword:String):Flow<DataState<Boolean>>
    suspend fun resetPasswordWithEmail(email: String):Flow<DataState<Boolean>>

    suspend fun signOut(callback:()->Unit)
}