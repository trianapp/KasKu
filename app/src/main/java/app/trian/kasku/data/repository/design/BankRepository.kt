package app.trian.kasku.data.repository.design

import app.trian.kasku.data.local.entity.BankAccount
import app.trian.kasku.domain.DataState
import app.trian.kasku.ui.theme.GradientColor
import kotlinx.coroutines.flow.Flow

interface BankRepository {
    suspend fun saveBank(
        bankName: String,
        amount: Int,
        color: GradientColor
    ):Flow<DataState<BankAccount>>
    suspend fun getCurrentBank():Flow<BankAccount?>

}