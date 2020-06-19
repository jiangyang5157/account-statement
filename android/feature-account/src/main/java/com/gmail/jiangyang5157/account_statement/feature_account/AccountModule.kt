package com.gmail.jiangyang5157.account_statement.feature_account

import android.content.Context
import com.gmail.jiangyang5157.account_statement.account_db.StatementDao
import com.gmail.jiangyang5157.account_statement.account_db.StatementDb
import com.gmail.jiangyang5157.account_statement.account_service.StatementService
import com.gmail.jiangyang5157.account_statement.account_usecase.repo.AccountRepository
import com.gmail.jiangyang5157.account_statement.account_usecase.repo.DefaultAccountRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object AccountServiceModule {

    @Singleton
    @Provides
    fun provideStatementService(): StatementService {
        return StatementService.Builder().build()
    }
}

@InstallIn(ApplicationComponent::class)
@Module
object AccountDbModule {

    @Singleton
    @Provides
    fun provideStatementDb(@ApplicationContext appContext: Context): StatementDb {
        // TODO close solution
        return StatementDb.Builder().build(appContext)
    }

    @Provides
    fun provideStatementDao(db: StatementDb): StatementDao {
        return db.statementDao()
    }
}

@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class ColorDataModule {

    @ActivityRetainedScoped
    @Binds
    abstract fun bindAccountRepository(repository: DefaultAccountRepository): AccountRepository
}
