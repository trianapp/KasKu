package app.trian.kasku.ui.pages.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.trian.kasku.data.repository.design.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *
 * author Trian Damai
 * created_at 14/03/22 - 22.00
 * site https://trian.app
 */
@HiltViewModel
class DashboardViewModel @Inject constructor() : ViewModel() {
    @Inject lateinit var userRepository: UserRepository

    fun signOut(callback:()->Unit)=viewModelScope.launch{
        userRepository.signOut {
            callback()
        }
    }
}