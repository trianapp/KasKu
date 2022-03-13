package app.trian.kasku.ui.pages.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.trian.kasku.data.repository.design.UserRepository
import app.trian.kasku.domain.DataState
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**
 *
 * author Trian Damai
 * created_at 13/03/22 - 20.01
 * site https://trian.app
 */
@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {
    @Inject lateinit var userRepository: UserRepository


    fun isUserLogin(
        callback:suspend (Boolean)->Unit
    )=viewModelScope.launch {
        userRepository
            .checkIsUserLogin()
            .onEach {
                callback(it)
            }
            .collect()
    }

    fun loginWithEmailAndPassword(
        email:String,
        password:String,
        callback:  (Boolean, Boolean,String) -> Unit
    )=viewModelScope.launch {
        userRepository
            .loginBasic(
                email, password
            )
            .onEach {
                when(it){
                    is DataState.OnData -> {
                        callback(true,it.data.isNewUser,"Sign In Success!")
                    }
                    is DataState.OnFailure -> {
                        callback(false,false,it.message)
                    }
                    DataState.OnLoading -> {}
                }
            }
            .collect()
    }

    fun loginWithGoogle(
        credential:Task<GoogleSignInAccount>?,
        callback: (Boolean,Boolean, String) -> Unit
    )=viewModelScope.launch {
        try {
            if(credential != null){
                val account = credential.await()
                userRepository
                    .loginGoogle(
                        account.idToken!!
                    )
                    .onEach {
                        when(it){
                            is DataState.OnData -> {
                                callback(true,it.data.isNewUser,"Sign in success")
                            }
                            is DataState.OnFailure -> {
                                callback(false,false,it.message)
                            }
                            DataState.OnLoading -> {}
                        }
                    }
                    .collect()
            }else{
                callback(false,false,"User don't have credential")
            }
        }catch (e:Exception){

        }
    }

    fun registerWithEmailAndPassword(
        name:String,
        email: String,
        password: String,
        callback: (Boolean, String) -> Unit
    )=viewModelScope.launch {
        userRepository
            .registerBasic(
                name, email, password
            )
            .onEach {
                when(it){
                    is DataState.OnData -> {
                        callback(true,"Register success, please check your inbox Email for verification!")
                    }
                    is DataState.OnFailure -> {
                        callback(false,it.message)
                    }
                    DataState.OnLoading -> {}
                }
            }
            .collect()
    }

    fun changePassword(
        newPassword:String,
        callback: (Boolean, String) -> Unit
    )=viewModelScope.launch {
        userRepository
            .changePassword(newPassword)
            .onEach {
                when(it){
                    is DataState.OnData -> {
                        callback(true,"Password has changed!")
                    }
                    is DataState.OnFailure -> {
                        callback(false,it.message)
                    }
                    DataState.OnLoading->{}

                }
            }
            .collect()
    }

    fun resetPasswordWithEmail(
        email: String,
        callback: (Boolean, String) -> Unit
    )=viewModelScope.launch {
        userRepository
            .resetPasswordWithEmail(email)
            .onEach {
                when(it){
                    is DataState.OnData -> {
                        callback(true,"Your password has sen to $email, please check your email!")
                    }
                    is DataState.OnFailure -> {
                        callback(false,it.message)
                    }
                    DataState.OnLoading -> {}
                }
            }
            .collect()
    }

    fun signOut(
        callback:()->Unit
    )=viewModelScope.launch {
        userRepository.signOut(callback)
    }
}