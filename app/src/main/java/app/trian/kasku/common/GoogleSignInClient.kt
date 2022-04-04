package app.trian.kasku.common

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

/**
 *
 * author Trian Damai
 * created_at 13/03/22 - 20.00
 * site https://trian.app
 */
fun getGoogleSignInClient(context: Context): GoogleSignInClient {
    val signInOptions = GoogleSignInOptions.Builder(
        GoogleSignInOptions.DEFAULT_SIGN_IN
    ).requestIdToken("543201836893-2n95d496dc1o5s06u45okiv17mlcphlo.apps.googleusercontent.com")
        .requestEmail()
        .build()

    return GoogleSignIn.getClient(context,signInOptions)
}