package app.trian.kasku.di

import app.trian.kasku.common.DispatcherProvider
import app.trian.kasku.data.local.dao.*
import app.trian.kasku.data.repository.*
import app.trian.kasku.data.repository.design.*
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

    @Provides
    fun provideBudgetRepository(
        dispatcherProvider: DispatcherProvider,
        firestore: FirebaseFirestore,
        firebaseAuth: FirebaseAuth,
        budgetDao: BudgetDao
    ):BudgetRepository = BudgetRepositoryImpl(
        dispatcherProvider,
        firestore,
        firebaseAuth,
        budgetDao
    )

    @Provides
    fun provideCategoryRepository(
        dispatcherProvider: DispatcherProvider,
        firestore: FirebaseFirestore,
        firebaseAuth: FirebaseAuth,
        categoryDao: CategoryDao
    ):CategoryRepository = CategoryRepositoryImpl(
        dispatcherProvider,
        firestore,
        firebaseAuth,
        categoryDao
    )

    @Provides
    fun provideTransactionRepository(
        dispatcherProvider: DispatcherProvider,
        transactionDao: TransactionDao
    ):TransactionRepository = TransactionRepositoryImpl(
        dispatcherProvider,
        transactionDao
    )
}