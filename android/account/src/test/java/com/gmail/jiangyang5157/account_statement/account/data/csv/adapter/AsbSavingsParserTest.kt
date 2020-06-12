package com.gmail.jiangyang5157.account_statement.account.data.csv.adapter

import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.ByteArrayInputStream
import java.util.*

class AsbSavingsParserTest {

    @Test
    fun test_withHeaderLine() {
        val statementString =
            "Date,Unique Id,Tran Type,Cheque Number,Payee,Memo,Amount\n" +
                "2020/05/15,2020051501,DEBIT,,\"DEBIT\",\"CARD 8117 ZHA ZHA TEA  AUCKLAND\",-9.00\n" +
                "2020/05/15,2020051502,TFR IN,,\"MB TRANSFER\",\"EX 12-3059- 0004114-00\",1000.00\n" +
                "2020/05/15,2020051503,D/C,,\"D/C FROM Jay J\",\"Jay\",1000.00\n"

        val transactions =
            AsbSavingsParser().map(ByteArrayInputStream(statementString.toByteArray(Charsets.UTF_8)))
        val calendar = Calendar.getInstance()

        assertEquals(3, transactions.size)

        assertEquals("\"CARD 8117 ZHA ZHA TEA  AUCKLAND\"", transactions[0].description)
        assertEquals("-9.00", transactions[0].money.value!!.amount.toString())
        calendar.time = transactions[0].date.value!!
        assertEquals(2020, calendar.get(Calendar.YEAR))
        assertEquals(5, calendar.get(Calendar.MONTH) + 1)
        assertEquals(15, calendar.get(Calendar.DAY_OF_MONTH))

        assertEquals("\"EX 12-3059- 0004114-00\"", transactions[1].description)
        assertEquals("1000.00", transactions[1].money.value!!.amount.toString())
        calendar.time = transactions[1].date.value!!
        assertEquals(2020, calendar.get(Calendar.YEAR))
        assertEquals(5, calendar.get(Calendar.MONTH) + 1)
        assertEquals(15, calendar.get(Calendar.DAY_OF_MONTH))
    }

    @Test
    fun test_withoutHeaderLine() {
        val statementString =
            "2020/05/15,2020051501,DEBIT,,\"DEBIT\",\"CARD 8117 ZHA ZHA TEA  AUCKLAND\",-9.00\n" +
                "2020/05/15,2020051502,TFR IN,,\"MB TRANSFER\",\"EX 12-3059- 0004114-00\",1000.00\n"

        val transactions =
            AsbSavingsParser().map(ByteArrayInputStream(statementString.toByteArray(Charsets.UTF_8)))
        val calendar = Calendar.getInstance()

        assertEquals(2, transactions.size)

        assertEquals("\"CARD 8117 ZHA ZHA TEA  AUCKLAND\"", transactions[0].description)
        assertEquals("-9.00", transactions[0].money.value!!.amount.toString())
        calendar.time = transactions[0].date.value!!
        assertEquals(2020, calendar.get(Calendar.YEAR))
        assertEquals(5, calendar.get(Calendar.MONTH) + 1)
        assertEquals(15, calendar.get(Calendar.DAY_OF_MONTH))

        assertEquals("\"EX 12-3059- 0004114-00\"", transactions[1].description)
        assertEquals("1000.00", transactions[1].money.value!!.amount.toString())
        calendar.time = transactions[1].date.value!!
        assertEquals(2020, calendar.get(Calendar.YEAR))
        assertEquals(5, calendar.get(Calendar.MONTH) + 1)
        assertEquals(15, calendar.get(Calendar.DAY_OF_MONTH))
    }

    @Test
    fun test_empty() {
        assertEquals(
            0,
            AsbSavingsParser().map(
                ByteArrayInputStream(
                    "".toByteArray(Charsets.UTF_8)
                )
            ).size
        )

        assertEquals(
            0,
            AsbSavingsParser().map(
                ByteArrayInputStream(
                    "\n".toByteArray(Charsets.UTF_8)
                )
            ).size
        )
    }

    @Test
    fun test_invalid() {
        assertEquals(
            0,
            AsbSavingsParser().map(
                ByteArrayInputStream(
                    ",Smart Kitchen,,,,-20.00,26/11/,,\n".toByteArray(Charsets.UTF_8)
                )
            ).size
        )
    }
}
