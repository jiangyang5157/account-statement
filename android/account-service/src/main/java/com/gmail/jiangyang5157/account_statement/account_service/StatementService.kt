package com.gmail.jiangyang5157.account_statement.account_service

import androidx.lifecycle.LiveData
import com.gmail.jiangyang5157.account_statement.account_service.dto.StatementDto
import com.gmail.jiangyang5157.core.network.ApiResponse
import okhttp3.*
import retrofit2.http.GET

/**
 * No available service at this moment, using fake service to fit in
 */
interface StatementService {

    companion object {
        const val baseUrl = "https://www.google.com/"
    }

    @GET("get_account_statements.json")
    fun fetchStatements(): LiveData<ApiResponse<List<StatementDto>>>
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