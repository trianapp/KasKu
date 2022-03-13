package app.trian.kasku.common

import android.content.Context
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
