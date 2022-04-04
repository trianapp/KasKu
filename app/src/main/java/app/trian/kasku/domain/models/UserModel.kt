package app.trian.kasku.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import app.trian.kasku.common.fromOffsetDateTime
import app.trian.kasku.common.toOffsetDateTime
import app.trian.kasku.data.local.entity.User
import java.time.OffsetDateTime


data class UserModel(
    var uid:String,
    var dateOfBirth:String
)

fun User.toModel()=UserModel(
    uid = uid,
    dateOfBirth = dateOfBirth.fromOffsetDateTime() ?: ""
)

fun UserModel.toEntity() = User(
    uid = uid,
    dateOfBirth = dateOfBirth.toOffsetDateTime() ?: OffsetDateTime.now()
)