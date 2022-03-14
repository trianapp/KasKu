package app.trian.kasku.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.trian.kasku.data.local.entity.BankAccount

/**
 *
 * author Trian Damai
 * created_at 14/03/22 - 22.24
 * site https://trian.app
 */
@Dao
interface BankDao {

    @Query("SELECT * FROM BankAccount WHERE uid=:uid")
    fun getBankAccountById(uid:String):BankAccount?

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    fun insert(data:BankAccount)
}