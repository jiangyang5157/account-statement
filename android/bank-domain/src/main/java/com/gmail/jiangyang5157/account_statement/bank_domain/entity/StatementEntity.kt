package com.gmail.jiangyang5157.account_statement.bank_domain.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.google.gson.annotations.SerializedName

data class StatementEntity(

    @Embedded
    @field:SerializedName("account")
    val account: AccountEntity,

    @Relation(
        parentColumn = "name",
        entityColumn = "accountName",
        entity = TransactionEntity::class
    )
    @field:SerializedName("transactions")
    val transactions: List<TransactionEntity>
)
