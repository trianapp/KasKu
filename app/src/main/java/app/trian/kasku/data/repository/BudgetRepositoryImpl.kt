package app.trian.kasku.data.repository

import app.trian.kasku.common.DispatcherProvider
import app.trian.kasku.data.local.dao.BudgetDao
import app.trian.kasku.data.local.entity.Budget
import app.trian.kasku.data.local.entity.Category
import app.trian.kasku.data.repository.design.BudgetRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.time.OffsetDateTime

/**
 *
 * author Trian Damai
 * created_at 17/03/22 - 00.41
 * site https://trian.app
 */
class BudgetRepositoryImpl(
    private val dispatcher: DispatcherProvider,
    private val firestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth,
    private val budgetDao: BudgetDao
):BudgetRepository {
    companion object{
        const val BUDGET_COLLECTION = "BUDGET"
    }

    override suspend fun getListBudget(
    ): Flow<List<Budget>> = budgetDao.getListBudget().flowOn(dispatcher.io())

    override suspend fun saveBudget(
        category: String,
        name: String,
        description: String,
        amount: Int
    ): Flow<String> = flow {

        //get random id
        val budgetId = firestore
            .collection(BUDGET_COLLECTION)
            .document()
            .id
        //create budget
        val budget = Budget(
            uid = budgetId,
            category = category,
            name = name,
            description= description,
            amount = amount,
            createdAt = OffsetDateTime.now(),
            updatedAt = OffsetDateTime.now()
        )
        //save
        budgetDao.saveBudget(budget)
        emit(budgetId)
    }.flowOn(dispatcher.io())
}