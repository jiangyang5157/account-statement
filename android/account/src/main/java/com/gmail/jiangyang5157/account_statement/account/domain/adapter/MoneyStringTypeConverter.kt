package com.gmail.jiangyang5157.account_statement.account.domain.adapter

import androidx.room.TypeConverter
import com.gmail.jiangyang5157.kotlin_kit.data.adapter.MoneyStringConverter
import com.gmail.jiangyang5157.kotlin_kit.data.model.finance.Money

class MoneyStringTypeConverter {

    @TypeConverter
    fun moneyToString(src: Money?): String? {
        return MoneyStringConverter().forward(src)
    }

    @TypeConverter
    fun stringToMoney(src: String?): Money? {
        return MoneyStringConverter().backward(src)
    }
}
