package com.gmail.jiangyang5157.account_statement.account_cvo.adapter

import com.gmail.jiangyang5157.kotlin_kit.data.model.finance.Money
import org.junit.Assert
import org.junit.Test

class MoneyStringConverterTest {

    @Test
    fun test_backward_common() {
        Assert.assertEquals("-1.00", MoneyStringConverter().backward("-1")?.amount.toString())
        Assert.assertEquals("-0.99", MoneyStringConverter().backward("-.99")?.amount.toString())
    }

    @Test
    fun test_forward_common() {
        val money = Money(-1.0)
        Assert.assertEquals("-1.00", MoneyStringConverter().forward(money))
    }

    @Test
    fun test_backward_trimmed() {
        Assert.assertEquals(
            "-1.00",
            MoneyStringConverter().backward("\n\t\n -1 \t\n\t")?.amount.toString()
        )
    }

    @Test
    fun test_backward_round() {
        Assert.assertEquals(
            "-2.00",
            MoneyStringConverter().backward("-1.999")?.amount.toString()
        )
    }

    @Test
    fun test_backward_invalid() {
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
            Assert.assertNull(MoneyStringConverter().backward(it))
        }
    }

    @Test
    fun test_forward_invalid() {
        Assert.assertNull(MoneyStringConverter().forward(null))
    }
}
