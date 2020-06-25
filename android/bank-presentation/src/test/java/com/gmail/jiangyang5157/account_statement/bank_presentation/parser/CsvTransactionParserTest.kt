package com.gmail.jiangyang5157.account_statement.bank_presentation.parser

import com.gmail.jiangyang5157.account_statement.bank_domain.entity.TransactionEntity
import com.gmail.jiangyang5157.kotlin_kit.data.model.finance.Money
import org.junit.Assert
import org.junit.Test
import java.util.*

class CsvTransactionParserTest {

    @Test
    fun test_common() {
        val accountName = "FakeAccount"
        val csvTransaction = object : CsvTransaction {
            override var date: CsvField<Date>
                get() = CsvField(
                    0,
                    Date()
                )
                set(value) {}
            override var money: CsvField<Money>
                get() = CsvField(
                    0,
                    TransactionEntity.MoneyDoubleConverter().backward(1.0)
                )
                set(value) {}
            override val description: String
                get() = "desc"

        }
        val transactionEntity =
            CsvTransactionParser(accountName).parse(csvTransaction)
        Assert.assertNotNull(transactionEntity)
        Assert.assertEquals(accountName, transactionEntity?.accountName)
        Assert.assertEquals(csvTransaction.date.value, transactionEntity?.date)
        Assert.assertEquals(csvTransaction.description, transactionEntity?.description)
    }

    @Test
    fun test_desc_withSurrounding() {
        val accountName = "FakeAccount"
        val csvTransaction = object : CsvTransaction {
            override var date: CsvField<Date>
                get() = CsvField(
                    0,
                    Date()
                )
                set(value) {}
            override var money: CsvField<Money>
                get() = CsvField(
                    0,
                    TransactionEntity.MoneyDoubleConverter().backward(1.0)
                )
                set(value) {}
            override val description: String
                get() = "\"desc\""

        }
        val transactionEntity =
            CsvTransactionParser(accountName).parse(csvTransaction)
        Assert.assertNotNull(transactionEntity)
        Assert.assertEquals(accountName, transactionEntity?.accountName)
        Assert.assertEquals(csvTransaction.date.value, transactionEntity?.date)
        Assert.assertEquals("desc", transactionEntity?.description)
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
                        TransactionEntity.MoneyDoubleConverter().backward(1.0)
                    )
                    set(value) {}
                override val description: String
                    get() = "desc"

            },
            object : CsvTransaction {
                override var date: CsvField<Date>
                    get() = CsvField(
                        0,
                        Date()
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
            Assert.assertNull(CsvTransactionParser(accountName).parse(it))
        }
    }
}
