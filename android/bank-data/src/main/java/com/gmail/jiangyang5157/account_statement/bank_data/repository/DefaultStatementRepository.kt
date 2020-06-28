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
        appExecutor.diskIO.execute {
            statementDao.insertAccounts(accounts)
        }
    }

    override fun addTransactions(transactions: List<TransactionEntity>) {
        appExecutor.diskIO.execute {
            statementDao.insertTransactions(transactions)
        }
    }

    override fun deleteAccounts(accounts: List<AccountEntity>) {
        appExecutor.diskIO.execute {
            statementDao.deleteAccounts(accounts)
        }
    }

    override fun updateAccounts(accounts: List<AccountEntity>) {
        appExecutor.diskIO.execute {
            statementDao.updateAccounts(accounts)
        }
    }

    override fun addStatement(account: AccountEntity, transactions: List<TransactionEntity>) {
        appExecutor.diskIO.execute {
            statementDao.insertAccounts(listOf(account))
            statementDao.insertTransactions(transactions)
        }
    }

    /**
     * Demonstration of dealing with remote data [StatementDto] and local data [StatementEntity]
     */
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

    override fun getStatementByAccountName(accountName: String): LiveData<Resource<StatementEntity>> {
        return MutableLiveData(Resource.success(statementDao.findStatementByAccountName(accountName).value))
    }
}
