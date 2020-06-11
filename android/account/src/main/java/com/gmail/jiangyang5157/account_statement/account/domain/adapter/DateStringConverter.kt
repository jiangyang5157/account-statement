package com.gmail.jiangyang5157.account_statement.account.domain.adapter

import androidx.room.TypeConverter
import com.gmail.jiangyang5157.kotlin_kit.utils.RegexUtils
import java.text.SimpleDateFormat
import java.util.*

object DateStringConverter {

    @TypeConverter
    @JvmStatic
    fun dateToString(src: Date?): String? {
        return src?.let {
            SimpleDateFormat(
                RegexUtils.DATE_DMY,
                Locale.getDefault()
            ).format(it)
        }
    }

    @TypeConverter
    @JvmStatic
    fun stringToDate(src: String?): Date? {
        return src?.let {
            SimpleDateFormat(
                RegexUtils.DATE_DMY,
                Locale.getDefault()
            ).parse(it.trim())
        }
    }
}
