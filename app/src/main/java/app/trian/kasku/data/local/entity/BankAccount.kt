package app.trian.kasku.data.local.entity

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.OffsetDateTime

@Entity
@Keep
data class BankAccount(
    @PrimaryKey
    var uid:String,
    var bankName:String,
    var startAmount:Double,
    var colorStart:String,
    var colorEnd:String,
    var amount:Double,
    var created_at:OffsetDateTime,
    var updated_at:OffsetDateTime
)
