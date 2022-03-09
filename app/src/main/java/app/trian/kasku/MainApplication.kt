package app.trian.kasku

import android.app.Application
import android.content.Context
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat
import androidx.multidex.MultiDex
import dagger.hilt.android.HiltAndroidApp
import es.dmoral.toasty.Toasty
import logcat.AndroidLogcatLogger
import logcat.LogPriority

@HiltAndroidApp
class MainApplication : Application(){
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        try {

            //configure toast
            val typeface: Typeface? = ResourcesCompat.getFont(this, R.font.gt_walsheim_regular)
            Toasty.Config.getInstance()
                .tintIcon(true)
                .setToastTypeface(typeface!!)
                .setTextSize(16)
                .allowQueue(true)
                .setGravity(1,0,1)
                .supportDarkTheme(true)
                .apply()
            AndroidLogcatLogger.installOnDebuggableApp(this, minPriority = LogPriority.VERBOSE)
        }catch (e:Exception){

        }
    }

}