package com.gmail.jiangyang5157.account_statement.account_service.dto

import com.google.gson.annotations.SerializedName

data class StatementDto(

    @field:SerializedName("account")
    val account: AccountDto,

    @field:SerializedName("transactions")
    val transactions: List<TransactionDto>
)
