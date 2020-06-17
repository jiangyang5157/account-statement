package com.gmail.jiangyang5157.account_statement.account_cvo.adapter

import com.gmail.jiangyang5157.kotlin_kit.data.model.finance.Money
import com.google.gson.*
import java.lang.reflect.Type

open class MoneyJsonSerializer : JsonSerializer<Money>, JsonDeserializer<Money> {

    override fun serialize(
        src: Money?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return MoneyStringConverter().forward(
            src
        )?.let {
            JsonPrimitive(it)
        } ?: throw IllegalArgumentException("Cannot serialize $src to [JsonElement]")
    }

    override fun deserialize(
        src: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Money {
        return MoneyStringConverter().backward(
            src?.asString
        ) ?: throw IllegalArgumentException("Cannot deserialize $src to [Money]")
    }
}
