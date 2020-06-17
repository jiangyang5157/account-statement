package com.gmail.jiangyang5157.account_statement.account_db

import android.database.sqlite.SQLiteConstraintException
import androidx.arch.core.executor.testing.CountingTaskExecutorRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gmail.jiangyang5157.account_statement.account_cvo.AccountEntity
import com.gmail.jiangyang5157.account_statement.account_cvo.TransactionEntity
import com.gmail.jiangyang5157.core.ext.getOrAwaitValue
import com.gmail.jiangyang5157.kotlin_kit.data.model.finance.Money
import org.junit.*
import org.junit.runner.RunWith
import java.util.*
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class StatementDaoTest {

    @get:Rule
    val countingTaskExecutorRule = CountingTaskExecutorRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: StatementDb

    @Before
    fun initDb() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            StatementDb::class.java
        ).build()
    }

    @After
    fun closeDb() {
        countingTaskExecutorRule.drainTasks(10, TimeUnit.SECONDS)
        db.close()
    }

    @Test
    fun test_insertAccounts() {
        val account = AccountEntity(
            "FakeAccount",
            Date(1592391430000) // "Jun 17, 2020 10:57:10 PM"
        )
        db.statementDao().insertAccounts(listOf(account))

        Assert.assertEquals(
            account,
            db.statementDao().findAccountByName("FakeAccount").getOrAwaitValue()
        )
    }

    @Test
    fun test_insertAccounts_whereAccountExist_abort() {
        val account = AccountEntity(
            "FakeAccount",
            Date(1592391430000)
        )
        val account2 = AccountEntity(
            "FakeAccount",
            Date()
        )
        try {
            db.statementDao().insertAccounts(listOf(account, account2))
            Assert.fail()
        } catch (e: SQLiteConstraintException) {
        }
    }

    @Test
    fun test_insertTransaction_whereAccountNotExist_abort() {
        val transaction = TransactionEntity(
            accountName = "FakeAccount",
            date = Date(),
            money = Money(-10.0),
            description = "desc"
        )
        try {
            db.statementDao().insertTransactions(listOf(transaction))
            Assert.fail()
        } catch (e: SQLiteConstraintException) {
        }
    }

    @Test
    fun test_insertTransaction_whereAccountExist() {
        val account = AccountEntity(
            "FakeAccount",
            Date(1592391430000)
        )
        val transaction = TransactionEntity(
            accountName = "FakeAccount",
            date = Date(),
            money = Money(-10.0),
            description = "desc"
        )
        db.statementDao().insertAccounts(listOf(account))
        db.statementDao().insertTransactions(listOf(transaction))

        val transactions =
            db.statementDao().findTransactionsByAccountName("FakeAccount").getOrAwaitValue()
        Assert.assertEquals(1, transactions.size)
        Assert.assertEquals("FakeAccount", transactions[0].accountName)
        Assert.assertEquals("-10.00", transactions[0].money.amount.toString())
    }

    @Test
    fun test_insertTransaction_whereTransactionExist_abort() {
        val account = AccountEntity(
            "FakeAccount",
            Date(1592391430000)
        )
        val transaction = TransactionEntity(
            accountName = "FakeAccount",
            date = Date(),
            money = Money(-10.0),
            description = "desc"
        )
        db.statementDao().insertAccounts(listOf(account))
        try {
            db.statementDao().insertTransactions(listOf(transaction, transaction))
            Assert.fail()
        } catch (e: SQLiteConstraintException) {
        }
    }

    @Test
    fun test_findAccounts() {
        val account = AccountEntity(
            "FakeAccount",
            Date(1592391430000)
        )
        val account2 = AccountEntity(
            "FakeAccount****",
            Date(1592391430000)
        )
        db.statementDao().insertAccounts(listOf(account, account2))
        Assert.assertEquals(2, db.statementDao().findAccounts().getOrAwaitValue().size)
    }

    @Test
    fun test_findTransactions() {
        val account = AccountEntity(
            "FakeAccount",
            Date(1592391430000)
        )
        val account2 = AccountEntity(
            "FakeAccount****",
            Date(1592391430000)
        )
        val transaction = TransactionEntity(
            accountName = "FakeAccount",
            date = Date(),
            money = Money(-10.0),
            description = "desc"
        )
        val transaction2 = TransactionEntity(
            accountName = "FakeAccount",
            date = Date(),
            money = Money(-10.0),
            description = "desc****"
        )
        val transaction3 = TransactionEntity(
            accountName = "FakeAccount****",
            date = Date(),
            money = Money(-10.0),
            description = "desc"
        )
        db.statementDao().insertAccounts(listOf(account, account2))
        db.statementDao().insertTransactions(listOf(transaction, transaction2, transaction3))

        Assert.assertEquals(3, db.statementDao().findTransactions().getOrAwaitValue().size)
        Assert.assertEquals(
            2,
            db.statementDao().findTransactionsByAccountName("FakeAccount").getOrAwaitValue().size
        )
        Assert.assertEquals(
            1,
            db.statementDao().findTransactionsByAccountName("FakeAccount****").getOrAwaitValue().size
        )
    }

    @Test
    fun test_findStatements() {
        val account = AccountEntity(
            "FakeAccount",
            Date(1592391430000)
        )
        val account2 = AccountEntity(
            "FakeAccount****",
            Date(1592391430000)
        )
        db.statementDao().insertAccounts(listOf(account, account2))

        Assert.assertEquals(2, db.statementDao().findStatements().getOrAwaitValue().size)
        Assert.assertNotNull(
            db.statementDao().findStatementByAccountName("FakeAccount").getOrAwaitValue()
        )
        Assert.assertNotNull(
            db.statementDao().findStatementByAccountName("FakeAccount****").getOrAwaitValue()
        )
        Assert.assertNull(
            db.statementDao().findStatementByAccountName("FakeAccount????").getOrAwaitValue()
        )
    }

    @Test
    fun test_deleteAccount() {
        val account = AccountEntity(
            "FakeAccount",
            Date(1592391430000)
        )
        db.statementDao().insertAccounts(listOf(account))

        Assert.assertEquals(1, db.statementDao().findStatements().getOrAwaitValue().size)

        db.statementDao().deleteAccounts(
            listOf(
                AccountEntity(
                    "FakeAccount",
                    Date()
                )
            )
        )
        Assert.assertEquals(0, db.statementDao().findStatements().getOrAwaitValue().size)
    }

    @Test
    fun test_deleteTransactions() {
        val account = AccountEntity(
            "FakeAccount",
            Date(1592391430000)
        )
        val account2 = AccountEntity(
            "FakeAccount****",
            Date(1592391430000)
        )
        val transaction = TransactionEntity(
            accountName = "FakeAccount",
            date = Date(),
            money = Money(-10.0),
            description = "desc"
        )
        val transaction2 = TransactionEntity(
            accountName = "FakeAccount",
            date = Date(),
            money = Money(-10.0),
            description = "desc****"
        )
        val transaction3 = TransactionEntity(
            accountName = "FakeAccount****",
            date = Date(),
            money = Money(-10.0),
            description = "desc"
        )
        db.statementDao().insertAccounts(listOf(account, account2))
        db.statementDao().insertTransactions(listOf(transaction, transaction2, transaction3))
        Assert.assertEquals(3, db.statementDao().findTransactions().getOrAwaitValue().size)

        db.statementDao().deleteTransactions(listOf(transaction, transaction3))
        Assert.assertEquals(1, db.statementDao().findTransactions().getOrAwaitValue().size)
        Assert.assertEquals(
            1,
            db.statementDao().findTransactionsByAccountName("FakeAccount").getOrAwaitValue().size
        )
        Assert.assertEquals(
            transaction2,
            db.statementDao().findTransactionsByAccountName("FakeAccount").getOrAwaitValue()[0]
        )
    }

    @Test
    fun test_deleteAccount_whereAccountNotExist_abort() {
        val account = AccountEntity(
            "FakeAccount",
            Date(1592391430000)
        )
        db.statementDao().insertAccounts(listOf(account))
        Assert.assertEquals(1, db.statementDao().findStatements().getOrAwaitValue().size)

        db.statementDao().deleteAccounts(
            listOf(
                AccountEntity(
                    "FakeAccount????",
                    Date(1592391430000)
                )
            )
        )
        Assert.assertEquals(1, db.statementDao().findStatements().getOrAwaitValue().size)
    }

    @Test
    fun test_deleteAccount_alsoDeleteAssociatedTransaction() {
        val account = AccountEntity(
            "FakeAccount",
            Date(1592391430000)
        )
        val account2 = AccountEntity(
            "FakeAccount****",
            Date(1592391430000)
        )
        val transaction = TransactionEntity(
            accountName = "FakeAccount",
            date = Date(),
            money = Money(-10.0),
            description = "desc"
        )
        val transaction2 = TransactionEntity(
            accountName = "FakeAccount",
            date = Date(),
            money = Money(-10.0),
            description = "desc****"
        )
        val transaction3 = TransactionEntity(
            accountName = "FakeAccount****",
            date = Date(),
            money = Money(-10.0),
            description = "desc"
        )

        db.statementDao().insertAccounts(listOf(account, account2))
        db.statementDao().insertTransactions(listOf(transaction, transaction2, transaction3))
        Assert.assertEquals(3, db.statementDao().findTransactions().getOrAwaitValue().size)

        db.statementDao().deleteAccounts(
            listOf(
                AccountEntity(
                    "FakeAccount",
                    Date()
                )
            )
        )
        Assert.assertEquals(1, db.statementDao().findTransactions().getOrAwaitValue().size)
        Assert.assertEquals(1, db.statementDao().findStatements().getOrAwaitValue().size)

        db.statementDao().deleteAccounts(
            listOf(
                AccountEntity(
                    "FakeAccount****",
                    Date()
                )
            )
        )
        Assert.assertEquals(0, db.statementDao().findTransactions().getOrAwaitValue().size)
        Assert.assertEquals(0, db.statementDao().findStatements().getOrAwaitValue().size)
    }

    @Test
    fun test_updateAccounts() {
        val account = AccountEntity(
            "FakeAccount",
            Date()
        )
        val account2 = AccountEntity(
            "FakeAccount",
            Date(1592391430000)
        )
        db.statementDao().insertAccounts(listOf(account))
        Assert.assertEquals(1, db.statementDao().findAccounts().getOrAwaitValue().size)
        Assert.assertEquals(
            account,
            db.statementDao().findAccountByName("FakeAccount").getOrAwaitValue()
        )

        db.statementDao().updateAccounts(listOf(account2))
        Assert.assertEquals(1, db.statementDao().findAccounts().getOrAwaitValue().size)
        Assert.assertEquals(
            account2,
            db.statementDao().findAccountByName("FakeAccount").getOrAwaitValue()
        )
    }
}
