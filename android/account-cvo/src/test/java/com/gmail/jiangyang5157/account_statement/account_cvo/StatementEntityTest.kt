//package com.gmail.jiangyang5157.account_statement.account_cvo
//
//import com.gmail.jiangyang5157.core.ext.fromJson
//import com.gmail.jiangyang5157.kotlin_kit.data.adapter.DateStringConverter
//import com.gmail.jiangyang5157.kotlin_kit.data.adapter.MoneyStringConverter
//import com.gmail.jiangyang5157.kotlin_kit.utils.RegexUtils
//import com.google.gson.GsonBuilder
//import org.junit.Assert
//import org.junit.Test
//
//class StatementEntityTest {
//
//    @Test
//    fun test_json_serializer() {
//        val statement =
//            StatementEntity(
//                account = AccountEntity(
//                    "FakeAccount",
//                    DateStringConverter(RegexUtils.DATE_HTTP_EDMYHMSZ).backward("Jun 17, 2020 10:57:10 PM")!!
//                ),
//                transactions = listOf(
//                    TransactionEntity(
//                        accountName = "FakeAccount",
//                        date = Date(1592391430000),
//                        money = MoneyStringConverter().backward("-10")!!,
//                        description = "desc"
//                    ),
//                    TransactionEntity(
//                        accountName = "FakeAccount",
//                        date = Date(1592391430000),
//                        money = MoneyStringConverter().backward("10")!!,
//                        description = "desc"
//                    )
//                )
//            )
//        val json = """
//{
//  "account": {
//    "name": "FakeAccount",
//    "lastModifiedDate": "Jun 17, 2020 10:57:10 PM"
//  },
//  "transactions": [
//    {
//      "accountName": "FakeAccount",
//      "date": "01/12/2019",
//      "money": "-10.00",
//      "description": "desc"
//    },
//    {
//      "accountName": "FakeAccount",
//      "date": "01/12/2019",
//      "money": "10.00",
//      "description": "desc"
//    }
//  ]
//}
//            """.trimIndent()
//        Assert.assertEquals(
//            json, GsonBuilder().setPrettyPrinting().create().toJson(statement)
//        )
//    }
//
//    @Test
//    fun test_json_deserialize() {
//        val statement =
//            StatementEntity(
//                account = AccountEntity(
//                    "FakeAccount",
//                    DateStringConverter(RegexUtils.DATE_HTTP_EDMYHMSZ).backward("Jun 17, 2020 10:57:10 PM")!!
//                ),
//                transactions = listOf(
//                    TransactionEntity(
//                        accountName = "FakeAccount",
//                        date = Date(1592391430000),
//                        money = MoneyStringConverter().backward("-10")!!,
//                        description = "desc"
//                    ),
//                    TransactionEntity(
//                        accountName = "FakeAccount",
//                        date = Date(1592391430000),
//                        money = MoneyStringConverter().backward("10")!!,
//                        description = "desc"
//                    )
//                )
//            )
//        val json = """
//{
//  "account": {
//    "name": "FakeAccount",
//    "lastModifiedDate": "Jun 17, 2020 10:57:10 PM"
//  },
//  "transactions": [
//    {
//      "accountName": "FakeAccount",
//      "date": "01/12/2019",
//      "money": "-10.00",
//      "description": "desc"
//    },
//    {
//      "accountName": "FakeAccount",
//      "date": "01/12/2019",
//      "money": "10.00",
//      "description": "desc"
//    }
//  ]
//}
//            """.trimIndent()
//        Assert.assertEquals(
//            statement, GsonBuilder().setPrettyPrinting().create().fromJson<StatementEntity>(json)
//        )
//    }
//}
//
