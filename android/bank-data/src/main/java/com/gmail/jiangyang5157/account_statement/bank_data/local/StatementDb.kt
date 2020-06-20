package com.gmail.jiangyang5157.account_statement.bank_data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gmail.jiangyang5157.account_statement.bank_domain.entity.AccountEntity
import com.gmail.jiangyang5157.account_statement.bank_domain.entity.TransactionEntity

@Database(
    entities = [
        AccountEntity::class,
        TransactionEntity::class
    ],
    version = 1
)
@TypeConverters(
    value = [
        TransactionEntity.MoneyDoubleConverter::class,
        TransactionEntity.DateLongConverter::class
    ]
)
abstract class StatementDb : RoomDatabase() {

    abstract fun statementDao(): StatementDao

    // TODO remove
    class Builder {
        fun build(applicationContext: Context): StatementDb {
            return Room.databaseBuilder(
                applicationContext,
                StatementDb::class.java, "statement"
            ).build()
        }
    }
}

