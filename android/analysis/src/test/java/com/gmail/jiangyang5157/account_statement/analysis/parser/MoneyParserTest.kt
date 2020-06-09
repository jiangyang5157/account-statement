package com.gmail.jiangyang5157.account_statement.analysis.parser

import org.junit.Assert.*
import org.junit.Test

class MoneyParserTest {

    @Test
    fun test_common() {
        assertEquals("-1.00", MoneyParser.map("-1").amount.toString())
        assertEquals("-0.99", MoneyParser.map("-.99").amount.toString())
    }

    @Test
    fun test_trimmed() {
        assertEquals("-1.00", MoneyParser.map("\n\t\n -1 \t\n\t").amount.toString())
    }

    @Test
    fun test_round() {
        assertEquals("-2.00", MoneyParser.map("-1.999").amount.toString())
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
            try {
                MoneyParser.map(it)
                fail()
            } catch (e: IllegalArgumentException) {
            }
        }
    }
}
