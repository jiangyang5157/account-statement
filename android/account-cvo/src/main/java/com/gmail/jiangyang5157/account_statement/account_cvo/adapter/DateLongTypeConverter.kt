package com.gmail.jiangyang5157.account_statement.account_cvo.adapter

import androidx.room.TypeConverter
import java.util.*

object DateLongTypeConverter {

    @TypeConverter
    @JvmStatic
    fun dateToLong(src: Date?): Long? {
        return DateLongConverter().forward(src)
    }

    @TypeConverter
    @JvmStatic
    fun longToDate(src: Long?): Date? {
        return DateLongConverter().backward(src)
    }
}
