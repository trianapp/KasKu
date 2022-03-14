package app.trian.kasku.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import app.trian.kasku.data.local.KasKuDatabase
import app.trian.kasku.data.local.dao.BankDao
import app.trian.kasku.data.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 *
 * author Trian Damai
 * created_at 13/03/22 - 19.57
 * site https://trian.app
 */
@Module
@InstallIn(
    value = [SingletonComponent::class]
)
object DatabaseModule {
    @Provides
    fun provideDatabase(
        @ApplicationContext appContext:Context
    ):KasKuDatabase = Room.databaseBuilder(
        appContext,
        KasKuDatabase::class.java,
        KasKuDatabase.DB_NAME
    )
        .fallbackToDestructiveMigration()
        .addCallback(
            object :RoomDatabase.Callback(){
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                }
            }
        )
        .build()

    @Provides
    fun provideUserDao(
        db:KasKuDatabase
    ):UserDao = db.userDao()

    @Provides
    fun provideBankDao(
        db:KasKuDatabase
    ):BankDao = db.bankDao()
}