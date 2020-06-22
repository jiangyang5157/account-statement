package com.gmail.jiangyang5157.account_statement.bank_presentation.vm

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gmail.jiangyang5157.account_statement.bank_domain.entity.AccountEntity
import com.gmail.jiangyang5157.account_statement.bank_domain.entity.StatementEntity
import com.gmail.jiangyang5157.account_statement.bank_domain.entity.TransactionEntity
import com.gmail.jiangyang5157.account_statement.bank_domain.interactor.*
import com.gmail.jiangyang5157.core.data.Resource
import java.util.*

class StatementViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val getStatementUseCase: GetStatementUseCase,
    private val addAccountUseCase: AddAccountUseCase,
    private val addTransactionUseCase: AddTransactionUseCase,
    private val deleteAccountUseCase: DeleteAccountUseCase
) : ViewModel() {

    fun getStatements(): LiveData<Resource<List<StatementEntity>>> {
        return getStatementUseCase()
    }

    fun deleteAccounts(accounts: List<AccountEntity>) {
        return deleteAccountUseCase(accounts)
    }

    fun addStatement(accountName: String, transactions: List<TransactionEntity>) {
        addAccountUseCase(listOf(AccountEntity(accountName, Date())))
        addTransactionUseCase(transactions)
    }

    fun mergeStatements(accountName: String, statements: List<StatementEntity>) {
        val accountsToBeDeleted = statements.distinctBy { it.account }.map { it.account }
        val newAccount = AccountEntity(accountName, Date())
        val mergedTransactions = statements.map { it.transactions }.flatMap { transitions ->
            transitions.map {
                TransactionEntity(
                    accountName,
                    it.date,
                    it.money,
                    it.description
                )
            }
        }
        deleteAccountUseCase.invoke(accountsToBeDeleted)
        addAccountUseCase(listOf(newAccount))
        addTransactionUseCase(mergedTransactions)
    }
}
