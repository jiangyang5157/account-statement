package com.gmail.jiangyang5157.account_statement.bank_domain.interactor

import com.gmail.jiangyang5157.account_statement.bank_domain.entity.AccountEntity
import com.gmail.jiangyang5157.account_statement.bank_domain.entity.StatementEntity
import com.gmail.jiangyang5157.account_statement.bank_domain.entity.TransactionEntity
import com.gmail.jiangyang5157.account_statement.bank_domain.repository.StatementRepository
import javax.inject.Inject

class MergeStatementUseCase @Inject constructor(
    private val statementRepository: StatementRepository
) {

    operator fun invoke(account: AccountEntity, statements: List<StatementEntity>) {
//        val accountsToBeDeleted = statements.distinctBy { it.account }.map { it.account }
        val mergedTransactions = statements.map { it.transactions }.flatMap { transitions ->
            transitions.map {
                TransactionEntity(
                    account.name,
                    it.date,
                    it.money,
                    it.description
                )
            }
        }
//        statementRepository.deleteAccounts(accountsToBeDeleted)
        statementRepository.addStatement(account, mergedTransactions)
    }
}
