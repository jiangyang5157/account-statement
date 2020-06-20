package com.gmail.jiangyang5157.account_statement.bank_data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gmail.jiangyang5157.core.ext.getOrAwaitValue
import com.gmail.jiangyang5157.core.network.ApiSuccessResponse
import com.gmail.jiangyang5157.core.network.LiveDataCallAdapterFactory
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class StatementServiceTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var statementService: StatementService

    @Before
    fun initialize() {
        statementService = Retrofit.Builder()
            .baseUrl("https://www.google.com/")
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

        val calendar = Calendar.getInstance()
        assertEquals("FakeAccount", statements[0].account.name)
        assertEquals(2, statements[0].transactions.size)
        assertEquals("FakeAccount", statements[0].transactions[0].accountName)
        assertEquals("FakeAccount", statements[0].transactions[1].accountName)
        assertEquals("-10.00", statements[0].transactions[0].money.amount.toString())
        assertEquals("10.00", statements[0].transactions[1].money.amount.toString())
        assertEquals("desc", statements[1].transactions[0].description)
        assertEquals("desc", statements[1].transactions[1].description)
        calendar.time = statements[0].transactions[0].date
        assertEquals(2020, calendar.get(Calendar.YEAR))
        assertEquals(1, calendar.get(Calendar.MONTH) + 1)
        assertEquals(1, calendar.get(Calendar.DAY_OF_MONTH))
        calendar.time = statements[0].transactions[1].date
        assertEquals(2020, calendar.get(Calendar.YEAR))
        assertEquals(1, calendar.get(Calendar.MONTH) + 1)
        assertEquals(1, calendar.get(Calendar.DAY_OF_MONTH))

        assertEquals("FakeAccount****", statements[1].account.name)
        assertEquals(2, statements[1].transactions.size)
        assertEquals("FakeAccount****", statements[1].transactions[0].accountName)
        assertEquals("FakeAccount****", statements[1].transactions[1].accountName)
        assertEquals("-10.00", statements[1].transactions[0].money.amount.toString())
        assertEquals("10.00", statements[1].transactions[1].money.amount.toString())
        assertEquals("desc", statements[1].transactions[0].description)
        assertEquals("desc", statements[1].transactions[1].description)
        calendar.time = statements[1].transactions[0].date
        assertEquals(2020, calendar.get(Calendar.YEAR))
        assertEquals(1, calendar.get(Calendar.MONTH) + 1)
        assertEquals(2, calendar.get(Calendar.DAY_OF_MONTH))
        calendar.time = statements[1].transactions[1].date
        assertEquals(2020, calendar.get(Calendar.YEAR))
        assertEquals(1, calendar.get(Calendar.MONTH) + 1)
        assertEquals(2, calendar.get(Calendar.DAY_OF_MONTH))
    }
}
