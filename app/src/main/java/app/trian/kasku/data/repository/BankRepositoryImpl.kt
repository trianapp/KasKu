package app.trian.kasku.data.repository

import app.trian.kasku.common.DispatcherProvider
import app.trian.kasku.data.local.dao.BankDao
import app.trian.kasku.data.local.entity.BankAccount
import app.trian.kasku.data.repository.design.BankRepository
import app.trian.kasku.domain.DataState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.time.OffsetDateTime

/**
 *
 * author Trian Damai
 * created_at 14/03/22 - 22.22
 * site https://trian.app
 */
class BankRepositoryImpl(
    private val dispatcher: DispatcherProvider,
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val bankDao:BankDao
) : BankRepository {
    override suspend fun saveBank(bankName: String, amount: Double): Flow<DataState<BankAccount>> =flow{

        val user = firebaseAuth.currentUser
        if(user != null) {
            val bank = BankAccount(
                uid = user.uid,
                bankName = bankName,
                startAmount = amount,
                amount = amount,
                created_at = OffsetDateTime.now(),
                updated_at = OffsetDateTime.now()
            )
            bankDao.insert(
                bank
            )
            emit(DataState.OnData(bank))
        }else{
            emit(DataState.OnFailure("User not logged in!"))
        }
    }.flowOn(dispatcher.io())

    override suspend fun getCurrentBank(): Flow<BankAccount?> = flow {
        val user = firebaseAuth.currentUser
        if(user != null){
            val bankAccount = bankDao.getBankAccountById(user.uid)
            emit(bankAccount)
        }else{
            emit(null)
        }
    }.flowOn(dispatcher.io())


}