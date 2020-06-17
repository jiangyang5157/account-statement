package com.gmail.jiangyang5157.account_statement.account_repo.usecase

import com.gmail.jiangyang5157.account_statement.account_cvo.TransactionEntity
import com.gmail.jiangyang5157.account_statement.account_repo.AccountRepository
import javax.inject.Inject

class AddTransactionsUseCase @Inject constructor(
    private val accountRepository: AccountRepository
)  {

    operator fun invoke(transactions: List<TransactionEntity>) {
        accountRepository.addTransactions(transactions)
    }
}
