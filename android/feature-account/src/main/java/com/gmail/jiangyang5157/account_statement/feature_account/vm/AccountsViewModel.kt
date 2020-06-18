package com.gmail.jiangyang5157.account_statement.feature_account.vm

import androidx.lifecycle.LiveData
import com.gmail.jiangyang5157.account_statement.account_cvo.StatementEntity
import com.gmail.jiangyang5157.account_statement.account_usecase.AddAccountsUseCase
import com.gmail.jiangyang5157.account_statement.account_usecase.AddTransactionsUseCase
import com.gmail.jiangyang5157.account_statement.account_usecase.GetStatementsUseCase
import com.gmail.jiangyang5157.core.data.Resource
import javax.inject.Inject

class AccountsViewModel @Inject constructor(
    private val getStatementsUseCase: GetStatementsUseCase,
    private val addAccountsUseCase: AddAccountsUseCase,
    private val addTransactionsUseCase: AddTransactionsUseCase
) {

    fun getStatements(): LiveData<Resource<List<StatementEntity>>> {
        return getStatementsUseCase()
    }
}
