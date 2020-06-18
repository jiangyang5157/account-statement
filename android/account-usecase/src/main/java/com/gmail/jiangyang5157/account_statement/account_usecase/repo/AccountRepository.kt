package com.gmail.jiangyang5157.account_statement.account_usecase.repo

import androidx.lifecycle.LiveData
import com.gmail.jiangyang5157.account_statement.account_cvo.AccountEntity
import com.gmail.jiangyang5157.account_statement.account_cvo.StatementEntity
import com.gmail.jiangyang5157.account_statement.account_cvo.TransactionEntity
import com.gmail.jiangyang5157.core.data.Resource

interface AccountRepository {

    fun addAccounts(accounts: List<AccountEntity>)

    fun addTransactions(transactions: List<TransactionEntity>)

    fun deleteAccounts(accounts: List<AccountEntity>)

    fun updateAccounts(accounts: List<AccountEntity>)

    fun deleteTransactions(transactions: List<TransactionEntity>)

    fun getStatements(): LiveData<Resource<List<StatementEntity>>>

    fun getStatementByAccountName(name: String): LiveData<Resource<StatementEntity>>
}
