package com.gmail.jiangyang5157.account_statement.account.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gmail.jiangyang5157.account_statement.account.data.api.StatementService
import com.gmail.jiangyang5157.account_statement.account.data.db.StatementDao
import com.gmail.jiangyang5157.account_statement.account.domain.model.AccountEntity
import com.gmail.jiangyang5157.account_statement.account.domain.model.StatementEntity
import com.gmail.jiangyang5157.account_statement.account.domain.model.TransactionEntity
import com.gmail.jiangyang5157.account_statement.account.domain.repo.AccountRepository
import com.gmail.jiangyang5157.core.data.NetworkBoundResource
import com.gmail.jiangyang5157.core.data.Resource
import com.gmail.jiangyang5157.core.network.ApiResponse
import com.gmail.jiangyang5157.core.util.AppExecutor
import javax.inject.Inject

class DefaultAccountRepository @Inject constructor(
    private val appExecutor: AppExecutor,
    private val statementDao: StatementDao,
    private val statementService: StatementService
) : AccountRepository {

    override fun addAccounts(accounts: List<AccountEntity>) {
        statementDao.insertAccounts(accounts)
    }

    override fun addTransactions(transactions: List<TransactionEntity>) {
        statementDao.insertTransactions(transactions)
    }

    override fun deleteAccounts(accounts: List<AccountEntity>) {
        statementDao.deleteAccounts(accounts)
    }

    override fun deleteTransactions(transactions: List<TransactionEntity>) {
        statementDao.deleteTransactions(transactions)
    }

    override fun getStatements(): LiveData<Resource<List<StatementEntity>>> {
        return object :
            NetworkBoundResource<List<StatementEntity>, List<StatementEntity>>(appExecutor) {
            override fun loadFromDb(): LiveData<List<StatementEntity>> {
                return statementDao.findStatements()
            }

            override fun shouldFetch(data: List<StatementEntity>?): Boolean {
                return data.isNullOrEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<StatementEntity>>> {
                return statementService.fetchStatements()
            }

            override fun saveCallResult(item: List<StatementEntity>) {
                addAccounts(item.map { it.account })
                addTransactions(item.flatMap { it.transactions })
            }
        }.asLiveData()
    }

    override fun getStatementByAccountName(name: String): LiveData<Resource<StatementEntity>> {
        return MutableLiveData(Resource.success(statementDao.findStatementByAccountName(name).value))
    }
}
