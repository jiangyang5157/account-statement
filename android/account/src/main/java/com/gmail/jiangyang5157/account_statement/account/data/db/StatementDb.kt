package com.gmail.jiangyang5157.account_statement.account.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gmail.jiangyang5157.account_statement.account.domain.entity.AccountEntity
import com.gmail.jiangyang5157.account_statement.account.domain.entity.Converters
import com.gmail.jiangyang5157.account_statement.account.domain.entity.TransactionEntity

@Database(
    entities = [
        AccountEntity::class,
        TransactionEntity::class
    ],
    version = 1
)
@TypeConverters(value = [Converters::class])
abstract class StatementDb : RoomDatabase() {

    abstract fun statementDao(): StatementDao
}
