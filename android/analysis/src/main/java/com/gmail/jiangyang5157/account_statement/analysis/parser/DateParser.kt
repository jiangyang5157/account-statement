package com.gmail.jiangyang5157.account_statement.analysis.parser

import com.gmail.jiangyang5157.kotlin_kit.model.Mapper
import com.gmail.jiangyang5157.kotlin_kit.utils.RegexUtils
import java.text.ParseException
import java.util.*
import java.text.SimpleDateFormat

object DateParser : Mapper<CharSequence, Date> {

    override fun map(from: CharSequence): Date {
        return try {
            SimpleDateFormat(
                RegexUtils.DATE_DMY,
                Locale.getDefault()
            ).parse(from.trim().toString())
        } catch (e: Exception) {
            when (e) {
                is ParseException -> {
                    throw IllegalArgumentException("Cannot map $from to [Money]")
                }
                else -> throw e
            }
        }
    }
}
