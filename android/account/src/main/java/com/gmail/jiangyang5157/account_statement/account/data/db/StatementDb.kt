package com.gmail.jiangyang5157.account_statement.account.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gmail.jiangyang5157.account_statement.account.domain.entity.AccountEntity
import com.gmail.jiangyang5157.account_statement.account.domain.adapter.DateStringConverter
import com.gmail.jiangyang5157.account_statement.account.domain.adapter.MoneyStringConverter
import com.gmail.jiangyang5157.account_statement.account.domain.entity.TransactionEntity

@Database(
    entities = [
        AccountEntity::class,
        TransactionEntity::class
    ],
    version = 1
)
@TypeConverters(
    value = [
        DateStringConverter::class,
        MoneyStringConverter::class
    ]
)
abstract class StatementDb : RoomDatabase() {

    abstract fun statementDao(): StatementDao
}
