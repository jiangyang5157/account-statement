package com.gmail.jiangyang5157.account_statement.bank_data.remote

import androidx.lifecycle.LiveData
import com.gmail.jiangyang5157.account_statement.bank_data.remote.dto.StatementDto
import com.gmail.jiangyang5157.core.network.ApiResponse
import com.gmail.jiangyang5157.core.network.LiveDataCallAdapterFactory
import com.google.gson.GsonBuilder
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface StatementService {

    @GET("get_statements.json")
    fun fetchStatements(): LiveData<ApiResponse<List<StatementDto>>>

    // TODO remove
    class Builder {
        /**
         * No available service at this moment, using fake service to fit in
         */
        fun build(): StatementService {
            return Retrofit.Builder()
                .baseUrl("https://www.google.com/")
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(StatementServiceInterceptor())
                        .build()
                )
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder().setLenient().create()
                    )
                )
                .build()
                .create(StatementService::class.java)
        }
    }
}

class StatementServiceInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url().uri().toString()
        val responseString = when {
            uri.endsWith("get_statements.json") -> mock_get_statements
            else -> ""
        }

        return chain.proceed(chain.request())
            .newBuilder()
            .code(200)
            .protocol(Protocol.HTTP_2)
            .message(responseString)
            .body(
                ResponseBody.create(
                    MediaType.parse("application/json"),
                    responseString.toByteArray()
                )
            )
            .addHeader("content-type", "application/json")
            .build()
    }
}

const val mock_get_statements = """
[
    {
      "account": {
        "name": "FakeAccount"
      },
      "transactions": [
        {
          "accountName": "FakeAccount",
          "date": "01/01/2020",
          "money": -10.0,
          "description": "desc"
        },
        {
          "accountName": "FakeAccount",
          "date": "01/01/2020",
          "money": 10.0,
          "description": "desc"
        }
      ]
    },
    {
      "account": {
        "name": "FakeAccount****"
      },
      "transactions": [
        {
          "accountName": "FakeAccount****",
          "date": "02/01/2020",
          "money": -10.0,
          "description": "desc"
        },
        {
          "accountName": "FakeAccount****",
          "date": "02/01/2020",
          "money": 10.0,
          "description": "desc"
        }
      ]
    }
]
"""
