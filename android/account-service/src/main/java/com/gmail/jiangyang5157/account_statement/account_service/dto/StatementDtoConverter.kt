package com.gmail.jiangyang5157.account_statement.account_service.dto

import com.gmail.jiangyang5157.account_statement.account_cvo.StatementEntity
import com.gmail.jiangyang5157.kotlin_kit.data.model.Converter

class StatementDtoConverter : Converter<StatementDto, StatementEntity> {

    override fun backward(b: StatementEntity?): StatementDto? =
        b?.let {
            StatementDto(
                AccountDtoConverter().backward(it.account)!!,
                it.transactions.map { transaction ->
                    TransactionDtoConverter().backward(transaction)!!
                }
            )
        } ?: throw IllegalArgumentException("Cannot convert $b to [StatementDto]")

    override fun forward(a: StatementDto?): StatementEntity? =
        a?.let {
            StatementEntity(
                AccountDtoConverter().forward(it.account)!!,
                it.transactions.map { transaction ->
                    TransactionDtoConverter().forward(transaction)!!
                }
            )
        } ?: throw IllegalArgumentException("Cannot convert $a to [StatementEntity]")
}
