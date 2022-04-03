package app.trian.kasku.data.repository

import app.trian.kasku.common.DispatcherProvider
import app.trian.kasku.data.local.dao.CategoryDao
import app.trian.kasku.data.local.entity.Category
import app.trian.kasku.data.repository.design.CategoryRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.time.OffsetDateTime

/**
 *
 * author Trian Damai
 * created_at 17/03/22 - 00.42
 * site https://trian.app
 */
class CategoryRepositoryImpl(
    private val dispatcher: DispatcherProvider,
    private val firestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth,
    private val categoryDao: CategoryDao
):CategoryRepository {
    companion object{
        const val CATEGORY_COLLECTION = "CATEGORY"
    }
    override suspend fun getListCategory(
    ): Flow<List<Category>> = categoryDao.getListCategory()
        .flowOn(dispatcher.io())

    override suspend fun saveCategory(
        categoryName: String,
        icon: Int
    ): Flow<String> =flow {
        //generate random id
        val categoryId = firestore
            .collection(CATEGORY_COLLECTION)
            .document()
            .id
        //create entity
        val category = Category(
            uid = categoryId,
            name = categoryName,
            icon = icon,
            createdAt = OffsetDateTime.now(),
            updatedAt = OffsetDateTime.now()
        )
        //save
        categoryDao.insertCategory(category)
        emit(categoryId)
    }.flowOn(dispatcher.io())
}