package com.gmail.jiangyang5157.account_statement.bank_presentation.parser

import com.gmail.jiangyang5157.account_statement.bank_domain.entity.TransactionEntity

class CsvTransactionParser(private val accountName: String) :
    Parser<CsvTransaction, TransactionEntity> {

    override fun parse(from: CsvTransaction?): TransactionEntity? {
        return from?.let {
            if (it.validate()) {
                TransactionEntity(
                    accountName = accountName,
                    date = it.date.value!!,
                    money = it.money.value!!,
                    description = it.description.removeSurrounding("\"", "\"")
                )
            } else {
                null
            }
        }
    }
}
