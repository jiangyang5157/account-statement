package com.gmail.jiangyang5157.account_statement.account.domain.adapter

import com.gmail.jiangyang5157.kotlin_kit.utils.RegexUtils
import org.junit.Assert
import org.junit.Test
import java.util.*

class DateStringConverterTest {

    @Test
    fun test_common() {
        val calendar = Calendar.getInstance()
        calendar.time = DateStringConverter.stringToDate("18/06/2020", RegexUtils.DATE_DMY)!!
        Assert.assertEquals(2020, calendar.get(Calendar.YEAR))
        Assert.assertEquals(6, calendar.get(Calendar.MONTH) + 1)
        Assert.assertEquals(18, calendar.get(Calendar.DAY_OF_MONTH))
    }

    @Test
    fun test_trimmed() {
        val calendar = Calendar.getInstance()
        calendar.time =
            DateStringConverter.stringToDate("\n\t\n 18/06/2020 \t\n\t", RegexUtils.DATE_DMY)!!
        Assert.assertEquals(2020, calendar.get(Calendar.YEAR))
        Assert.assertEquals(6, calendar.get(Calendar.MONTH) + 1)
        Assert.assertEquals(18, calendar.get(Calendar.DAY_OF_MONTH))
    }

    @Test
    fun test_invalid() {
        val invalid = listOf(
            "",
            "a/s/d"
        )

        invalid.forEach {
            Assert.assertNull(DateStringConverter.stringToDate(it, RegexUtils.DATE_DMY))
        }
    }
}
