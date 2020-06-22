package com.gmail.jiangyang5157.account_statement.bank_presentation.vm

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gmail.jiangyang5157.account_statement.bank_domain.entity.AccountEntity
import com.gmail.jiangyang5157.account_statement.bank_domain.entity.StatementEntity
import com.gmail.jiangyang5157.account_statement.bank_domain.entity.TransactionEntity
import com.gmail.jiangyang5157.account_statement.bank_domain.interactor.AddAccountsUseCase
import com.gmail.jiangyang5157.account_statement.bank_domain.interactor.AddTransactionsUseCase
import com.gmail.jiangyang5157.account_statement.bank_domain.interactor.DeleteAccountsUseCase
import com.gmail.jiangyang5157.account_statement.bank_domain.interactor.GetStatementsUseCase
import com.gmail.jiangyang5157.core.data.Resource

class StatementViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val getStatementsUseCase: GetStatementsUseCase,
    private val addAccountsUseCase: AddAccountsUseCase,
    private val addTransactionsUseCase: AddTransactionsUseCase,
    private val deleteAccountsUseCase: DeleteAccountsUseCase
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

    fun deleteAccounts(accounts: List<AccountEntity>) {
        return deleteAccountsUseCase(accounts)
    }

    fun mergeStatements(name: String, statements: List<StatementEntity>) {
        // TODO
    }
}
