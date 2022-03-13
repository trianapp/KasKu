package app.trian.kasku.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * Local Database
 * author Trian Damai
 * created_at 13/03/22 - 19.40
 * site https://trian.app
 */
@Database(
    entities = [

    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(
    DateConverter::class
)
abstract class KasKuDatabase :RoomDatabase(){
    companion object{
        const val DB_NAME = "KASKU"
    }
}