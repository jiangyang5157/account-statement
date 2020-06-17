package com.gmail.jiangyang5157.account_statement.account.domain.adapter

import androidx.room.TypeConverter
import com.gmail.jiangyang5157.kotlin_kit.data.adapter.DateLongConverter
import java.util.*

class DateLongTypeConverter {

    @TypeConverter
    fun dateToLong(src: Date?): Long? {
        return DateLongConverter().forward(src)
    }

    @TypeConverter
    fun longToDate(src: Long?): Date? {
        return DateLongConverter().backward(src)
    }
}
