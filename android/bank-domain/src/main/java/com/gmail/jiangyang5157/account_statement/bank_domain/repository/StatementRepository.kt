package com.gmail.jiangyang5157.account_statement.bank_domain.repository

import androidx.lifecycle.LiveData
import com.gmail.jiangyang5157.account_statement.bank_domain.entity.AccountEntity
import com.gmail.jiangyang5157.account_statement.bank_domain.entity.StatementEntity
import com.gmail.jiangyang5157.account_statement.bank_domain.entity.TransactionEntity
import com.gmail.jiangyang5157.core.data.Resource

interface StatementRepository {

    fun addAccounts(accounts: List<AccountEntity>)

    fun deleteAccounts(accounts: List<AccountEntity>)

    fun updateAccounts(accounts: List<AccountEntity>)

    fun addTransactions(transactions: List<TransactionEntity>)

    fun addStatement(account: AccountEntity, transactions: List<TransactionEntity>)

    fun getStatements(): LiveData<Resource<List<StatementEntity>>>

    fun getStatementByAccountName(accountName: String): LiveData<Resource<StatementEntity>>
}
