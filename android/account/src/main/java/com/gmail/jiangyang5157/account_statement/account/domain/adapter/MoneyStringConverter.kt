package com.gmail.jiangyang5157.account_statement.account.domain.adapter

import androidx.room.TypeConverter
import com.gmail.jiangyang5157.kotlin_kit.data.model.finance.Money

object MoneyStringConverter {

    @TypeConverter
    @JvmStatic
    fun moneyToString(src: Money?): String? {
        return src?.amount?.toString()
    }

    @TypeConverter
    @JvmStatic
    fun stringToMoney(src: String?): Money? {
        return src?.let { Money(src.trim().toDouble()) }
    }
}
