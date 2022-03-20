package app.trian.kasku.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import app.trian.kasku.common.fromOffsetDateTime
import app.trian.kasku.common.toOffsetDateTime
import app.trian.kasku.data.local.entity.BankAccount
import java.time.OffsetDateTime


data class BankAccountModel(
    var uid:String="",
    var bankName:String="",
    var startAmount:Double=0.0,
    var colorStart:String="",
    var colorEnd:String="",
    var amount:Double=0.0,
    var created_at:String="",
    var updated_at:String=""
)

fun BankAccount.toModel()=BankAccountModel(
    uid = uid,
    bankName=bankName,
    colorStart = colorStart,
    colorEnd=colorEnd,
    amount=amount,
    startAmount=startAmount,
    created_at=created_at.fromOffsetDateTime()?:"",
    updated_at=updated_at.fromOffsetDateTime()?:""
)


fun BankAccountModel.toEntity()=BankAccount(
    uid = uid,
    bankName=bankName,
    colorStart = colorStart,
    colorEnd=colorEnd,
    amount=amount,
    startAmount=startAmount,
    created_at=created_at.toOffsetDateTime() ?: OffsetDateTime.now(),
    updated_at=updated_at.toOffsetDateTime() ?: OffsetDateTime.now()
)