package com.gmail.jiangyang5157.account_statement.account.data.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.gmail.jiangyang5157.core.network.LiveDataCallAdapterFactory
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient

import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gmail.jiangyang5157.core.network.ApiSuccessResponse
import org.junit.runners.JUnit4

class StatementServiceTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var statementService: StatementService

    @Before
    fun init() {
        statementService = Retrofit.Builder()
            .baseUrl(StatementService.baseUrl)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(StatementServiceInterceptor())
                    .build()
            )
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
            .create(StatementService::class.java)
    }

    @After
    fun finalize() {
    }

    @Test
    fun test_fetchStatements() {
        val statements = (statementService.fetchStatements()
            .getOrAwaitValue() as ApiSuccessResponse).responseBody

        assertEquals(2, statements.size)

        assertEquals("FakeAccount", statements[0].account.name)
        assertEquals(2, statements[0].transactions.size)
        assertEquals("FakeAccount", statements[0].transactions[0].accountName)
        assertEquals("FakeAccount", statements[0].transactions[1].accountName)
        assertEquals("-10.00", statements[0].transactions[0].money.amount.toString())
        assertEquals("10.00", statements[0].transactions[1].money.amount.toString())

        assertEquals("FakeAccount2", statements[1].account.name)
        assertEquals(2, statements[1].transactions.size)
        assertEquals("FakeAccount2", statements[1].transactions[0].accountName)
        assertEquals("FakeAccount2", statements[1].transactions[1].accountName)
        assertEquals("-10.00", statements[1].transactions[0].money.amount.toString())
        assertEquals("10.00", statements[1].transactions[1].money.amount.toString())
    }
}

fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            data = o
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)

    afterObserve.invoke()

    // Don't wait indefinitely if the LiveData is not set.
    if (!latch.await(time, timeUnit)) {
        this.removeObserver(observer)
        throw TimeoutException("LiveData value was never set.")
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}
