package com.gmail.jiangyang5157.account_statement.account.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gmail.jiangyang5157.account_statement.account.domain.entity.AccountEntity
import com.gmail.jiangyang5157.account_statement.account.domain.entity.StatementEntity
import com.gmail.jiangyang5157.account_statement.account.domain.entity.TransactionEntity

@Dao
interface StatementDao {

    @Insert
    fun insertAccounts(accountEntity: List<AccountEntity>)

    @Insert
    fun insertTransactions(transactionEntity: List<TransactionEntity>)

    @Delete
    fun deleteAccounts(accountEntity: List<AccountEntity>)

    @Update
    fun updateAccounts(accountEntity: List<AccountEntity>)

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
