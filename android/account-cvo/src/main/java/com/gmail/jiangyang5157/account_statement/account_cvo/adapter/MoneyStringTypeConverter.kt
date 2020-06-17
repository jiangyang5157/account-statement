package com.gmail.jiangyang5157.account_statement.account_cvo.adapter

import androidx.room.TypeConverter
import com.gmail.jiangyang5157.kotlin_kit.data.model.finance.Money

object MoneyStringTypeConverter {

    @TypeConverter
    @JvmStatic
    fun moneyToString(src: Money?): String? {
        return MoneyStringConverter().forward(src)
    }

    @TypeConverter
    @JvmStatic
    fun stringToMoney(src: String?): Money? {
        return MoneyStringConverter().backward(src)
    }
}
