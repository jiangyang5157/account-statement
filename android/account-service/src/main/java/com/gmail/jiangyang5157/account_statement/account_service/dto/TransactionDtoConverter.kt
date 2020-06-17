package com.gmail.jiangyang5157.account_statement.account_service.dto

import com.gmail.jiangyang5157.account_statement.account_cvo.TransactionEntity
import com.gmail.jiangyang5157.kotlin_kit.data.model.Converter

class TransactionDtoConverter : Converter<TransactionDto, TransactionEntity> {

    override fun backward(b: TransactionEntity?): TransactionDto? =
        b?.let {
            TransactionDto(
                it.accountName,
                it.date,
                it.money,
                it.description
            )
        } ?: throw IllegalArgumentException("Cannot convert $b to [TransactionDto]")

    override fun forward(a: TransactionDto?): TransactionEntity? =
        a?.let {
            TransactionEntity(
                it.accountName,
                it.date,
                it.money,
                it.description
            )
        } ?: throw IllegalArgumentException("Cannot convert $a to [TransactionEntity]")
}
