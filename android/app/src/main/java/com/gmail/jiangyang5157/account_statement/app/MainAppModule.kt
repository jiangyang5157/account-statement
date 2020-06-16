package com.gmail.jiangyang5157.account_statement.app

import androidx.lifecycle.ViewModelProvider
import com.gmail.jiangyang5157.account_statement.ui.accounts.AccountsFragment
import com.gmail.jiangyang5157.account_statement.HomeActivity
import com.gmail.jiangyang5157.account_statement.router.DefaultFragmentTransition
import com.gmail.jiangyang5157.account_statement.router.UriRoute
import com.gmail.jiangyang5157.android.router.core.MultiRouter
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import com.gmail.jiangyang5157.core.util.ViewModelFactory
import com.gmail.jiangyang5157.kotlin_kit.data.model.Key
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module(includes = [MainAppInjection::class])
class MainAppModule {

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

@Module()
abstract class MainAppInjection {

    @ContributesAndroidInjector(modules = [])
    abstract fun contributeHomeActivity(): HomeActivity

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory


}
