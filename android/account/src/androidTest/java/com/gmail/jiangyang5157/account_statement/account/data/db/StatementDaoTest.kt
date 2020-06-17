//package com.gmail.jiangyang5157.account_statement.account.data.db
//
//import android.database.sqlite.SQLiteConstraintException
//import androidx.arch.core.executor.testing.CountingTaskExecutorRule
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.room.Room
//import androidx.test.core.app.ApplicationProvider
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import com.gmail.jiangyang5157.account_statement.account.ext.getOrAwaitValue
//import com.gmail.jiangyang5157.account_statement.account_cvo.AccountEntity
//import com.gmail.jiangyang5157.account_statement.account_cvo.TransactionEntity
//import com.gmail.jiangyang5157.kotlin_kit.data.adapter.DateStringConverter
//import com.gmail.jiangyang5157.kotlin_kit.data.adapter.MoneyStringConverter
//import com.gmail.jiangyang5157.kotlin_kit.utils.RegexUtils
//import org.junit.*
//import org.junit.runner.RunWith
//import java.util.concurrent.TimeUnit
//
//@RunWith(AndroidJUnit4::class)
//class StatementDaoTest {
//
//    @get:Rule
//    val countingTaskExecutorRule = CountingTaskExecutorRule()
//
//    @get:Rule
//    var instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    private lateinit var db: StatementDb
//
//    @Before
//    fun initDb() {
//        db = Room.inMemoryDatabaseBuilder(
//            ApplicationProvider.getApplicationContext(),
//            StatementDb::class.java
//        ).build()
//    }
//
//    @After
//    fun closeDb() {
//        countingTaskExecutorRule.drainTasks(10, TimeUnit.SECONDS)
//        db.close()
//    }
//
//    @Test
//    fun test_insertAccounts() {
//        db.statementDao().insertAccounts(listOf(
//            AccountEntity(
//                "FakeAccount"
//            )
//        ))
//        Assert.assertEquals(
//            "FakeAccount",
//            db.statementDao().findAccountByName("FakeAccount").getOrAwaitValue().name
//        )
//    }
//
//    @Test
//    fun test_insertAccounts_whereAccountExist_abort() {
//        try {
//            db.statementDao().insertAccounts(
//                listOf(
//                    AccountEntity(
//                        "FakeAccount"
//                    ),
//                    AccountEntity(
//                        "FakeAccount"
//                    )
//                )
//            )
//            Assert.fail()
//        } catch (e: SQLiteConstraintException) {
//
//        }
//    }
//
//    @Test
//    fun test_insertTransaction_whereAccountNotExist_abort() {
//        try {
//            db.statementDao().insertTransactions(
//                listOf(
//                    TransactionEntity(
//                        accountName = "FakeAccount",
//                        date = Date(1592391430000),
//                        money = MoneyStringConverter().backward("-10")!!,
//                        description = "desc"
//                    )
//                )
//            )
//            Assert.fail()
//        } catch (e: SQLiteConstraintException) {
//
//        }
//    }
//
//    @Test
//    fun test_insertTransaction_whereAccountExist() {
//        db.statementDao().insertAccounts(listOf(
//            AccountEntity(
//                "FakeAccount"
//            )
//        ))
//        db.statementDao().insertTransactions(
//            listOf(
//                TransactionEntity(
//                    accountName = "FakeAccount",
//                    date = Date(1592391430000),
//                    money = MoneyStringConverter().backward("-10")!!,
//                    description = "desc"
//                )
//            )
//        )
//        val transactions =
//            db.statementDao().findTransactionsByAccountName("FakeAccount").getOrAwaitValue()
//        Assert.assertEquals(1, transactions.size)
//        Assert.assertEquals("FakeAccount", transactions[0].accountName)
//        Assert.assertEquals("-10.00", transactions[0].money.amount.toString())
//    }
//
//    @Test
//    fun test_insertTransaction_whereTransactionExist_abort() {
//        db.statementDao().insertAccounts(listOf(
//            AccountEntity(
//                "FakeAccount"
//            )
//        ))
//        try {
//            db.statementDao().insertTransactions(
//                listOf(
//                    TransactionEntity(
//                        accountName = "FakeAccount",
//                        date = Date(1592391430000),
//                        money = MoneyStringConverter().backward("-10")!!,
//                        description = "desc"
//                    ),
//                    TransactionEntity(
//                        accountName = "FakeAccount",
//                        date = Date(1592391430000),
//                        money = MoneyStringConverter().backward("-10")!!,
//                        description = "desc"
//                    )
//                )
//            )
//            Assert.fail()
//        } catch (e: SQLiteConstraintException) {
//
//        }
//    }
//
//    @Test
//    fun test_findAccounts() {
//        db.statementDao().insertAccounts(
//            listOf(
//                AccountEntity(
//                    "FakeAccount"
//                ),
//                AccountEntity(
//                    "FakeAccount2"
//                )
//            )
//        )
//        Assert.assertEquals(2, db.statementDao().findAccounts().getOrAwaitValue().size)
//    }
//
//    @Test
//    fun test_findTransactions() {
//        db.statementDao().insertAccounts(
//            listOf(
//                AccountEntity(
//                    "FakeAccount"
//                ),
//                AccountEntity(
//                    "FakeAccount2"
//                )
//            )
//        )
//        db.statementDao().insertTransactions(
//            listOf(
//                TransactionEntity(
//                    accountName = "FakeAccount",
//                    date = Date(1592391430000),
//                    money = MoneyStringConverter().backward("-10")!!,
//                    description = "desc"
//                ),
//                TransactionEntity(
//                    accountName = "FakeAccount",
//                    date = Date(1592391430000),
//                    money = MoneyStringConverter().backward("-10")!!,
//                    description = "desc2"
//                ),
//                TransactionEntity(
//                    accountName = "FakeAccount2",
//                    date = Date(1592391430000),
//                    money = MoneyStringConverter().backward("-10")!!,
//                    description = "desc"
//                )
//            )
//        )
//        Assert.assertEquals(3, db.statementDao().findTransactions().getOrAwaitValue().size)
//        Assert.assertEquals(
//            2,
//            db.statementDao().findTransactionsByAccountName("FakeAccount").getOrAwaitValue().size
//        )
//        Assert.assertEquals(
//            1,
//            db.statementDao().findTransactionsByAccountName("FakeAccount2").getOrAwaitValue().size
//        )
//    }
//
//    @Test
//    fun test_findStatements() {
//        db.statementDao().insertAccounts(
//            listOf(
//                AccountEntity(
//                    "FakeAccount"
//                ),
//                AccountEntity(
//                    "FakeAccount2"
//                )
//            )
//        )
//        Assert.assertEquals(2, db.statementDao().findStatements().getOrAwaitValue().size)
//
//        Assert.assertNotNull(
//            db.statementDao().findStatementByAccountName("FakeAccount").getOrAwaitValue()
//        )
//        Assert.assertNotNull(
//            db.statementDao().findStatementByAccountName("FakeAccount2").getOrAwaitValue()
//        )
//        Assert.assertNull(
//            db.statementDao().findStatementByAccountName("FakeAccount11111111").getOrAwaitValue()
//        )
//    }
//
//    @Test
//    fun test_deleteAccount() {
//        db.statementDao().insertAccounts(listOf(
//            AccountEntity(
//                "FakeAccount"
//            )
//        ))
//        Assert.assertEquals(1, db.statementDao().findStatements().getOrAwaitValue().size)
//
//        db.statementDao().deleteAccounts(listOf(
//            AccountEntity(
//                "FakeAccount"
//            )
//        ))
//        Assert.assertEquals(0, db.statementDao().findStatements().getOrAwaitValue().size)
//    }
//
//    @Test
//    fun test_deleteTransactions() {
//        db.statementDao().insertAccounts(
//            listOf(
//                AccountEntity(
//                    "FakeAccount"
//                ),
//                AccountEntity(
//                    "FakeAccount2"
//                )
//            )
//        )
//        db.statementDao().insertTransactions(
//            listOf(
//                TransactionEntity(
//                    accountName = "FakeAccount",
//                    date = Date(1592391430000),
//                    money = MoneyStringConverter().backward("-10")!!,
//                    description = "desc"
//                ),
//                TransactionEntity(
//                    accountName = "FakeAccount",
//                    date = Date(1592391430000),
//                    money = MoneyStringConverter().backward("-10")!!,
//                    description = "desc2"
//                ),
//                TransactionEntity(
//                    accountName = "FakeAccount2",
//                    date = Date(1592391430000),
//                    money = MoneyStringConverter().backward("-10")!!,
//                    description = "desc"
//                )
//            )
//        )
//        Assert.assertEquals(3, db.statementDao().findTransactions().getOrAwaitValue().size)
//
//        db.statementDao().deleteTransactions(
//            listOf(
//                TransactionEntity(
//                    accountName = "FakeAccount",
//                    date = Date(1592391430000),
//                    money = MoneyStringConverter().backward("-10")!!,
//                    description = "desc2"
//                ),
//                TransactionEntity(
//                    accountName = "FakeAccount2",
//                    date = Date(1592391430000),
//                    money = MoneyStringConverter().backward("-10")!!,
//                    description = "desc"
//                )
//            )
//        )
//        Assert.assertEquals(1, db.statementDao().findTransactions().getOrAwaitValue().size)
//        Assert.assertEquals(
//            1,
//            db.statementDao().findTransactionsByAccountName("FakeAccount").getOrAwaitValue().size
//        )
//    }
//
//    @Test
//    fun test_deleteAccount_whereAccountNotExist_abort() {
//        db.statementDao().insertAccounts(listOf(
//            AccountEntity(
//                "FakeAccount"
//            )
//        ))
//        Assert.assertEquals(1, db.statementDao().findStatements()
//            .getOrAwaitValue().size)
//
//        db.statementDao().deleteAccounts(listOf(
//            AccountEntity(
//                "FakeAccount11111111"
//            )
//        ))
//        Assert.assertEquals(1, db.statementDao().findStatements().getOrAwaitValue().size)
//    }
//
//    @Test
//    fun test_deleteAccount_whereHasAssociatedTransaction() {
//        db.statementDao().insertAccounts(
//            listOf(
//                AccountEntity(
//                    "FakeAccount"
//                ),
//                AccountEntity(
//                    "FakeAccount2"
//                )
//            )
//        )
//        db.statementDao().insertTransactions(
//            listOf(
//                TransactionEntity(
//                    accountName = "FakeAccount",
//                    date = Date(1592391430000),
//                    money = MoneyStringConverter().backward("-10")!!,
//                    description = "desc"
//                ),
//                TransactionEntity(
//                    accountName = "FakeAccount",
//                    date = Date(1592391430000),
//                    money = MoneyStringConverter().backward("-10")!!,
//                    description = "desc2"
//                ),
//                TransactionEntity(
//                    accountName = "FakeAccount2",
//                    date = Date(1592391430000),
//                    money = MoneyStringConverter().backward("-10")!!,
//                    description = "desc"
//                )
//            )
//        )
//        Assert.assertEquals(3, db.statementDao().findTransactions().getOrAwaitValue().size)
//
//        db.statementDao().deleteAccounts(listOf(
//            AccountEntity(
//                "FakeAccount"
//            )
//        ))
//        Assert.assertEquals(1, db.statementDao().findTransactions().getOrAwaitValue().size)
//
//        db.statementDao().deleteAccounts(listOf(
//            AccountEntity(
//                "FakeAccount2"
//            )
//        ))
//        Assert.assertEquals(0, db.statementDao().findStatements().getOrAwaitValue().size)
//    }
//}
