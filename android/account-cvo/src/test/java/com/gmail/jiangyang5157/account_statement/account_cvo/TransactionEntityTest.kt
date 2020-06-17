package com.gmail.jiangyang5157.account_statement.account_cvo

import com.gmail.jiangyang5157.core.ext.fromJson
import com.gmail.jiangyang5157.kotlin_kit.data.model.finance.Money
import com.google.gson.GsonBuilder
import org.junit.Assert
import org.junit.Test
import java.util.*

class TransactionEntityTest {

    @Test
    fun test_json_serializer_common() {
        val transaction =
            TransactionEntity(
                accountName = "FakeAccount",
                date = Date(1592391430000),
                money = Money(-10.99),
                description = "desc"
            )
        val json = """
{
  "accountName": "FakeAccount",
  "date": "Jun 17, 2020 10:57:10 PM",
  "money": -10.99,
  "description": "desc"
}
            """.trimIndent()
        Assert.assertEquals(
            json, GsonBuilder().setPrettyPrinting().create().toJson(transaction)
        )
    }

    @Test
    fun test_json_serializer_round_4() {
        val transaction =
            TransactionEntity(
                accountName = "FakeAccount",
                date = Date(1592391430000),
                money = Money(-10.994),
                description = "desc"
            )
        val json = """
{
  "accountName": "FakeAccount",
  "date": "Jun 17, 2020 10:57:10 PM",
  "money": -10.99,
  "description": "desc"
}
            """.trimIndent()
        Assert.assertEquals(
            json, GsonBuilder().setPrettyPrinting().create().toJson(transaction)
        )
    }

    @Test
    fun test_json_serializer_round_5() {
        val transaction =
            TransactionEntity(
                accountName = "FakeAccount",
                date = Date(1592391430000),
                money = Money(-10.995),
                description = "desc"
            )
        val json = """
{
  "accountName": "FakeAccount",
  "date": "Jun 17, 2020 10:57:10 PM",
  "money": -11.0,
  "description": "desc"
}
            """.trimIndent()
        Assert.assertEquals(
            json, GsonBuilder().setPrettyPrinting().create().toJson(transaction)
        )
    }

    @Test
    fun test_json_deserialize_common() {
        val transaction =
            TransactionEntity(
                accountName = "FakeAccount",
                date = Date(1592391430000),
                money = Money(-10.99),
                description = "desc"
            )
        val json = """
{
  "accountName": "FakeAccount",
  "date": "Jun 17, 2020 10:57:10 PM",
  "money": -10.99,
  "description": "desc"
}
            """.trimIndent()
        Assert.assertEquals(
            transaction,
            GsonBuilder().setPrettyPrinting().create().fromJson<TransactionEntity>(json)
        )
    }

    @Test
    fun test_json_deserialize_round_4() {
        val transaction =
            TransactionEntity(
                accountName = "FakeAccount",
                date = Date(1592391430000),
                money = Money(-10.99),
                description = "desc"
            )
        val json = """
{
  "accountName": "FakeAccount",
  "date": "Jun 17, 2020 10:57:10 PM",
  "money": -10.994,
  "description": "desc"
}
            """.trimIndent()
        Assert.assertEquals(
            transaction,
            GsonBuilder().setPrettyPrinting().create().fromJson<TransactionEntity>(json)
        )
    }

    @Test
    fun test_json_deserialize_round_5() {
        val transaction =
            TransactionEntity(
                accountName = "FakeAccount",
                date = Date(1592391430000),
                money = Money(-11.0),
                description = "desc"
            )
        val json = """
{
  "accountName": "FakeAccount",
  "date": "Jun 17, 2020 10:57:10 PM",
  "money": -10.995,
  "description": "desc"
}
            """.trimIndent()
        Assert.assertEquals(
            transaction,
            GsonBuilder().setPrettyPrinting().create().fromJson<TransactionEntity>(json)
        )
    }
}
