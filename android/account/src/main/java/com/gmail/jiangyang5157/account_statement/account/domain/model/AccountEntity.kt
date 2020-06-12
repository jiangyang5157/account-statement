package com.gmail.jiangyang5157.account_statement.account.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "accounts",
    primaryKeys = ["name"],
    indices = [Index(value = ["name"], unique = true)]
)
data class AccountEntity(

    @ColumnInfo(name = "name")
    @field:SerializedName("name")
    val name: String
)
