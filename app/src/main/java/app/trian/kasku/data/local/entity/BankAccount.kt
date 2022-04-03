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
    var startAmount:Int,
    var colorStart:String,
    var colorEnd:String,
    var amount:Int,
    var created_at:OffsetDateTime,
    var updated_at:OffsetDateTime
)
