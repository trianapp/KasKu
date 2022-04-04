package app.trian.kasku.data.repository.design

import app.trian.kasku.data.local.entity.Category
import com.google.android.datatransport.cct.StringMerger
import kotlinx.coroutines.flow.Flow

/**
 *
 * author Trian Damai
 * created_at 17/03/22 - 00.42
 * site https://trian.app
 */
interface CategoryRepository {
    suspend fun getListCategory(
    ):Flow<List<Category>>

    suspend fun saveCategory(
        categoryName:String,
        icon:Int
    ):Flow<String>
}