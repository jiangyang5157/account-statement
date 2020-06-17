package com.gmail.jiangyang5157.account_statement.account_cvo

import com.gmail.jiangyang5157.account_statement.account_cvo.adapter.DateStringConverter
import com.gmail.jiangyang5157.core.ext.fromJson
import com.gmail.jiangyang5157.kotlin_kit.utils.RegexUtils
import com.google.gson.GsonBuilder
import org.junit.Assert
import org.junit.Test

class AccountEntityTest {

    @Test
    fun test_json_serializer() {
        val account =
            AccountEntity(
                "FakeAccount",
                DateStringConverter(RegexUtils.DATE_MDYHMS).backward("Nov 15, 1994 12:45:26")!!
            )
        val json = """
{
  "name": "FakeAccount",
  "lastModifiedDate": "Nov 15, 1994 12:45:26"
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
                DateStringConverter(RegexUtils.DATE_MDYHMS).backward("Nov 15, 1994 12:45:26")!!
            )
        val json = """
{
  "name": "FakeAccount",
  "lastModifiedDate": "Nov 15, 1994 12:45:26"
}
            """.trimIndent()
        Assert.assertEquals(
            account, GsonBuilder().setPrettyPrinting().create().fromJson<AccountEntity>(json)
        )
    }
}
