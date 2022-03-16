package app.trian.kasku.di

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
 * created_at 17/03/22 - 00.44
 * site https://trian.app
 */
@Module
@InstallIn(
    value = [
        SingletonComponent::class
    ]
)
object RepositoryModule {
    @Provides
    fun provideUserRepository(
        dispatcherProvider: DispatcherProvider,
        firebaseAuth: FirebaseAuth,
        firestore: FirebaseFirestore,
        firebaseMessaging: FirebaseMessaging,
        firebaseStorage: FirebaseStorage,
        userDao: UserDao
    ): UserRepository = UserRepositoryImpl(
        dispatcherProvider,
        firebaseAuth,
        firestore ,
        firebaseMessaging,
        firebaseStorage,
        userDao
    )

    @Provides
    fun provideBankRepository(
        dispatcherProvider: DispatcherProvider,
        firebaseAuth: FirebaseAuth,
        firestore: FirebaseFirestore,
        bankDao: BankDao
    ): BankRepository = BankRepositoryImpl(
        dispatcherProvider,
        firebaseAuth,
        firestore,
        bankDao
    )
}