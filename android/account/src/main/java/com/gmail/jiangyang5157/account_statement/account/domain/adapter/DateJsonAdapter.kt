package com.gmail.jiangyang5157.account_statement.account.domain.adapter

import com.google.gson.*
import java.lang.reflect.Type
import java.util.*

class DateJsonAdapter : JsonSerializer<Date>, JsonDeserializer<Date> {

    override fun serialize(
        src: Date?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return DateStringConverter.dateToString(
            src
        )?.let {
            JsonPrimitive(it)
        } ?: throw IllegalArgumentException("Cannot serialize $src to [JsonElement]")
    }

    override fun deserialize(
        src: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Date {
        return DateStringConverter.stringToDate(
            src?.asString
        )
            ?: throw IllegalArgumentException("Cannot deserialize $src to [Date]")
    }
}
