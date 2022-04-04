package app.trian.kasku.common

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri

/**
 *
 * author Trian Damai
 * created_at 21/03/22 - 01.20
 * site https://trian.app
 */

fun Uri?.getBitmap(c:ContentResolver):Bitmap?{
    return try {
        if(this == null) return null
        val input = c.openInputStream(this)
        BitmapFactory.decodeStream(input)
    }catch (e:Exception){
        null
    }
}