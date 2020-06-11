package com.gmail.jiangyang5157.account_statement.account.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import com.gmail.jiangyang5157.kotlin_kit.data.model.finance.Money
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(
    tableName = "transactions",
    primaryKeys = ["accountName", "date", "money", "description"],
    foreignKeys = [
        ForeignKey(
            entity = AccountEntity::class,
            parentColumns = ["name"],
            childColumns = ["accountName"],
            onDelete = CASCADE,
            onUpdate = CASCADE
        )
    ]
)
data class TransactionEntity(

    @ColumnInfo(name = "accountName")
    @field:SerializedName("accountName")
    val accountName: String,

    @ColumnInfo(name = "date")
    @field:SerializedName("date")
    @JsonAdapter(value = DateJsonAdapter::class)
    val date: Date,

    @ColumnInfo(name = "money")
    @field:SerializedName("money")
    @JsonAdapter(value = MoneyJsonAdapter::class)
    val money: Money,

    @ColumnInfo(name = "description")
    @field:SerializedName("description")
    val description: String
)
