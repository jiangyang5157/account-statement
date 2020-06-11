package com.gmail.jiangyang5157.account_statement.account.domain.entity

import com.gmail.jiangyang5157.account_statement.account.domain.adapter.DateStringConverter
import com.gmail.jiangyang5157.account_statement.account.domain.adapter.MoneyStringConverter
import com.gmail.jiangyang5157.core.ext.fromJson
import com.google.gson.GsonBuilder
import org.junit.Assert
import org.junit.Test

class StatementEntityTest {

    @Test
    fun test_json_serializer() {
        val statement = StatementEntity(
            account = AccountEntity("FakeAccount"),
            transactions = listOf(
                TransactionEntity(
                    accountName = "FakeAccount",
                    date = DateStringConverter.stringToDate("1/12/2019")!!,
                    money = MoneyStringConverter.stringToMoney("-10")!!,
                    description = "desc"
                ),
                TransactionEntity(
                    accountName = "FakeAccount",
                    date = DateStringConverter.stringToDate("1/12/2019")!!,
                    money = MoneyStringConverter.stringToMoney("10")!!,
                    description = "desc"
                )
            )
        )
        val json = """
{
  "account": {
    "name": "FakeAccount"
  },
  "transactions": [
    {
      "accountName": "FakeAccount",
      "date": "01/12/2019",
      "money": "-10.00",
      "description": "desc"
    },
    {
      "accountName": "FakeAccount",
      "date": "01/12/2019",
      "money": "10.00",
      "description": "desc"
    }
  ]
}
            """.trimIndent()
        Assert.assertEquals(
            json, GsonBuilder().setPrettyPrinting().create().toJson(statement)
        )
    }

    @Test
    fun test_json_deserialize() {
        val statement = StatementEntity(
            account = AccountEntity("FakeAccount"),
            transactions = listOf(
                TransactionEntity(
                    accountName = "FakeAccount",
                    date = DateStringConverter.stringToDate("1/12/2019")!!,
                    money = MoneyStringConverter.stringToMoney("-10")!!,
                    description = "desc"
                ),
                TransactionEntity(
                    accountName = "FakeAccount",
                    date = DateStringConverter.stringToDate("1/12/2019")!!,
                    money = MoneyStringConverter.stringToMoney("10")!!,
                    description = "desc"
                )
            )
        )
        val json = """
{
  "account": {
    "name": "FakeAccount"
  },
  "transactions": [
    {
      "accountName": "FakeAccount",
      "date": "01/12/2019",
      "money": "-10.00",
      "description": "desc"
    },
    {
      "accountName": "FakeAccount",
      "date": "01/12/2019",
      "money": "10.00",
      "description": "desc"
    }
  ]
}
            """.trimIndent()
        Assert.assertEquals(
            statement, GsonBuilder().setPrettyPrinting().create().fromJson<StatementEntity>(json)
        )
    }
}

