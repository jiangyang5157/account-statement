package com.gmail.jiangyang5157.account_statement.bank

import com.gmail.jiangyang5157.account_statement.account_cvo.TransactionEntity
import com.gmail.jiangyang5157.kotlin_kit.data.model.Converter

class CsvTransactionConverter(private val accountName: String) :
    Converter<CsvTransaction, TransactionEntity> {

    override fun backward(b: TransactionEntity?): CsvTransaction? {
        throw UnsupportedOperationException("Convert [TransactionEntity] to [CsvTransaction] is not supported")
    }

    override fun forward(a: CsvTransaction?): TransactionEntity? {
        return a?.let {
            if (a.validate()) {
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
