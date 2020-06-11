package com.gmail.jiangyang5157.account_statement.account.domain.entity

import com.gmail.jiangyang5157.account_statement.account.domain.adapter.DateStringConverter
import com.gmail.jiangyang5157.account_statement.account.domain.adapter.MoneyStringConverter
import com.gmail.jiangyang5157.core.ext.fromJson
import com.google.gson.GsonBuilder
import org.junit.Assert
import org.junit.Test

class TransactionEntityTest {

    @Test
    fun test_json_serializer() {
        val transaction = TransactionEntity(
            accountName = "FakeAccount",
            date = DateStringConverter.stringToDate("1/12/2019")!!,
            money = MoneyStringConverter.stringToMoney("-10")!!,
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
        val transaction = TransactionEntity(
            accountName = "FakeAccount",
            date = DateStringConverter.stringToDate("1/12/2019")!!,
            money = MoneyStringConverter.stringToMoney("-10")!!,
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
            transaction, GsonBuilder().setPrettyPrinting().create().fromJson<TransactionEntity>(json)
        )
    }
}

