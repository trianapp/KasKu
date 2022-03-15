package app.trian.kasku.ui.pages.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.trian.kasku.data.local.entity.BankAccount
import app.trian.kasku.data.repository.design.BankRepository
import app.trian.kasku.data.repository.design.UserRepository
import app.trian.kasku.domain.models.CurrentUserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
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
    @Inject lateinit var bankRepository: BankRepository

    private var _currentBankAccount = MutableLiveData<BankAccount?>()
    val currentBankAccount get() = _currentBankAccount

    private var _currentUserProfile = MutableLiveData<CurrentUserModel>()
    val currentUserProfile get() = _currentUserProfile

    fun getCurrentBankAccount()=viewModelScope.launch {
        bankRepository.getCurrentBank()
            .onEach {
                _currentBankAccount.postValue(it)
            }
            .collect()
    }

    fun getCurrentUserProfile()=viewModelScope.launch {
        userRepository.getCurrentUserProfile()
            .onEach {
                _currentUserProfile.postValue(it)
            }
            .collect()
    }
    fun signOut(callback:()->Unit)=viewModelScope.launch{
        userRepository.signOut {
            callback()
        }
    }
}