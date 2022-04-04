package app.trian.kasku.data.local.entity

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.OffsetDateTime

@Keep
@Entity
data class Budget(
    @PrimaryKey
    var uid:String,
    var category:String,
    var name:String,
    var description:String,
    var amount:Int,
    var updatedAt:OffsetDateTime,
    var createdAt:OffsetDateTime
)
