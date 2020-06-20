package com.gmail.jiangyang5157.account_statement.bank_domain.interactor

import androidx.lifecycle.LiveData
import com.gmail.jiangyang5157.account_statement.bank_domain.entity.StatementEntity
import com.gmail.jiangyang5157.account_statement.bank_domain.repository.StatementRepository
import com.gmail.jiangyang5157.core.data.Resource
import javax.inject.Inject

class GetStatementsUseCase @Inject constructor(
    private val statementRepository: StatementRepository
) {

    operator fun invoke(): LiveData<Resource<List<StatementEntity>>> {
        return statementRepository.getStatements()
    }
}
