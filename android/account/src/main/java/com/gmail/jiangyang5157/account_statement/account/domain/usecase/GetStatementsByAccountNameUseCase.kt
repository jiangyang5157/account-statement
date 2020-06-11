package com.gmail.jiangyang5157.account_statement.account.domain.usecase

import androidx.lifecycle.LiveData
import com.gmail.jiangyang5157.account_statement.account.domain.entity.StatementEntity
import com.gmail.jiangyang5157.account_statement.account.domain.repository.AccountRepository
import com.gmail.jiangyang5157.core.data.Resource
import javax.inject.Inject

class GetStatementsByAccountNameUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {

    operator fun invoke(name: String): LiveData<Resource<List<StatementEntity>>> {
        return accountRepository.getStatementsByAccountName(name)
    }
}
