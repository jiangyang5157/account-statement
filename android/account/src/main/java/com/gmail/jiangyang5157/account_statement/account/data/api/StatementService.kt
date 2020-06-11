package com.gmail.jiangyang5157.account_statement.account.data.api

import androidx.lifecycle.LiveData
import com.gmail.jiangyang5157.account_statement.account.domain.entity.StatementEntity
import com.gmail.jiangyang5157.core.network.ApiResponse
import okhttp3.*
import retrofit2.http.GET

interface StatementService {

    companion object {
        const val baseUrl = "https://www.google.com/"
    }

    @GET("get_account_statements.json")
    fun fetchStatements(): LiveData<ApiResponse<List<StatementEntity>>>
}

class StatementServiceInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url().uri().toString()
        val responseString = when {
            uri.endsWith("get_account_statements.json") -> mock_get_account_statements
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

const val mock_get_account_statements = """
[
    {
      "account": {
        "name": "FakeAccount"
      },
      "transactions": [
        {
          "accountName": "FakeAccount",
          "date": "01/12/2019",
          "money": "-10.00",
          "description": "desc"
        },
        {
          "accountName": "FakeAccount",
          "date": "01/12/2019",
          "money": "10.00",
          "description": "desc"
        }
      ]
    },
    {
      "account": {
        "name": "FakeAccount2"
      },
      "transactions": [
        {
          "accountName": "FakeAccount2",
          "date": "02/12/2019",
          "money": "-10.00",
          "description": "desc"
        },
        {
          "accountName": "FakeAccount2",
          "date": "02/12/2019",
          "money": "10.00",
          "description": "desc"
        }
      ]
    }
]
"""
