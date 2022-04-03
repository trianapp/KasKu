package app.trian.kasku.data.local.entity

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.OffsetDateTime

@Keep
@Entity
data class Transaction(
    @PrimaryKey
    var uid:String,
    var name:String,
    var description:String,
    var category:String,
    var amount:Int,
    var createdAt:OffsetDateTime,
    var updatedAt:OffsetDateTime
)
