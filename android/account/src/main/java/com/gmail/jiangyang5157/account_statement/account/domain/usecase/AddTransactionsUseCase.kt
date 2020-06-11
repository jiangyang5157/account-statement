package com.gmail.jiangyang5157.account_statement.account.domain.usecase

import com.gmail.jiangyang5157.account_statement.account.domain.entity.TransactionEntity
import com.gmail.jiangyang5157.account_statement.account.domain.repository.AccountRepository
import javax.inject.Inject

class AddTransactionsUseCase @Inject constructor(
    private val accountRepository: AccountRepository
)  {

    operator fun invoke(transactions: List<TransactionEntity>) {
        accountRepository.addTransactions(transactions)
    }
}
