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
        val accountToBeDeleted = statements.distinctBy { it.account }.map { it.account }
        val newAccount = AccountEntity(name, Date())
        val mergedTransactions = statements.map { it.transactions }.flatMap { transitions ->
            transitions.map {
                TransactionEntity(
                    name,
                    it.date,
                    it.money,
                    it.description
                )
            }
        }
        deleteAccountsUseCase.invoke(accountToBeDeleted)
        addAccountsUseCase(listOf(newAccount))
        /*
        TODO
            android.database.sqlite.SQLiteConstraintException: UNIQUE constraint failed: transactions.accountName, transactions.date, transactions.money, transactions.description (code 1555 SQLITE_CONSTRAINT_PRIMARYKEY)
            at android.database.sqlite.SQLiteConnection.nativeExecuteForLastInsertedRowId(Native Method)
            at android.database.sqlite.SQLiteConnection.executeForLastInsertedRowId(SQLiteConnection.java:879)
            at android.database.sqlite.SQLiteSession.executeForLastInsertedRowId(SQLiteSession.java:790)
            at android.database.sqlite.SQLiteStatement.executeInsert(SQLiteStatement.java:88)
            at androidx.sqlite.db.framework.FrameworkSQLiteStatement.executeInsert(FrameworkSQLiteStatement.java:51)
            at androidx.room.EntityInsertionAdapter.insert(EntityInsertionAdapter.java:97)
         */
        addTransactionsUseCase(mergedTransactions)

    }
}
