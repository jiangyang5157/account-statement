package com.gmail.jiangyang5157.account_statement.feature_account.vm

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gmail.jiangyang5157.account_statement.account_cvo.AccountEntity
import com.gmail.jiangyang5157.account_statement.account_cvo.StatementEntity
import com.gmail.jiangyang5157.account_statement.account_cvo.TransactionEntity
import com.gmail.jiangyang5157.account_statement.account_usecase.AddAccountsUseCase
import com.gmail.jiangyang5157.account_statement.account_usecase.AddTransactionsUseCase
import com.gmail.jiangyang5157.account_statement.account_usecase.GetStatementsUseCase
import com.gmail.jiangyang5157.core.data.Resource

class AccountsViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val getStatementsUseCase: GetStatementsUseCase,
    private val addAccountsUseCase: AddAccountsUseCase,
    private val addTransactionsUseCase: AddTransactionsUseCase
) : ViewModel() {

    fun getStatements(): LiveData<Resource<List<StatementEntity>>> {
        return getStatementsUseCase()
    }

    fun addAccounts(accounts: List<AccountEntity>) {
        return addAccountsUseCase(accounts)
    }

    fun addTransactions(transactions: List<TransactionEntity>) {
        return addTransactionsUseCase(transactions)
    }
}
