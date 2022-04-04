package app.trian.kasku.ui.pages.budget

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.trian.kasku.data.local.entity.Budget
import app.trian.kasku.data.local.entity.Category
import app.trian.kasku.data.repository.design.BudgetRepository
import app.trian.kasku.data.repository.design.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import logcat.LogPriority
import logcat.logcat
import javax.inject.Inject

/**
 *
 * author Trian Damai
 * created_at 03/04/22 - 19.08
 * site https://trian.app
 */
@HiltViewModel
class BudgetViewModel @Inject constructor() : ViewModel() {
    @Inject lateinit var budgetRepository: BudgetRepository
    @Inject lateinit var categoryRepository: CategoryRepository

    private var _listBudget = MutableLiveData<List<Budget>>()
    val listBudget get() = _listBudget

    private var _listCategory = MutableLiveData<List<Category>>()
    val listCategory get() = _listCategory

    fun getListBudget(
    )=viewModelScope.launch {
        budgetRepository.getListBudget()
            .onEach {
                logcat("yoo",LogPriority.ERROR){it.toString()}
                _listBudget.postValue(it)
            }
            .collect()
    }

    fun getListCategory(
    )=viewModelScope.launch {
        categoryRepository.getListCategory()
            .onEach {
                _listCategory.postValue(it)
            }
            .collect()
    }

    fun saveBudget(
        categoryId:String,
        budgetName:String,
        description:String,
        budgetAmount:Int,
        callback:(String)->Unit
    )=viewModelScope.launch {
        budgetRepository.saveBudget(
            categoryId,
            budgetName,
            description,
            budgetAmount
        ).onEach {
            callback(it)
        }
            .collect()
    }
}