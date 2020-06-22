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
    private val deleteAccountUseCase: DeleteAccountUseCase,
    private val deleteTransactionUseCase: DeleteTransactionUseCase
) : ViewModel() {

    fun getStatements(): LiveData<Resource<List<StatementEntity>>> {
        return getStatementUseCase()
    }

    fun addAccounts(accounts: List<AccountEntity>) {
        return addAccountUseCase(accounts)
    }

    fun addTransactions(transactions: List<TransactionEntity>) {
        return addTransactionUseCase(transactions)
    }

    fun deleteAccounts(accounts: List<AccountEntity>) {
        return deleteAccountUseCase(accounts)
    }

    fun mergeStatements(name: String, statements: List<StatementEntity>) {
        val accountsToBeDeleted = statements.distinctBy { it.account }.map { it.account }
        val transactionsToBeDeleted = statements.flatMap { it.transactions }
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
//        deleteTransactionUseCase(transactionsToBeDeleted)
//        deleteAccountUseCase.invoke(accountsToBeDeleted)
        addAccountUseCase(listOf(newAccount))
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
        addTransactionUseCase(mergedTransactions)

    }
}
