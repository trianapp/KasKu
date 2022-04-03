package app.trian.kasku.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.trian.kasku.data.local.entity.Budget
import kotlinx.coroutines.flow.Flow

/**
 *
 * author Trian Damai
 * created_at 17/03/22 - 00.36
 * site https://trian.app
 */
@Dao
interface BudgetDao {
    @Query("SELECT * FROM Budget ORDER BY createdAt ASC")
    fun getListBudget():Flow<List<Budget>>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    fun saveBudget(
        budget: Budget
    )
}