package app.trian.kasku.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.OffsetDateTime

@Entity
data class BankAccount(
    @PrimaryKey
    var uid:String,
    var bankName:String,
    var startAmount:Double,
    var amount:Double,
    var created_at:OffsetDateTime,
    var updated_at:OffsetDateTime
)
