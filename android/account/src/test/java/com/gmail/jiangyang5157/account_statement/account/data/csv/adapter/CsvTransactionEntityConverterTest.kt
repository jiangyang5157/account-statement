package com.gmail.jiangyang5157.account_statement.account.data.csv.adapter

import com.gmail.jiangyang5157.account_statement.account.data.csv.model.CsvField
import com.gmail.jiangyang5157.account_statement.account.data.csv.model.CsvTransaction
import com.gmail.jiangyang5157.account_statement.account.domain.adapter.DateStringConverter
import com.gmail.jiangyang5157.account_statement.account.domain.adapter.MoneyStringConverter
import com.gmail.jiangyang5157.kotlin_kit.data.model.finance.Money
import com.gmail.jiangyang5157.kotlin_kit.utils.RegexUtils
import org.junit.Assert
import org.junit.Test
import java.util.*

class CsvTransactionEntityConverterTest {

    @Test
    fun test_common() {
        val accountName = "FakeAccount"
        val csvTransaction = object : CsvTransaction {
            override var date: CsvField<Date>
                get() = CsvField(
                    0,
                    DateStringConverter.stringToDate("11/12/2015", RegexUtils.DATE_DMY)
                )
                set(value) {}
            override var money: CsvField<Money>
                get() = CsvField(
                    0,
                    MoneyStringConverter.stringToMoney("1")
                )
                set(value) {}
            override val description: String
                get() = "desc"

        }
        val transactionEntity =
            CsvTransactionEntityConverter.csvTransactionToEntity(accountName, csvTransaction)
        Assert.assertNotNull(transactionEntity)
        Assert.assertEquals(accountName, transactionEntity?.accountName)
        Assert.assertEquals(csvTransaction.date.value, transactionEntity?.date)
        Assert.assertEquals(csvTransaction.description, transactionEntity?.description)
    }

    @Test
    fun test_invalid() {
        val accountName = "FakeAccount"
        val invalid = listOf(
            object : CsvTransaction {
                override var date: CsvField<Date>
                    get() = CsvField(
                        0,
                        null
                    )
                    set(value) {}
                override var money: CsvField<Money>
                    get() = CsvField(
                        0,
                        MoneyStringConverter.stringToMoney("1")
                    )
                    set(value) {}
                override val description: String
                    get() = "desc"

            },
            object : CsvTransaction {
                override var date: CsvField<Date>
                    get() = CsvField(
                        0,
                        DateStringConverter.stringToDate("11/12/2015", RegexUtils.DATE_DMY)
                    )
                    set(value) {}
                override var money: CsvField<Money>
                    get() = CsvField(
                        0,
                        null
                    )
                    set(value) {}
                override val description: String
                    get() = "desc"

            }
        )
        invalid.forEach {
            Assert.assertNull(CsvTransactionEntityConverter.csvTransactionToEntity(accountName, it))
        }
    }
}
