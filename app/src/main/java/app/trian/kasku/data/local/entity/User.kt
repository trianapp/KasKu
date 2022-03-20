package app.trian.kasku.data.local.entity

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.OffsetDateTime

@Entity
@Keep
data class User(
    @PrimaryKey
    var uid:String,
    var dateOfBirth:OffsetDateTime
)
