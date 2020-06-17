package com.gmail.jiangyang5157.account_statement.account_repo.usecase

import androidx.lifecycle.LiveData
import com.gmail.jiangyang5157.account_statement.account_cvo.StatementEntity
import com.gmail.jiangyang5157.account_statement.account_repo.AccountRepository
import com.gmail.jiangyang5157.core.data.Resource
import javax.inject.Inject

class GetStatementByAccountNameUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {

    operator fun invoke(name: String): LiveData<Resource<StatementEntity>> {
        return accountRepository.getStatementByAccountName(name)
    }
}
