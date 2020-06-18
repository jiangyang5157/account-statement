package com.gmail.jiangyang5157.account_statement.account_db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gmail.jiangyang5157.account_statement.account_cvo.AccountEntity
import com.gmail.jiangyang5157.account_statement.account_cvo.StatementEntity
import com.gmail.jiangyang5157.account_statement.account_cvo.TransactionEntity

@Dao
interface StatementDao {

    @Insert
    fun insertAccounts(accounts: List<AccountEntity>)

    @Insert
    fun insertTransactions(transactions: List<TransactionEntity>)

    @Delete
    fun deleteAccounts(accounts: List<AccountEntity>)

    @Delete
    fun deleteTransactions(transactions: List<TransactionEntity>)

    @Update
    fun updateAccounts(accounts: List<AccountEntity>)

    @Query("SELECT * from accounts")
    fun findAccounts(): LiveData<List<AccountEntity>>

    @Query("SELECT * from accounts WHERE name = :name")
    fun findAccountByName(name: String): LiveData<AccountEntity>

    @Query("SELECT * from transactions")
    fun findTransactions(): LiveData<List<TransactionEntity>>

    @Query("SELECT * from transactions WHERE accountName = :accountName")
    fun findTransactionsByAccountName(accountName: String): LiveData<List<TransactionEntity>>

    @Transaction
    @Query("SELECT * from accounts")
    fun findStatements(): LiveData<List<StatementEntity>>

    @Query("SELECT * from accounts WHERE name = :accountName")
    fun findStatementByAccountName(accountName: String): LiveData<StatementEntity>
}