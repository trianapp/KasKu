package app.trian.kasku.ui.pages.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.trian.kasku.data.repository.design.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *
 * author Trian Damai
 * created_at 03/04/22 - 22.05
 * site https://trian.app
 */
@HiltViewModel
class CategoryViewModel @Inject constructor() : ViewModel() {
    @Inject lateinit var categoryRepository: CategoryRepository

    fun saveCategory(
        categoryName:String,
        icon:Int,
        callback:(Boolean)->Unit
    )=viewModelScope.launch {
        categoryRepository.saveCategory(
            categoryName, icon
        )
            .onEach {
                callback(true)
            }.collect()
    }
}