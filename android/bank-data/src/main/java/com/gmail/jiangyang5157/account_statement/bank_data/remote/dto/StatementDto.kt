package com.gmail.jiangyang5157.account_statement.bank_data.remote.dto

import com.gmail.jiangyang5157.account_statement.bank_domain.entity.StatementEntity
import com.google.gson.annotations.SerializedName

/**
 * StatementDto is service model
 *
 * [StatementDto.Converter] account model between [StatementDto] and [StatementEntity]
 */
data class StatementDto(

    @field:SerializedName("account")
    val account: AccountDto,

    @field:SerializedName("transactions")
    val transactions: List<TransactionDto>
) {

    class Converter :
        com.gmail.jiangyang5157.kotlin_kit.data.model.Converter<StatementDto, StatementEntity> {

        override fun backward(b: StatementEntity?): StatementDto? =
            b?.let {
                StatementDto(
                    AccountDto.Converter().backward(it.account)!!,
                    it.transactions.map { transaction ->
                        TransactionDto.Converter().backward(transaction)!!
                    }
                )
            }

        override fun forward(a: StatementDto?): StatementEntity? =
            a?.let {
                StatementEntity(
                    AccountDto.Converter().forward(it.account)!!,
                    it.transactions.map { transaction ->
                        TransactionDto.Converter().forward(transaction)!!
                    }
                )
            }
    }
}
