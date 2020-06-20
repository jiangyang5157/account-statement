package com.gmail.jiangyang5157.account_statement.bank_domain.entity

import com.gmail.jiangyang5157.core.ext.fromJson
import com.gmail.jiangyang5157.kotlin_kit.data.model.finance.Money
import com.google.gson.GsonBuilder
import org.junit.Assert
import org.junit.Test
import java.util.*

class StatementEntityTest {

    @Test
    fun test_json_serializer() {
        val statement =
            StatementEntity(
                account = AccountEntity(
                    "FakeAccount",
                    Date(1592391430000) // "Jun 17, 2020 10:57:10 PM"
                ),
                transactions = listOf(
                    TransactionEntity(
                        accountName = "FakeAccount",
                        date = Date(1592391430000),
                        money = Money(-10.99),
                        description = "desc"
                    ),
                    TransactionEntity(
                        accountName = "FakeAccount",
                        date = Date(1592391430000),
                        money = Money(10.99),
                        description = "desc"
                    )
                )
            )
        val json = """
{
  "account": {
    "name": "FakeAccount",
    "lastModifiedDate": "Jun 17, 2020 10:57:10 PM"
  },
  "transactions": [
    {
      "accountName": "FakeAccount",
      "date": "Jun 17, 2020 10:57:10 PM",
      "money": -10.99,
      "description": "desc"
    },
    {
      "accountName": "FakeAccount",
      "date": "Jun 17, 2020 10:57:10 PM",
      "money": 10.99,
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
        val statement =
            StatementEntity(
                account = AccountEntity(
                    "FakeAccount",
                    Date(1592391430000) // "Jun 17, 2020 10:57:10 PM"
                ),
                transactions = listOf(
                    TransactionEntity(
                        accountName = "FakeAccount",
                        date = Date(1592391430000),
                        money = Money(-10.99),
                        description = "desc"
                    ),
                    TransactionEntity(
                        accountName = "FakeAccount",
                        date = Date(1592391430000),
                        money = Money(10.99),
                        description = "desc"
                    )
                )
            )
        val json = """
{
  "account": {
    "name": "FakeAccount",
    "lastModifiedDate": "Jun 17, 2020 10:57:10 PM"
  },
  "transactions": [
    {
      "accountName": "FakeAccount",
      "date": "Jun 17, 2020 10:57:10 PM",
      "money": -10.99,
      "description": "desc"
    },
    {
      "accountName": "FakeAccount",
      "date": "Jun 17, 2020 10:57:10 PM",
      "money": 10.99,
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
