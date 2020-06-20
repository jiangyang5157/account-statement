package com.gmail.jiangyang5157.account_statement.bank_domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(
    tableName = "accounts",
    primaryKeys = ["name"],
    indices = [Index(value = ["name"], unique = true)]
)
data class AccountEntity(

    @ColumnInfo(name = "name")
    @field:SerializedName("name")
    val name: String,

    @ColumnInfo(name = "lastModifiedDate")
    @field:SerializedName("lastModifiedDate")
    val lastModifiedDate: Date
)
