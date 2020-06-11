package com.gmail.jiangyang5157.account_statement.account.domain.usecase

import androidx.lifecycle.LiveData
import com.gmail.jiangyang5157.account_statement.account.domain.entity.StatementEntity
import com.gmail.jiangyang5157.account_statement.account.domain.repo.AccountRepository
import com.gmail.jiangyang5157.core.data.Resource
import javax.inject.Inject

class GetStatementsUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {

    operator fun invoke(): LiveData<Resource<List<StatementEntity>>> {
        return accountRepository.getStatements()
    }
}
