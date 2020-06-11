package com.gmail.jiangyang5157.account_statement.account.domain.repository

import androidx.lifecycle.LiveData
import com.gmail.jiangyang5157.account_statement.account.domain.entity.AccountEntity
import com.gmail.jiangyang5157.account_statement.account.domain.entity.StatementEntity
import com.gmail.jiangyang5157.account_statement.account.domain.entity.TransactionEntity
import com.gmail.jiangyang5157.core.data.Resource

interface AccountRepository {

    fun addAccounts(accounts: List<AccountEntity>)

    fun addTransactions(transactions: List<TransactionEntity>)

    fun deleteAccounts(accounts: List<AccountEntity>)

    fun updateAccounts(accounts: List<AccountEntity>)

    fun getStatements(): LiveData<Resource<List<StatementEntity>>>

    fun getStatementsByAccountName(name: String): LiveData<Resource<List<StatementEntity>>>
}
