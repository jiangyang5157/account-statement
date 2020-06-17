package com.gmail.jiangyang5157.account_statement.account_repo.usecase

import com.gmail.jiangyang5157.account_statement.account_cvo.AccountEntity
import com.gmail.jiangyang5157.account_statement.account_repo.AccountRepository
import javax.inject.Inject

class AddAccountsUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {

    operator fun invoke(accounts: List<AccountEntity>) {
        accountRepository.addAccounts(accounts)
    }
}
