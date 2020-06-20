package com.gmail.jiangyang5157.account_statement.bank_data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gmail.jiangyang5157.account_statement.bank_data.local.StatementDao
import com.gmail.jiangyang5157.account_statement.bank_data.remote.StatementService
import com.gmail.jiangyang5157.account_statement.bank_data.remote.dto.StatementDto
import com.gmail.jiangyang5157.account_statement.bank_domain.entity.AccountEntity
import com.gmail.jiangyang5157.account_statement.bank_domain.entity.StatementEntity
import com.gmail.jiangyang5157.account_statement.bank_domain.entity.TransactionEntity
import com.gmail.jiangyang5157.account_statement.bank_domain.repository.StatementRepository
import com.gmail.jiangyang5157.core.data.NetworkBoundResource
import com.gmail.jiangyang5157.core.data.Resource
import com.gmail.jiangyang5157.core.network.ApiResponse
import com.gmail.jiangyang5157.core.util.AppExecutor
import javax.inject.Inject

class DefaultStatementRepository @Inject constructor(
    private val appExecutor: AppExecutor,
    private val statementDao: StatementDao,
    private val statementService: StatementService
) : StatementRepository {

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

    override fun updateAccounts(accounts: List<AccountEntity>) {
        statementDao.updateAccounts(accounts)
    }

    override fun getStatements(): LiveData<Resource<List<StatementEntity>>> {
        return object :
            NetworkBoundResource<List<StatementEntity>, List<StatementDto>>(appExecutor) {
            override fun loadFromDb(): LiveData<List<StatementEntity>> {
                return statementDao.findStatements()
            }

            override fun shouldFetch(data: List<StatementEntity>?): Boolean {
                return data.isNullOrEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<StatementDto>>> {
                return statementService.fetchStatements()
            }

            override fun saveCallResult(item: List<StatementDto>) {
                val statements: List<StatementEntity> = item.map {
                    StatementDto.Converter().forward(it)!!
                }
                addAccounts(statements.map { it.account })
                addTransactions(statements.flatMap { it.transactions })
            }
        }.asLiveData()
    }

    override fun getStatementByAccountName(name: String): LiveData<Resource<StatementEntity>> {
        return MutableLiveData(Resource.success(statementDao.findStatementByAccountName(name).value))
    }
}
