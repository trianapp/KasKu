package app.trian.kasku.data.repository.design

import app.trian.kasku.data.local.entity.Transaction
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.OffsetDateTime

/**
 *
 * author Trian Damai
 * created_at 17/03/22 - 00.43
 * site https://trian.app
 */
interface TransactionRepository {
    suspend fun getListTransaction(
        from: OffsetDateTime,
        to: OffsetDateTime
    ):Flow<List<Transaction>>
}