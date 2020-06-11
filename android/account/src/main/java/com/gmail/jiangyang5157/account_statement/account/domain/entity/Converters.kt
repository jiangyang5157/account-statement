package com.gmail.jiangyang5157.account_statement.account.domain.entity

import androidx.room.TypeConverter
import com.gmail.jiangyang5157.kotlin_kit.model.finance.Money
import com.gmail.jiangyang5157.kotlin_kit.utils.RegexUtils
import com.google.gson.*
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

object Converters {

    @TypeConverter
    fun dateToString(src: Date?): String? {
        return src?.let {
            SimpleDateFormat(
                RegexUtils.DATE_DMY,
                Locale.getDefault()
            ).format(it)
        }
    }

    @TypeConverter
    fun stringToDate(src: String?): Date? {
        return src?.let {
            SimpleDateFormat(
                RegexUtils.DATE_DMY,
                Locale.getDefault()
            ).parse(it.trim())
        }
    }

    @TypeConverter
    fun moneyToString(src: Money?): String? {
        return src?.amount?.toString()
    }

    @TypeConverter
    fun stringToMoney(src: String?): Money? {
        return src?.let { Money(src.trim().toDouble()) }
    }
}

class DateJsonAdapter : JsonSerializer<Date>, JsonDeserializer<Date> {

    override fun serialize(
        src: Date?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return Converters.dateToString(src)?.let {
            JsonPrimitive(it)
        } ?: throw IllegalArgumentException("Cannot serialize $src to [JsonElement]")
    }

    override fun deserialize(
        src: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Date {
        return Converters.stringToDate(src?.asString)
            ?: throw IllegalArgumentException("Cannot deserialize $src to [Date]")
    }
}

class MoneyJsonAdapter : JsonSerializer<Money>, JsonDeserializer<Money> {

    override fun serialize(
        src: Money?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return Converters.moneyToString(src)?.let {
            JsonPrimitive(it)
        } ?: throw IllegalArgumentException("Cannot serialize $src to [JsonElement]")
    }

    override fun deserialize(
        src: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Money {
        return Converters.stringToMoney(src?.asString)
            ?: throw IllegalArgumentException("Cannot deserialize $src to [Money]")
    }
}
