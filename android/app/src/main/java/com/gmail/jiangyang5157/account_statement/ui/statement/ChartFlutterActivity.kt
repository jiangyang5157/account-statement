package com.gmail.jiangyang5157.account_statement.ui.statement

import android.util.Log
import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class ChartFlutterActivity : FlutterActivity() {

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        val transactions = intent.extras?.getString(keyTransactions)
        Log.d("####", "configureFlutterEngine ${flutterEngine}\ntransactions=$transactions")

        MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            methodChannel
        ).setMethodCallHandler { call, result ->
            if (call.method == "transactionsChannel") {
                if (!transactions.isNullOrEmpty()) {
                    result.success(transactions)
                } else {
                    result.error(transactions, "Empty transactions", transactions)
                }
            } else {
                result.notImplemented()
            }
        }
    }

    companion object {
        const val methodChannel = "com.gmail.jiangyang5157.account_statement/MethodChannel"
        const val keyTransactions = "keyTransactions"
    }
}
