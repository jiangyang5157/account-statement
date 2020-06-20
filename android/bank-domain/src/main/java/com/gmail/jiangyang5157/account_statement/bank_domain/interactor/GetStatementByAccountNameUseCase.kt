package com.gmail.jiangyang5157.account_statement.bank_domain.interactor

import androidx.lifecycle.LiveData
import com.gmail.jiangyang5157.account_statement.bank_domain.entity.StatementEntity
import com.gmail.jiangyang5157.account_statement.bank_domain.repository.StatementRepository
import com.gmail.jiangyang5157.core.data.Resource
import javax.inject.Inject

class GetStatementByAccountNameUseCase @Inject constructor(
    private val statementRepository: StatementRepository
) {

    operator fun invoke(name: String): LiveData<Resource<StatementEntity>> {
        return statementRepository.getStatementByAccountName(name)
    }
}
