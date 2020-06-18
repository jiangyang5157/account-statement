package com.gmail.jiangyang5157.account_statement.feature_account.parser

import com.gmail.jiangyang5157.account_statement.account_cvo.TransactionEntity

class CsvTransactionParser(private val accountName: String) :
    Parser<CsvTransaction, TransactionEntity> {

    override fun parse(from: CsvTransaction?): TransactionEntity? {
        return from?.let {
            if (it.validate()) {
                TransactionEntity(
                    accountName = accountName,
                    date = it.date.value!!,
                    money = it.money.value!!,
                    description = it.description
                )
            } else {
                null
            }
        }
    }
}
