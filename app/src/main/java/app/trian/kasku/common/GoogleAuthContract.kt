package app.trian.kasku.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task

/**
 *
 * author Trian Damai
 * created_at 13/03/22 - 20.00
 * site https://trian.app
 */
const val AUTH_GOOGLE_CODE = 111
class GoogleAuthContract: ActivityResultContract<Int, Task<GoogleSignInAccount>?>() {
    override fun createIntent(context: Context, input: Int): Intent {
        val gso= getGoogleSignInClient(context)
        gso.signOut()
        return gso.signInIntent
    }


    override fun parseResult(resultCode: Int, intent: Intent?): Task<GoogleSignInAccount>? {

        return when (resultCode){

            Activity.RESULT_OK->{
                return GoogleSignIn.getSignedInAccountFromIntent(intent)
            }
            else -> null
        }
    }



}