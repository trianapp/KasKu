package app.trian.kasku.ui.pages.transaction

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.trian.kasku.data.local.entity.Category
import app.trian.kasku.data.local.entity.Transaction
import app.trian.kasku.data.repository.design.CategoryRepository
import app.trian.kasku.data.repository.design.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.OffsetDateTime
import javax.inject.Inject

/**
 *
 * author Trian Damai
 * created_at 03/04/22 - 17.00
 * site https://trian.app
 */
@HiltViewModel
class TransactionViewModel @Inject constructor() : ViewModel() {
    @Inject lateinit var categoryRepository: CategoryRepository
    @Inject lateinit var transactionRepository: TransactionRepository

    private var _listCategory = MutableLiveData<List<Category>>()
    val listCategory get() = _listCategory

    private var _listTransaction = MutableLiveData<List<Transaction>>()
    val listTransaction get() = _listTransaction

    fun getListCategory(
    )=viewModelScope.launch{
        categoryRepository.getListCategory()
            .onEach {
                _listCategory.postValue(it)
            }
            .collect()
    }

    fun getListTransaction(
        from:OffsetDateTime,
        to:OffsetDateTime
    )=viewModelScope.launch {
        transactionRepository.getListTransaction(
            from,
            to
        )
            .onEach {
                _listTransaction.postValue(it)
            }
            .collect()
    }


}