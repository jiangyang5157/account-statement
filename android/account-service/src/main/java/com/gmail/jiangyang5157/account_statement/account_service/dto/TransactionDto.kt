package com.gmail.jiangyang5157.account_statement.account_service.dto

import com.gmail.jiangyang5157.account_statement.account_cvo.TransactionEntity
import com.gmail.jiangyang5157.kotlin_kit.data.model.Converter
import com.gmail.jiangyang5157.kotlin_kit.data.model.finance.Money
import com.gmail.jiangyang5157.kotlin_kit.utils.RegexUtils
import com.google.gson.*
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

data class TransactionDto(

    @field:SerializedName("accountName")
    val accountName: String,

    @field:SerializedName("date")
    @JsonAdapter(value = DateStringJsonSerializer::class)
    val date: Date,

    @field:SerializedName("money")
    @JsonAdapter(value = TransactionEntity.MoneyDoubleJsonSerializer::class)
    val money: Money,

    @field:SerializedName("description")
    val description: String
) {
    class DateStringJsonSerializer : JsonSerializer<Date>, JsonDeserializer<Date> {

        private val pattern: String = RegexUtils.DATE_DMY

        override fun serialize(
            date: Date?,
            typeOfSrc: Type?,
            context: JsonSerializationContext?
        ): JsonElement {
            return DateStringConverter(pattern).forward(date)?.let { JsonPrimitive(it) }
                ?: throw IllegalArgumentException("Cannot serialize $date to [JsonElement]")
        }

        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): Date {
            return json?.asString?.let { DateStringConverter(pattern).backward(it) }
                ?: throw IllegalArgumentException("Cannot deserialize $json to [Date]")
        }
    }

    class DateStringConverter(private val pattern: String) : Converter<Date, String> {

        override fun backward(b: String?): Date? =
            b?.let {
                try {
                    SimpleDateFormat(pattern, Locale.getDefault()).parse(it.trim())
                } catch (e: ParseException) {
                    null
                }
            }

        override fun forward(a: Date?): String? =
            a?.let { SimpleDateFormat(pattern, Locale.getDefault()).format(it) }
    }
}