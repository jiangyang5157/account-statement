package com.gmail.jiangyang5157.account_statement.analysis.parser

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class DateParserTest {

    @Test
    fun test_common() {
        val calendar = Calendar.getInstance()
        calendar.time = DateParser.map("18/06/2020")
        assertEquals(2020, calendar.get(Calendar.YEAR))
        assertEquals(6, calendar.get(Calendar.MONTH) + 1)
        assertEquals(18, calendar.get(Calendar.DAY_OF_MONTH))
    }

    @Test
    fun test_trimmed() {
        val calendar = Calendar.getInstance()
        calendar.time = DateParser.map("\n\t\n 18/06/2020 \t\n\t")
        assertEquals(2020, calendar.get(Calendar.YEAR))
        assertEquals(6, calendar.get(Calendar.MONTH) + 1)
        assertEquals(18, calendar.get(Calendar.DAY_OF_MONTH))
    }

    @Test
    fun test_invalid() {
        val invalid = listOf(
            "",
            "a/s/d"
        )

        invalid.forEach {
            try {
                DateParser.map(it)
                fail()
            } catch (e: IllegalArgumentException) {
            }
        }
    }
}
