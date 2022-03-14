package app.trian.kasku.domain.models

import app.trian.kasku.data.local.entity.User
import com.google.firebase.auth.FirebaseUser

data class CurrentUserModel(
    var user:User?,
    var authUser:FirebaseUser?
)
