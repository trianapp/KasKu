package app.trian.kasku.di

import app.trian.kasku.common.DefaultDispatcherProvider
import app.trian.kasku.common.DispatcherProvider
import app.trian.kasku.data.local.dao.BankDao
import app.trian.kasku.data.local.dao.UserDao
import app.trian.kasku.data.repository.BankRepositoryImpl
import app.trian.kasku.data.repository.UserRepositoryImpl
import app.trian.kasku.data.repository.design.BankRepository
import app.trian.kasku.data.repository.design.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 *
 * author Trian Damai
 * created_at 13/03/22 - 19.55
 * site https://trian.app
 */
@Module
@InstallIn(
    value = [
        SingletonComponent::class
    ]
)
object NetworkModule {
    @Provides
    fun provideDispatcher():DispatcherProvider= DefaultDispatcherProvider()

    @Provides
    fun provideFirebaseMessaging():FirebaseMessaging=FirebaseMessaging.getInstance()

    @Provides
    fun provideFirestore():FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    fun provideFirebaseAuth():FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()

}