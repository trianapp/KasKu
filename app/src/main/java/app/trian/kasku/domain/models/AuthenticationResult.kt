package app.trian.kasku.domain.models

import com.google.firebase.auth.FirebaseUser

data class AuthenticationResult(
    var isNewUser:Boolean,
    var firebaseUser: FirebaseUser?
)
