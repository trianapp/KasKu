package app.trian.kasku.data.repository.design

import app.trian.kasku.data.local.entity.Budget
import kotlinx.coroutines.flow.Flow

/**
 *
 * author Trian Damai
 * created_at 17/03/22 - 00.41
 * site https://trian.app
 */
interface BudgetRepository {
    suspend fun getListBudget(
    ): Flow<List<Budget>>

    suspend fun saveBudget(
        category:String,
        name:String,
        description:String,
        amount:Int,
    ):Flow<String>
}