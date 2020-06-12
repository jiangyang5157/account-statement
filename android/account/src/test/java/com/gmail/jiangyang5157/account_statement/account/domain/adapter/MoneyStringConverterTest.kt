package com.gmail.jiangyang5157.account_statement.account.domain.adapter

import org.junit.Assert
import org.junit.Test

class MoneyStringConverterTest {

    @Test
    fun test_common() {
        Assert.assertEquals("-1.00", MoneyStringConverter.stringToMoney("-1")?.amount.toString())
        Assert.assertEquals("-0.99", MoneyStringConverter.stringToMoney("-.99")?.amount.toString())
    }

    @Test
    fun test_trimmed() {
        Assert.assertEquals(
            "-1.00",
            MoneyStringConverter.stringToMoney("\n\t\n -1 \t\n\t")?.amount.toString()
        )
    }

    @Test
    fun test_round() {
        Assert.assertEquals(
            "-2.00",
            MoneyStringConverter.stringToMoney("-1.999")?.amount.toString()
        )
    }

    @Test
    fun test_invalid() {
        val invalid = listOf(
            "",
            "a",
            ".",
            "-",
            "+",
            "1.1.",
            ".1.1",
            "--1",
            "1..",
            "..1",
            "",
            "1.."
        )

        invalid.forEach {
            Assert.assertNull(MoneyStringConverter.stringToMoney(it))
        }
    }
}
