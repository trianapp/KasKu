package app.trian.kasku.common

import android.content.Context

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