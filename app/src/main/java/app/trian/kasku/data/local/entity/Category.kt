package app.trian.kasku.data.local.entity

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.trian.kasku.domain.CategoryIconModel
import java.time.OffsetDateTime

@Keep
@Entity
data class Category(
    @PrimaryKey
    var uid:String,
    var name:String,
    var icon:Int,
    var createdAt:OffsetDateTime,
    var updatedAt:OffsetDateTime
)
