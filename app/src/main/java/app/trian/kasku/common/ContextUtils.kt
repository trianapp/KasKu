package app.trian.kasku.common

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import es.dmoral.toasty.Toasty

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 15.20
 * site https://trian.app
 */
fun Context.getAppVersion():String{
    return try {
        this.packageManager.getPackageInfo(this.packageName,0).versionName
    }catch (e:Exception){
        "0.0.0"
    }
}

fun Context.toastSuccess(message:String){
    Toasty.success(this,message,Toast.LENGTH_SHORT).show()
}
fun Context.toastError(message:String){
    Toasty.error(this,message,Toast.LENGTH_SHORT).show()
}
fun Context.toastWarning(message:String){
    Toasty.warning(this,message,Toast.LENGTH_SHORT).show()
}
fun Context.toastInfo(message:String){
    Toasty.info(this,message,Toast.LENGTH_SHORT).show()
}
fun Context.toastNormal(message:String){
    Toasty.normal(this,message, Toast.LENGTH_SHORT).show()
}

fun Context.hideKeyboard(){
    val activity = (this as Activity)
    val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = activity.currentFocus
    if(view == null){
        view = View(activity)
    }
    imm.hideSoftInputFromWindow(view.windowToken,0)
}

@SuppressLint("QueryPermissionsNeeded")
fun Context.emailTo(from:String="",to:String, subject:String){

    Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:$to?subject=$subject&body=$subject - ")
        putExtra(Intent.EXTRA_EMAIL,from)
    }.also { intent ->
        this.startActivity(intent)

    }
}

fun Context.gotoApp(){
    val uri: Uri = Uri.parse("market://details?id=$packageName")
    Intent(Intent.ACTION_VIEW, uri).apply {
        addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
    }.also { intent->
        this.startActivity(intent)
    }
}