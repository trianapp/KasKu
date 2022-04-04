package app.trian.kasku.ui.pages.wallet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.trian.kasku.data.local.entity.BankAccount
import app.trian.kasku.data.repository.design.BankRepository
import app.trian.kasku.domain.DataState
import app.trian.kasku.ui.theme.GradientColor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *
 * author Trian Damai
 * created_at 14/03/22 - 22.16
 * site https://trian.app
 */
@HiltViewModel
class WalletViewModel @Inject constructor() : ViewModel() {
    @Inject lateinit var bankRepository: BankRepository

    private var _currentBankAccount = MutableLiveData<BankAccount?>()
    val currentBankAccount get() = _currentBankAccount

    fun getCurrentBankAccount()=viewModelScope.launch {
        bankRepository.getCurrentBank()
            .onEach {
                _currentBankAccount.postValue(it)
            }
            .collect()
    }

    fun saveBank(
        bankName:String,
        amount:Int,
        color: GradientColor,
        callback: (Boolean)->Unit
    )=viewModelScope.launch {
        bankRepository.saveBank(bankName, amount,color)
            .onEach {
                when(it){
                    is DataState.OnData -> callback(true)
                    is DataState.OnFailure -> callback(false)
                    DataState.OnLoading -> {}
                }
            }
            .collect()

    }
}