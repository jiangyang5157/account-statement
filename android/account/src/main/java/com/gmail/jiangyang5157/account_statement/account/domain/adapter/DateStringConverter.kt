package com.gmail.jiangyang5157.account_statement.account.domain.adapter

import androidx.room.TypeConverter
import com.gmail.jiangyang5157.kotlin_kit.data.model.Mapper
import com.gmail.jiangyang5157.kotlin_kit.utils.RegexUtils
import java.text.SimpleDateFormat
import java.util.*

object DateStringConverter {

    @TypeConverter
    @JvmStatic
    fun dateToString(src: Date?): String? {
        return dateToString(src, RegexUtils.DATE_DMY)
    }

    @TypeConverter
    @JvmStatic
    fun stringToDate(src: String?): Date? {
        return stringToDate(src, RegexUtils.DATE_DMY)
    }

    fun dateToString(src: Date?, pattern: String): String? {
        return src?.let {
            SimpleDateFormat(
                pattern,
                Locale.getDefault()
            ).format(it)
        }
    }

    fun stringToDate(src: String?, pattern: String): Date? {
        return src?.let {
            SimpleDateFormat(
                pattern,
                Locale.getDefault()
            ).parse(it.trim())
        }
    }
}
