package com.gmail.jiangyang5157.account_statement.account_cvo.adapter

import com.google.gson.*
import java.lang.reflect.Type
import java.util.*

abstract class DateJsonSerializer : JsonSerializer<Date>, JsonDeserializer<Date> {

    abstract val pattern: String

    override fun serialize(
        src: Date?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return DateStringConverter(
            pattern
        ).forward(
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
        return DateStringConverter(
            pattern
        ).backward(
            src?.asString
        ) ?: throw IllegalArgumentException("Cannot deserialize $src to [Date]")
    }
}
