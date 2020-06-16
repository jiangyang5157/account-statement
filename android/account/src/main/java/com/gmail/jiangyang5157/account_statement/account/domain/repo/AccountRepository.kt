package com.gmail.jiangyang5157.account_statement.account.domain.repo

import androidx.lifecycle.LiveData
import com.gmail.jiangyang5157.account_statement.account.domain.model.AccountEntity
import com.gmail.jiangyang5157.account_statement.account.domain.model.StatementEntity
import com.gmail.jiangyang5157.account_statement.account.domain.model.TransactionEntity
import com.gmail.jiangyang5157.core.data.Resource

interface AccountRepository {

    fun addAccounts(accounts: List<AccountEntity>)

    fun addTransactions(transactions: List<TransactionEntity>)

    fun deleteAccounts(accounts: List<AccountEntity>)

    fun getStatements(): LiveData<Resource<List<StatementEntity>>>

    fun getStatementByAccountName(name: String): LiveData<Resource<StatementEntity>>
}
