package app.trian.kasku.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.trian.kasku.data.local.entity.Category
import kotlinx.coroutines.flow.Flow

/**
 *
 * author Trian Damai
 * created_at 17/03/22 - 00.36
 * site https://trian.app
 */
@Dao
interface CategoryDao {
    @Query("SELECT * FROM Category ORDER BY name ASC")
    fun getListCategory(): Flow<List<Category>>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    fun insertCategory(
        category: Category
    )
}