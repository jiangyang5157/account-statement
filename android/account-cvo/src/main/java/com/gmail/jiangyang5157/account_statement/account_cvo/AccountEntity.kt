package com.gmail.jiangyang5157.account_statement.account_cvo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import com.gmail.jiangyang5157.kotlin_kit.utils.RegexUtils
import com.google.gson.annotations.JsonAdapter
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
    @JsonAdapter(value = LastModifiedDateJsonAdapter::class)
    val lastModifiedDate: Date
) {
    inner class LastModifiedDateJsonAdapter :
        com.gmail.jiangyang5157.account_statement.account_cvo.adapter.DateJsonSerializer() {
        override val pattern: String
            get() = RegexUtils.DATE_MDYHMS
    }
}
