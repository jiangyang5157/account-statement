package com.gmail.jiangyang5157.account_statement.account_cvo.adapter

import com.gmail.jiangyang5157.kotlin_kit.data.model.Converter
import com.gmail.jiangyang5157.kotlin_kit.data.model.finance.Money

open class MoneyStringConverter : Converter<Money, String> {

    override fun forward(a: Money?): String? {
        return a?.amount?.toString()
    }

    override fun backward(b: String?): Money? {
        return b?.let {
            try {
                Money(it.trim().toDouble())
            } catch (e: NumberFormatException) {
                null
            }
        }
    }
}
