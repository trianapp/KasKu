package app.trian.kasku.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.trian.kasku.data.local.dao.BankDao
import app.trian.kasku.data.local.dao.UserDao
import app.trian.kasku.data.local.entity.BankAccount
import app.trian.kasku.data.local.entity.User

/**
 * Local Database
 * author Trian Damai
 * created_at 13/03/22 - 19.40
 * site https://trian.app
 */
@Database(
    entities = [
        User::class,
        BankAccount::class
    ],
    version = 5,
    exportSchema = true
)
@TypeConverters(
    DateConverter::class
)
abstract class KasKuDatabase :RoomDatabase(){
    abstract fun userDao():UserDao
    abstract fun bankDao():BankDao
    companion object{
        const val DB_NAME = "KASKU"
    }
}