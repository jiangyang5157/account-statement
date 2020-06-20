package com.gmail.jiangyang5157.account_statement.bank_domain.interactor

import com.gmail.jiangyang5157.account_statement.bank_domain.entity.TransactionEntity
import com.gmail.jiangyang5157.account_statement.bank_domain.repository.StatementRepository
import javax.inject.Inject

class DeleteTransactionsUseCase @Inject constructor(
    private val statementRepository: StatementRepository
) {

    operator fun invoke(transactions: List<TransactionEntity>) {
        statementRepository.deleteTransactions(transactions)
    }
}
