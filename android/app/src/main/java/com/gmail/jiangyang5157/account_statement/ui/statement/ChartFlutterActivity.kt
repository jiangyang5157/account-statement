package com.gmail.jiangyang5157.account_statement.ui.statement

import android.util.Log
import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class ChartFlutterActivity : FlutterActivity() {

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        val dataInt = intent.extras?.getInt(keyDataInt, -1)
        Log.d("####", "configureFlutterEngine ${flutterEngine}\ndataint=$dataInt")

        MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            methodChannel
        ).setMethodCallHandler { call, result ->
            if (call.method == "intChannel") {
                if (dataInt != null && dataInt >= 0) {
                    result.success(dataInt)
                } else {
                    result.error(dataInt.toString(), "dataInt is less than 0", dataInt)
                }
            } else {
                result.notImplemented()
            }
        }
    }

    companion object {
        const val methodChannel = "com.gmail.jiangyang5157.account_statement/data"
        const val keyDataInt = "keyDataInt"
    }
}
