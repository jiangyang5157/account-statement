package com.gmail.jiangyang5157.account_statement.account_cvo.adapter

import com.gmail.jiangyang5157.kotlin_kit.data.model.Converter
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

open class DateStringConverter(private val pattern: String) : Converter<Date, String> {

    override fun forward(a: Date?): String? {
        return a?.let {
            SimpleDateFormat(
                pattern,
                Locale.getDefault()
            ).format(it)
        }
    }

    override fun backward(b: String?): Date? {
        return b?.let {
            try {
                SimpleDateFormat(
                    pattern,
                    Locale.getDefault()
                ).parse(it.trim())
            } catch (e: ParseException) {
                null
            }
        }
    }
}
