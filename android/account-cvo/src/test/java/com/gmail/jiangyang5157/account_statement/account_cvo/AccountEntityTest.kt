package com.gmail.jiangyang5157.account_statement.account_cvo

import com.gmail.jiangyang5157.core.ext.fromJson
import com.google.gson.GsonBuilder
import org.junit.Assert
import org.junit.Test
import java.util.*

class AccountEntityTest {

    @Test
    fun test_json_serializer() {
        val account =
            AccountEntity(
                "FakeAccount",
                Date(1592391430000) // "Jun 17, 2020 10:57:10 PM"
            )
        val json = """
{
  "name": "FakeAccount",
  "lastModifiedDate": "Jun 17, 2020 10:57:10 PM"
}
            """.trimIndent()
        Assert.assertEquals(
            json, GsonBuilder().setPrettyPrinting().create().toJson(account)
        )
    }

    @Test
    fun test_json_deserialize() {
        val account =
            AccountEntity(
                "FakeAccount",
                Date(1592391430000)
            )
        val json = """
{
  "name": "FakeAccount",
  "lastModifiedDate": "Jun 17, 2020 10:57:10 PM"
}
            """.trimIndent()
        Assert.assertEquals(
            account, GsonBuilder().setPrettyPrinting().create().fromJson<AccountEntity>(json)
        )
    }
}
