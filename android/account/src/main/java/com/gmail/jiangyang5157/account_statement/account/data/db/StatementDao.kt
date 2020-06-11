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
    fun findStatements(): LiveData<List<StatementEntity>>

    @Query("SELECT * from accounts WHERE name = :name")
    fun findStatementsByAccountName(name: String): LiveData<List<StatementEntity>>
}
