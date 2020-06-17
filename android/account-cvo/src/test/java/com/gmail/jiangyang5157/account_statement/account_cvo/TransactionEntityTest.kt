package com.gmail.jiangyang5157.account_statement.account_cvo

import com.gmail.jiangyang5157.account_statement.account_cvo.adapter.DateStringConverter
import com.gmail.jiangyang5157.account_statement.account_cvo.adapter.MoneyStringConverter
import com.gmail.jiangyang5157.core.ext.fromJson
import com.gmail.jiangyang5157.kotlin_kit.utils.RegexUtils
import com.google.gson.GsonBuilder
import org.junit.Assert
import org.junit.Test

class TransactionEntityTest {

    @Test
    fun test_json_serializer() {
        val transaction =
            TransactionEntity(
                accountName = "FakeAccount",
                date = DateStringConverter(RegexUtils.DATE_DMY).backward("1/12/2019")!!,
                money = MoneyStringConverter().backward("-10")!!,
                description = "desc"
            )
        val json = """
{
  "accountName": "FakeAccount",
  "date": "01/12/2019",
  "money": "-10.00",
  "description": "desc"
}
            """.trimIndent()
        Assert.assertEquals(
            json, GsonBuilder().setPrettyPrinting().create().toJson(transaction)
        )
    }

    @Test
    fun test_json_deserialize() {
        val transaction =
            TransactionEntity(
                accountName = "FakeAccount",
                date = DateStringConverter(RegexUtils.DATE_DMY).backward("1/12/2019")!!,
                money = MoneyStringConverter().backward("-10")!!,
                description = "desc"
            )
        val json = """
{
  "accountName": "FakeAccount",
  "date": "01/12/2019",
  "money": "-10.00",
  "description": "desc"
}
            """.trimIndent()
        Assert.assertEquals(
            transaction,
            GsonBuilder().setPrettyPrinting().create().fromJson<TransactionEntity>(json)
        )
    }
}

