package com.gmail.jiangyang5157.account_statement.bank_domain.interactor

import com.gmail.jiangyang5157.account_statement.bank_domain.entity.AccountEntity
import com.gmail.jiangyang5157.account_statement.bank_domain.repository.StatementRepository
import javax.inject.Inject

class DeleteAccountsUseCase @Inject constructor(
    private val statementRepository: StatementRepository
) {

    operator fun invoke(accounts: List<AccountEntity>) {
        statementRepository.deleteAccounts(accounts)
    }
}
