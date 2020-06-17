package com.gmail.jiangyang5157.account_statement.account_cvo.adapter

import com.gmail.jiangyang5157.kotlin_kit.data.model.Converter
import java.util.*

open class DateLongConverter : Converter<Date, Long> {

    override fun forward(a: Date?): Long? {
        return a?.time
    }

    override fun backward(b: Long?): Date? {
        return b?.let { Date(it) }
    }
}
