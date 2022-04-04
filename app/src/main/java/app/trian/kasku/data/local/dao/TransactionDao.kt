package app.trian.kasku.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import app.trian.kasku.data.local.entity.Transaction
import kotlinx.coroutines.flow.Flow
import java.time.OffsetDateTime

/**
 *
 * author Trian Damai
 * created_at 17/03/22 - 00.36
 * site https://trian.app
 */
@Dao
interface TransactionDao {

    @Query("SELECT * FROM `Transaction` WHERE datetime(createdAt) BETWEEN :from AND :to ORDER BY createdAt ASC")
    fun getTransactionFrom(from:OffsetDateTime,to:OffsetDateTime): Flow<List<Transaction>>

    @Insert
    fun insert(transaction: Transaction)


}