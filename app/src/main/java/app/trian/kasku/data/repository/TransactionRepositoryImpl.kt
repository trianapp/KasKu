package app.trian.kasku.data.repository

import app.trian.kasku.common.DispatcherProvider
import app.trian.kasku.data.local.dao.TransactionDao
import app.trian.kasku.data.local.entity.Transaction
import app.trian.kasku.data.repository.design.TransactionRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.OffsetDateTime

/**
 *
 * author Trian Damai
 * created_at 17/03/22 - 00.43
 * site https://trian.app
 */
class TransactionRepositoryImpl(
    private val dispatcherProvider: DispatcherProvider,
    private val transactionDao: TransactionDao
):TransactionRepository {
    override suspend fun getListTransaction(
        from:OffsetDateTime,
        to:OffsetDateTime
    ): Flow<List<Transaction>> = transactionDao.getTransactionFrom(
        from,
        to
    )
}