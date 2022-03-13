package app.trian.kasku.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    var uid:String,
    var name:String,
    var email:String,
    var dateOfBirth:String
)
