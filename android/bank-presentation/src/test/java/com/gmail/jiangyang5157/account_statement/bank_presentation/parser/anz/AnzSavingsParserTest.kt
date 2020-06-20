package com.gmail.jiangyang5157.account_statement.bank_presentation.parser.anz

import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.ByteArrayInputStream
import java.util.*

class AnzSavingsParserTest {

    @Test
    fun test_withHeaderLine() {
        val statementString =
            "Type,Details,Particulars,Code,Reference,Amount,Date,ForeignCurrencyAmount,ConversionCharge\n" +
                "Bank Fee,Monthly A/C Fee,,,,-12.50,30/11/2018,,\n" +
                "Eft-Pos,Tai Ping Trading,4055********,5758   C,181127124600,-6.07,27/11/2018,,\n" +
                "Eft-Pos,Smart Kitchen,4055********,5758   C,181124171315,-20.00,26/11/2018,,\n" +
                "Eft-Pos,Nz Nutrifood,4055********,5758   C,181126180731,-16.00,26/11/2018,,\n" +
                "Eft-Pos,Ziyi Food,4055********,5758   C,181124102449,-10.50,26/11/2018,,\n" +
                "Payment,Aklcouncil,058748Dpsa2A,Aklcouncil,12342091634,-554.79,26/11/2018,,\n" +
                "Atm Debit,Anz  S3A7132 Albany Mall,Anz  S3A7132,Albany Mall,Branc 154108,-230.00,26/11/2018,,\n" +
                "Eft-Pos,Smart Kitchen,4055********,5758   C,181123184700,-28.80,23/11/2018,,\n" +
                "Eft-Pos,Tai Ping Albany,4055********,5758   C,181121183539,-40.80,22/11/2018,,\n" +
                "Loan Payment,To: 88532640-1002,,,      004600,-1419.32,21/11/2018,,\n" +
                "Loan Payment,To: 88532640-1001,,,      004600,-1385.92,21/11/2018,,\n" +
                "Transfer,01-1842-0216128-46,Credit,Transfer,151641,1500.05,20/11/2018,,\n" +
                "Direct Debit,Dpl Insurance,222717-1,Pmv,222717-1,-69.21,19/11/2018,,\n" +
                "Eft-Pos,Tai Ping Albany,4055********,5758   C,181117115924,-50.12,19/11/2018,,\n" +
                "Eft-Pos,Fishland Seafood Lim,4055********,5758   C,181117114903,-8.82,19/11/2018,,\n" +
                "Automatic Payment,Serious Saver,,,,-20.00,16/11/2018,,\n" +
                "Transfer,9554-****-****-7240,Debit,Transfer,172311,-1000.00,16/11/2018,,\n" +
                "Salary,Ipayroll Limite,Fiserv,,Ipayroll,5188.44,16/11/2018,,\n" +
                "Eft-Pos,Dz Company Limited,4055********,5758   C,181115183757,-32.60,15/11/2018,,\n" +
                "Eft-Pos,Nz Nutrifood,4055********,5758   C,181110162254,-8.00,12/11/2018,,\n" +
                "Eft-Pos,Tai Ping Albany,4055********,5758   C,181105184146,-44.07,06/11/2018,,\n" +
                "Payment,Youyou Limited,,,For Owen,-456.00,06/11/2018,,\n" +
                "Atm Debit,Anz  S3A1450 Mt Eden Vill,Anz  S3A1450,Mt Eden Vill,Age B 121928,-500.00,05/11/2018,,\n" +
                "Direct Debit,Northland Waste Ltd,Bin Charge,Hb120,      221141,-17.50,02/11/2018,,\n" +
                "Eft-Pos,Chamate Limited,4055********,5758   C,181102130630,-13.80,02/11/2018,,\n" +
                "Direct Debit,Southern Cross Healt,,000014910576, 23185996,-26.08,01/11/2018,,\n" +
                "Direct Debit,Southern Cross Healt,,000014910576, 23185998,-1.95,01/11/2018,,\n"

        val transactions =
            AnzSavingsParser().parse(ByteArrayInputStream(statementString.toByteArray(Charsets.UTF_8)))
        val calendar = Calendar.getInstance()

        assertEquals(27, transactions!!.size)

        assertEquals("Bank Fee, Monthly A/C Fee", transactions[0].description)
        assertEquals("-12.50", transactions[0].money.value!!.amount.toString())
        calendar.time = transactions[0].date.value!!
        assertEquals(2018, calendar.get(Calendar.YEAR))
        assertEquals(11, calendar.get(Calendar.MONTH) + 1)
        assertEquals(30, calendar.get(Calendar.DAY_OF_MONTH))

        assertEquals("Direct Debit, Southern Cross Healt", transactions[26].description)
        assertEquals("-1.95", transactions[26].money.value!!.amount.toString())
        calendar.time = transactions[26].date.value!!
        assertEquals(2018, calendar.get(Calendar.YEAR))
        assertEquals(11, calendar.get(Calendar.MONTH) + 1)
        assertEquals(1, calendar.get(Calendar.DAY_OF_MONTH))
    }

    @Test
    fun test_withoutHeaderLine() {
        val statementString =
            "Eft-Pos,Tai Ping Trading,4055********,5758   C,181127124600,-6.07,27/11/2018,,\n" +
                "Eft-Pos,Smart Kitchen,4055********,5758   C,181124171315,-20.00,26/11/2018,,\n"

        val transactions =
            AnzSavingsParser().parse(ByteArrayInputStream(statementString.toByteArray(Charsets.UTF_8)))
        val calendar = Calendar.getInstance()

        assertEquals(2, transactions!!.size)

        assertEquals("Eft-Pos, Tai Ping Trading", transactions[0].description)
        assertEquals("-6.07", transactions[0].money.value!!.amount.toString())
        calendar.time = transactions[0].date.value!!
        assertEquals(2018, calendar.get(Calendar.YEAR))
        assertEquals(11, calendar.get(Calendar.MONTH) + 1)
        assertEquals(27, calendar.get(Calendar.DAY_OF_MONTH))

        assertEquals("Eft-Pos, Smart Kitchen", transactions[1].description)
        assertEquals("-20.00", transactions[1].money.value!!.amount.toString())
        calendar.time = transactions[1].date.value!!
        assertEquals(2018, calendar.get(Calendar.YEAR))
        assertEquals(11, calendar.get(Calendar.MONTH) + 1)
        assertEquals(26, calendar.get(Calendar.DAY_OF_MONTH))
    }

    @Test
    fun test_empty() {
        assertEquals(
            0,
            AnzSavingsParser().parse(
                ByteArrayInputStream(
                    "".toByteArray(Charsets.UTF_8)
                )
            )?.size
        )

        assertEquals(
            0,
            AnzSavingsParser().parse(
                ByteArrayInputStream(
                    "\n".toByteArray(Charsets.UTF_8)
                )
            )?.size
        )
    }

    @Test
    fun test_invalid() {
        assertEquals(
            0,
            AnzSavingsParser().parse(
                ByteArrayInputStream(
                    ",Smart Kitchen,,,,-20.00,26/11/,,\n".toByteArray(Charsets.UTF_8)
                )
            )?.size
        )
    }
}
