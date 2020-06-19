package com.gmail.jiangyang5157.account_statement.app

import com.gmail.jiangyang5157.account_statement.HomeActivity
import com.gmail.jiangyang5157.account_statement.router.DefaultFragmentTransition
import com.gmail.jiangyang5157.account_statement.router.UriRoute
import com.gmail.jiangyang5157.account_statement.ui.accounts.AccountsFragment
import com.gmail.jiangyang5157.android.router.core.MultiRouter
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import com.gmail.jiangyang5157.core.util.AppExecutor
import com.gmail.jiangyang5157.kotlin_kit.data.model.Key
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object MainAppModule {

    @Provides
    @Singleton
    fun provideAppExecutor(): AppExecutor {
        return AppExecutor()
    }

    // TODO sep router
    @Provides
    @Singleton
    fun provideRouter(): MultiRouter<String, UriRoute> = MultiRouter {
        when (it) {
            HomeActivity::class.java.name -> {
                FragmentRouter {
                    transition {
                        register(DefaultFragmentTransition())
                    }
                    fragment {
                        map(Key("app://account-statement/accounts")) { AccountsFragment::class }
                    }
                }
            }
            else -> {
                throw IllegalArgumentException("Factory of router $it is not implemented.")
            }
        }
    }
}
