package com.gmail.jiangyang5157.account_statement.app

import com.gmail.jiangyang5157.core.util.AppExecutor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object MainAppModule {

    @Singleton
    @Provides
    fun provideAppExecutor(): AppExecutor {
        return AppExecutor()
    }
}
