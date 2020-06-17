package com.gmail.jiangyang5157.account_statement.account.domain.usecase

import com.gmail.jiangyang5157.account_statement.account.domain.repo.AccountRepository
import com.gmail.jiangyang5157.account_statement.account_cvo.AccountEntity
import javax.inject.Inject

class AddAccountsUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {

    operator fun invoke(accounts: List<AccountEntity>) {
        accountRepository.addAccounts(accounts)
    }
}
