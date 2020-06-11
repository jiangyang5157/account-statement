package com.gmail.jiangyang5157.account_statement.account.domain.entity

import com.gmail.jiangyang5157.core.ext.fromJson
import com.google.gson.GsonBuilder
import org.junit.Assert
import org.junit.Test

class AccountEntityTest {

    @Test
    fun test_json_serializer() {
        val account = AccountEntity("FakeAccount")
        val json = """
{
  "name": "FakeAccount"
}
            """.trimIndent()
        Assert.assertEquals(
            json, GsonBuilder().setPrettyPrinting().create().toJson(account)
        )
    }

    @Test
    fun test_json_deserialize() {
        val account = AccountEntity("FakeAccount")
        val json = """
{
  "name": "FakeAccount"
}
            """.trimIndent()
        Assert.assertEquals(
            account, GsonBuilder().setPrettyPrinting().create().fromJson<AccountEntity>(json)
        )
    }
}
