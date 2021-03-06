package com.gmail.jiangyang5157.account_statement.bank_domain.entity

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.gmail.jiangyang5157.kotlin_kit.data.model.Converter
import com.gmail.jiangyang5157.kotlin_kit.data.model.finance.Money
import com.google.gson.*
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import java.lang.reflect.Type
import java.util.*

@Entity(
    tableName = "transactions",
    foreignKeys = [
        ForeignKey(
            entity = AccountEntity::class,
            parentColumns = ["name"],
            childColumns = ["accountName"],
            onDelete = CASCADE,
            onUpdate = CASCADE
        )
    ]
)
data class TransactionEntity(

    @ColumnInfo(name = "accountName")
    @field:SerializedName("accountName")
    val accountName: String,

    @ColumnInfo(name = "date")
    @field:SerializedName("date")
    val date: Date,

    @ColumnInfo(name = "money")
    @field:SerializedName("money")
    @JsonAdapter(value = MoneyDoubleJsonSerializer::class)
    val money: Money,

    @ColumnInfo(name = "description")
    @field:SerializedName("description")
    val description: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) {
    class MoneyDoubleJsonSerializer : JsonSerializer<Money>, JsonDeserializer<Money> {

        @Throws(IllegalArgumentException::class)
        override fun serialize(
            money: Money?,
            typeOfSrc: Type?,
            context: JsonSerializationContext?
        ): JsonElement {
            return MoneyDoubleConverter().forward(money)?.let { JsonPrimitive(it) }
                ?: throw IllegalArgumentException("Cannot serialize $money to [JsonElement]")
        }

        @Throws(IllegalArgumentException::class)
        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): Money {
            return json?.asDouble?.let { MoneyDoubleConverter().backward(it) }
                ?: throw IllegalArgumentException("Cannot deserialize $json to [Money]")
        }
    }

    class MoneyDoubleConverter : Converter<Money, Double> {

        @TypeConverter
        override fun backward(b: Double?): Money? = b?.let { Money(it) }

        @TypeConverter
        override fun forward(a: Money?): Double? = a?.amount?.toDouble()
    }

    class DateLongConverter : Converter<Date, Long> {

        @TypeConverter
        override fun backward(b: Long?): Date? = b?.let { Date(it) }

        @TypeConverter
        override fun forward(a: Date?): Long? = a?.time
    }
}
