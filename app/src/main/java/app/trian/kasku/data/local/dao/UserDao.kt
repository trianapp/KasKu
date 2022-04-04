package app.trian.kasku.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.trian.kasku.data.local.entity.User

@Dao
interface UserDao {

    @Query("SELECT * FROM User WHERE uid=:uid")
    fun getUser(uid:String):User?

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    fun insert(user:User)
}