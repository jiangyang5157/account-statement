package com.gmail.jiangyang5157.account_statement.router

import com.gmail.jiangyang5157.account_statement.ui.statement.AddStatementFragment
import com.gmail.jiangyang5157.account_statement.ui.statement.StatementFragment
import com.gmail.jiangyang5157.account_statement.ui.statements.StatementsFragment
import com.gmail.jiangyang5157.account_statement.ui.transaction.TransactionsFragment
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import com.gmail.jiangyang5157.kotlin_kit.data.model.Key
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Qualifier

@Qualifier
annotation class HomeRouter

@InstallIn(ActivityComponent::class)
@Module
object RouterModule {

    @HomeRouter
    @ActivityScoped
    @Provides
    fun provideHomeRouter(): FragmentRouter<UriRoute> =
        FragmentRouter {
            transition {
                register(DefaultFragmentTransition())
            }
            fragment {
                map(Key("app://account-statement/statements")) { StatementsFragment::class }
                map(Key("app://account-statement/add-statement")) { AddStatementFragment::class }
                map(Key("app://account-statement/statement")) { StatementFragment::class }
                map(Key("app://account-statement/transactions")) { TransactionsFragment::class }
            }
        }
}
