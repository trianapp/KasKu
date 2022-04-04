package app.trian.kasku.data.local

import androidx.room.TypeConverter
import app.trian.kasku.common.fromOffsetDateTime
import app.trian.kasku.common.toOffsetDateTime
import java.time.OffsetDateTime

/**
 * Converter date room
 * author Trian Damai
 * created_at 13/03/22 - 19.53
 * site https://trian.app
 */

object DateConverter{
    @TypeConverter
    @JvmStatic
    fun toOffsetDateTime(value:String?):OffsetDateTime?{
       return value.toOffsetDateTime()
    }

    @TypeConverter
    @JvmStatic
    fun fromOffsetDateTime(date:OffsetDateTime?):String?{
        return date.fromOffsetDateTime()
    }
}