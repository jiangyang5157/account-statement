package com.gmail.jiangyang5157.account_statement.bank_domain.interactor

import com.gmail.jiangyang5157.account_statement.bank_domain.entity.AccountEntity
import com.gmail.jiangyang5157.account_statement.bank_domain.entity.TransactionEntity
import com.gmail.jiangyang5157.account_statement.bank_domain.repository.StatementRepository
import javax.inject.Inject

class AddStatementUseCase @Inject constructor(
    private val statementRepository: StatementRepository
) {

    operator fun invoke(account: AccountEntity, transactions: List<TransactionEntity>) {
        statementRepository.addAccounts(listOf(account))
        statementRepository.addTransactions(transactions)
    }
}
