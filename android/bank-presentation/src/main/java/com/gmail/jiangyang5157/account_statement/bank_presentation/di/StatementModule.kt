package com.gmail.jiangyang5157.account_statement.bank_presentation.di

import android.content.Context
import com.gmail.jiangyang5157.account_statement.bank_data.local.StatementDao
import com.gmail.jiangyang5157.account_statement.bank_data.local.StatementDb
import com.gmail.jiangyang5157.account_statement.bank_data.remote.StatementService
import com.gmail.jiangyang5157.account_statement.bank_data.repository.DefaultStatementRepository
import com.gmail.jiangyang5157.account_statement.bank_domain.repository.StatementRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Singleton

@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class StatementRepositoryModule {

    @ActivityRetainedScoped
    @Binds
    abstract fun bindStatementRepository(repository: DefaultStatementRepository): StatementRepository
}

@InstallIn(ApplicationComponent::class)
@Module
object StatementDbModule {

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

@InstallIn(ApplicationComponent::class)
@Module
object StatementServiceModule {

    @Singleton
    @Provides
    fun provideStatementService(): StatementService {
        return StatementService.Builder().build()
    }
}
