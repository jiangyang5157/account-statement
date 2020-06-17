package com.gmail.jiangyang5157.account_statement.account.domain.usecase

import com.gmail.jiangyang5157.account_statement.account.domain.model.TransactionEntity
import com.gmail.jiangyang5157.account_statement.account.domain.repo.AccountRepository
import javax.inject.Inject

class DeleteTransactionsUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {

    operator fun invoke(transactions: List<TransactionEntity>) {
        accountRepository.deleteTransactions(transactions)
    }
}
