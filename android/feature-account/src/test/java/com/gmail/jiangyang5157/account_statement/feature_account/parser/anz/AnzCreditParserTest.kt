package com.gmail.jiangyang5157.account_statement.feature_account.parser.anz

import org.junit.Assert
import org.junit.Test
import java.io.ByteArrayInputStream
import java.util.*

class AnzCreditParserTest {

    @Test
    fun test_withHeaderLine() {
        val statementString =
            "Card,Type,Amount,Details,TransactionDate,ProcessedDate,ForeignCurrencyAmount,ConversionCharge\n" +
                "4055-****-****-5758,D,23.49,Countdown Silverdale   Silverdale    Nzl ,01/12/2018,03/12/2018,,\n" +
                "4055-****-****-5758,D,345.78,Peppers Bluewater Re   Lake Tekapo   Nzl ,01/12/2018,03/12/2018,,\n" +
                "4055-****-****-5758,D,37.49,Rebel Albany           Albany        Nz ,01/12/2018,03/12/2018,,\n" +
                "4055-****-****-5758,D,150.00,Philip Morris (New Zea Greenlane     Nz ,01/12/2018,03/12/2018,,\n" +
                "4055-****-****-5758,D,4.61,Powershop              0275721770    Nz ,01/12/2018,03/12/2018,,\n" +
                "9554-****-****-7240,C,3642.61,Online       Payment -  Thank You ,01/12/2018,03/12/2018,,\n" +
                "4055-****-****-5758,D,13.90,Roll N Sushi           Auckland      Nz ,30/11/2018,30/11/2018,,\n" +
                "4055-****-****-5758,D,5.46,Countdown Akl Metro    Auckland      Nzl ,28/11/2018,28/11/2018,,\n" +
                "4055-****-****-5758,D,60.67,Countdown Silverdale   Silverdale    Nzl ,28/11/2018,28/11/2018,,\n" +
                "4055-****-****-5758,D,12.00,Subway Beach Road      Auckland      Nz ,28/11/2018,30/11/2018,,\n" +
                "4055-****-****-5758,D,102.37,Bp Connect Millwater   Auckland      Nzl ,26/11/2018,26/11/2018,,\n" +
                "4055-****-****-5758,D,20.00,Vodafone Prepay Visa M Auckland      Nz ,26/11/2018,27/11/2018,,\n" +
                "4055-****-****-5758,D,85.00,Akl Airport Carpark    Auckland      Nz ,25/11/2018,26/11/2018,,\n" +
                "4055-****-****-5758,D,12.50,Japan Home Mart        Auckland      Nz ,24/11/2018,26/11/2018,,\n" +
                "4055-****-****-5758,D,28.50,New World Albany       Albany Nz     Nz ,24/11/2018,26/11/2018,,\n" +
                "4055-****-****-5758,D,19.11,Watercare Online       East Tamaki   Nz ,22/11/2018,22/11/2018,,\n" +
                "4055-****-****-5758,D,16.00,Silverdale Medical Par Silverdale    Nz ,22/11/2018,22/11/2018,,\n" +
                "4055-****-****-5758,D,2.50,Roll N Sushi           Auckland      Nz ,21/11/2018,21/11/2018,,\n" +
                "4055-****-****-5758,D,12.00,Saigonz Limited        Auckland City Nz ,21/11/2018,21/11/2018,,\n" +
                "4055-****-****-5758,D,175.00,Philip Morris (New Zea Greenlane     Nz ,21/11/2018,23/11/2018,,\n" +
                "4055-****-****-5758,D,26.07,Countdown Silverdale   Silverdale    Nzl ,19/11/2018,19/11/2018,,\n" +
                "4055-****-****-5758,D,2.00,Mylotto.Co.Nz          New Zealand   Nz ,17/11/2018,19/11/2018,,\n" +
                "4055-****-****-5758,D,1.00,Mylotto.Co.Nz          New Zealand   Nz ,17/11/2018,19/11/2018,,\n" +
                "4055-****-****-5758,D,106.72,Bp Connect Millwater   Auckland      Nzl ,16/11/2018,16/11/2018,,\n" +
                "9554-****-****-7240,C,1000.00,Online       Payment -  Thank You ,16/11/2018,16/11/2018,,\n" +
                "4055-****-****-5758,D,7.50,Roll N Sushi           Auckland      Nz ,14/11/2018,14/11/2018,,\n" +
                "4055-****-****-5758,D,5.04,Powershop              0275721770    Nz ,14/11/2018,15/11/2018,,\n" +
                "4055-****-****-5758,D,300.00,Philip Morris (New Zea Greenlane     Nz ,14/11/2018,15/11/2018,,\n" +
                "4055-****-****-5758,D,28.90,Beach Rd Liqour        Auckland      Nz ,13/11/2018,13/11/2018,,\n" +
                "4055-****-****-5758,D,20.99,Dominos Pizza          Auckland      Nz ,13/11/2018,15/11/2018,,\n" +
                "4055-****-****-5758,D,28.90,Bp Connect Millwater   Auckland      Nzl ,12/11/2018,12/11/2018,,\n" +
                "4055-****-****-5758,C,190.00,Skyline Queenstown Onl Queenstown    Nz ,12/11/2018,12/11/2018,,\n" +
                "4055-****-****-5758,D,53.39,Countdown Silverdale   Silverdale    Nzl ,11/11/2018,12/11/2018,,\n" +
                "4055-****-****-5758,D,28.90,Bp Connect Millwater   Auckland      Nzl ,10/11/2018,12/11/2018,,\n" +
                "4055-****-****-5758,D,3.99,Yummy Mart             Auckland      Nz ,10/11/2018,12/11/2018,,\n" +
                "4055-****-****-5758,D,110.58,Bp Connect Millwater   Auckland      Nzl ,09/11/2018,09/11/2018,,\n" +
                "4055-****-****-5758,D,10.50,Roll N Sushi           Auckland      Nz ,08/11/2018,08/11/2018,,\n" +
                "4055-****-****-5758,D,28.90,Bp Connect Millwater   Auckland      Nzl ,08/11/2018,08/11/2018,,\n" +
                "4055-****-****-5758,D,23.97,Dominos Pizza          Auckland      Nz ,08/11/2018,12/11/2018,,\n" +
                "4055-****-****-5758,D,4.69,Countdown Silverdale   Silverdale    Nzl ,07/11/2018,08/11/2018,,\n" +
                "4055-****-****-5758,D,31.34,Countdown Silverdale   Silverdale    Nzl ,06/11/2018,06/11/2018,,\n" +
                "4055-****-****-5758,D,22.00,Twl 191 Silverdale     Silverdale    Nz ,06/11/2018,07/11/2018,,\n" +
                "4055-****-****-5758,D,7.00,Pizza Hut Silverdale   Silverdale    Nz ,06/11/2018,07/11/2018,,\n" +
                "4055-****-****-5758,D,4.80,Powershop              0275721770    Nz ,06/11/2018,07/11/2018,,\n" +
                "4055-****-****-5758,D,28.90,Bp Connect Millwater   Auckland      Nzl ,05/11/2018,05/11/2018,,\n" +
                "4055-****-****-5758,D,27.00,Countdown Silverdale   Silverdale    Nzl ,04/11/2018,05/11/2018,,\n" +
                "4055-****-****-5758,D,16.00,Northland Waste Limite Warkworth     Nz ,04/11/2018,05/11/2018,,\n" +
                "4055-****-****-5758,D,23.26,Bunnings - 9497        Silverdale    Nz ,04/11/2018,05/11/2018,,\n" +
                "4055-****-****-5758,D,11.99,Kfc - 610              Silverdale    Nz ,04/11/2018,06/11/2018,,\n" +
                "4055-****-****-5758,D,28.90,Bp Connect Millwater   Auckland      Nzl ,03/11/2018,05/11/2018,,\n" +
                "4055-****-****-5758,D,51.62,Bunnings - 9497        Silverdale    Nz ,03/11/2018,05/11/2018,,\n" +
                "4055-****-****-5758,D,28.90,Bp Connect Millwater   Auckland      Nzl ,02/11/2018,02/11/2018,,\n" +
                "4055-****-****-5758,C,330.00,Earth & Sky            Tekapo        Nz ,02/11/2018,02/11/2018,,\n" +
                "4055-****-****-5758,D,20.00,Vodafone Prepay Visa M Auckland      Nz ,02/11/2018,05/11/2018,,\n" +
                "4055-****-****-5758,D,106.93,Bp Connect Millwater   Auckland      Nzl ,01/11/2018,01/11/2018,,\n" +
                "4055-****-****-5758,D,9.11,Powershop              0275721770    Nz ,01/11/2018,02/11/2018,,\n"

        val transactions =
            AnzCreditParser().parse(ByteArrayInputStream(statementString.toByteArray(Charsets.UTF_8)))
        val calendar = Calendar.getInstance()

        Assert.assertEquals(56, transactions!!.size)

        Assert.assertEquals("Countdown Silverdale   Silverdale    Nzl", transactions[0].description)
        Assert.assertEquals("-23.49", transactions[0].money.value!!.amount.toString())
        calendar.time = transactions[0].date.value!!
        Assert.assertEquals(2018, calendar.get(Calendar.YEAR))
        Assert.assertEquals(12, calendar.get(Calendar.MONTH) + 1)
        Assert.assertEquals(1, calendar.get(Calendar.DAY_OF_MONTH))

        Assert.assertEquals("Online       Payment -  Thank You", transactions[5].description)
        Assert.assertEquals("3642.61", transactions[5].money.value!!.amount.toString())
        calendar.time = transactions[5].date.value!!
        Assert.assertEquals(2018, calendar.get(Calendar.YEAR))
        Assert.assertEquals(12, calendar.get(Calendar.MONTH) + 1)
        Assert.assertEquals(1, calendar.get(Calendar.DAY_OF_MONTH))
    }

    @Test
    fun test_withoutHeaderLine() {
        val statementString =
            "4055-****-****-5758,D,23.49,Countdown Silverdale   Silverdale    Nzl ,01/12/2018,03/12/2018,,\n" +
                "4055-****-****-5758,D,345.78,Peppers Bluewater Re   Lake Tekapo   Nzl ,01/12/2018,03/12/2018,,\n"

        val transactions =
            AnzCreditParser().parse(ByteArrayInputStream(statementString.toByteArray(Charsets.UTF_8)))
        val calendar = Calendar.getInstance()

        Assert.assertEquals(2, transactions!!.size)

        Assert.assertEquals(
            "Countdown Silverdale   Silverdale    Nzl",
            transactions[0].description
        )
        Assert.assertEquals("-23.49", transactions[0].money.value!!.amount.toString())
        calendar.time = transactions[0].date.value!!
        Assert.assertEquals(2018, calendar.get(Calendar.YEAR))
        Assert.assertEquals(12, calendar.get(Calendar.MONTH) + 1)
        Assert.assertEquals(1, calendar.get(Calendar.DAY_OF_MONTH))

        Assert.assertEquals("Peppers Bluewater Re   Lake Tekapo   Nzl", transactions[1].description)
        Assert.assertEquals("-345.78", transactions[1].money.value!!.amount.toString())
        calendar.time = transactions[1].date.value!!
        Assert.assertEquals(2018, calendar.get(Calendar.YEAR))
        Assert.assertEquals(12, calendar.get(Calendar.MONTH) + 1)
        Assert.assertEquals(1, calendar.get(Calendar.DAY_OF_MONTH))
    }

    @Test
    fun test_empty() {
        Assert.assertEquals(
            0,
            AnzSavingsParser().parse(
                ByteArrayInputStream(
                    "".toByteArray(Charsets.UTF_8)
                )
            )?.size
        )

        Assert.assertEquals(
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
        Assert.assertEquals(
            0,
            AnzSavingsParser().parse(
                ByteArrayInputStream(
                    ",Smart Kitchen,,,,-20.00,26/11/,,\n".toByteArray(Charsets.UTF_8)
                )
            )?.size
        )
    }
}
