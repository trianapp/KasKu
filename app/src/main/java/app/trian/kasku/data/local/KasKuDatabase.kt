package app.trian.kasku.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.trian.kasku.data.local.dao.*
import app.trian.kasku.data.local.entity.*

/**
 * Local Database
 * author Trian Damai
 * created_at 13/03/22 - 19.40
 * site https://trian.app
 */
@Database(
    entities = [
        User::class,
        BankAccount::class,
        Budget::class,
        Category::class,
        Transaction::class
    ],
    version = 7,
    exportSchema = true
)
@TypeConverters(
    DateConverter::class
)
abstract class KasKuDatabase :RoomDatabase(){
    abstract fun userDao():UserDao
    abstract fun bankDao():BankDao
    abstract fun budgetDao():BudgetDao
    abstract fun categoryDao():CategoryDao
    abstract fun transactionDao():TransactionDao

    companion object{
        const val DB_NAME = "KASKU"
    }
}