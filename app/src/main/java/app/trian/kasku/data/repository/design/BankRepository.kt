package app.trian.kasku.data.repository.design

import app.trian.kasku.data.local.entity.BankAccount
import app.trian.kasku.domain.DataState
import kotlinx.coroutines.flow.Flow

interface BankRepository {
    suspend fun saveBank(bankName:String,amount:Double):Flow<DataState<BankAccount>>
    suspend fun getCurrentBank():Flow<BankAccount?>

}