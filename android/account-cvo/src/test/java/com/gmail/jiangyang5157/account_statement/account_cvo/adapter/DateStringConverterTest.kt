package com.gmail.jiangyang5157.account_statement.account_cvo.adapter

import com.gmail.jiangyang5157.kotlin_kit.utils.RegexUtils
import org.junit.Assert
import org.junit.Test
import java.util.*

class DateStringConverterTest {

    @Test
    fun test_backward_common() {
        val calendar = Calendar.getInstance()
        calendar.time = DateStringConverter(RegexUtils.DATE_DMY).backward("18/06/2020")!!
        Assert.assertEquals(2020, calendar.get(Calendar.YEAR))
        Assert.assertEquals(6, calendar.get(Calendar.MONTH) + 1)
        Assert.assertEquals(18, calendar.get(Calendar.DAY_OF_MONTH))
    }

    @Test
    fun test_forward_common() {
        val calendar = Calendar.getInstance()
        val todayDayString = "${calendar.get(Calendar.DAY_OF_MONTH)}"
        val todayMonthInt = calendar.get(Calendar.MONTH) + 1
        val todayMonthString = if (todayMonthInt > 9) "$todayMonthInt" else "0$todayMonthInt"
        val todayYearString = "${calendar.get(Calendar.YEAR)}"
        val todayString = "$todayDayString/$todayMonthString/$todayYearString"
        Assert.assertEquals(todayString, DateStringConverter(RegexUtils.DATE_DMY).forward(Date()))
    }

    @Test
    fun test_backward_trimmed() {
        val calendar = Calendar.getInstance()
        calendar.time =
            DateStringConverter(RegexUtils.DATE_DMY).backward("\n\t\n 18/06/2020 \t\n\t")!!
        Assert.assertEquals(2020, calendar.get(Calendar.YEAR))
        Assert.assertEquals(6, calendar.get(Calendar.MONTH) + 1)
        Assert.assertEquals(18, calendar.get(Calendar.DAY_OF_MONTH))
    }

    @Test
    fun test_backward_invalid() {
        val invalid = listOf(
            null,
            "",
            "a/s/d"
        )

        invalid.forEach {
            Assert.assertNull(DateStringConverter(RegexUtils.DATE_DMY).backward(it))
        }
    }

    @Test
    fun test_forward_invalid() {
        Assert.assertNull(DateStringConverter(RegexUtils.DATE_DMY).forward(null))
    }
}
