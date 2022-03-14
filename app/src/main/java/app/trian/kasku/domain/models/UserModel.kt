package app.trian.kasku.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import app.trian.kasku.common.fromOffsetDateTime
import app.trian.kasku.common.toOffsetDateTime
import app.trian.kasku.data.local.entity.User
import java.time.OffsetDateTime


data class UserModel(
    var uid:String,
    var name:String,
    var email:String,
    var dateOfBirth:String
)

fun User.toModel()=UserModel(
    uid = uid,
    name = name,
    email = email,
    dateOfBirth = dateOfBirth.fromOffsetDateTime() ?: ""
)

fun UserModel.toEntity() = User(
    uid = uid,
    name=name,
    email = email,
    dateOfBirth = dateOfBirth.toOffsetDateTime() ?: OffsetDateTime.now()
)