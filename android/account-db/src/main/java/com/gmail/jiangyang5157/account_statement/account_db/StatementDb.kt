package com.gmail.jiangyang5157.account_statement.account_db//package com.gmail.jiangyang5157.account_statement.account.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gmail.jiangyang5157.account_statement.account_cvo.AccountEntity
import com.gmail.jiangyang5157.account_statement.account_cvo.TransactionEntity

@Database(
    entities = [
        AccountEntity::class,
        TransactionEntity::class
    ],
    version = 1
)
@TypeConverters(
    value = [
        TransactionEntity.MoneyDoubleTypeConverter::class,
        TransactionEntity.DateLongTypeConverter::class
    ]
)
abstract class StatementDb : RoomDatabase() {

    abstract fun statementDao(): StatementDao
}
