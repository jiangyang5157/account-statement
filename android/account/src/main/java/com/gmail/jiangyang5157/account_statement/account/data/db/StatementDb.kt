//package com.gmail.jiangyang5157.account_statement.account.data.db
//
//import androidx.room.Database
//import androidx.room.RoomDatabase
//import androidx.room.TypeConverters
//import com.gmail.jiangyang5157.account_statement.account.domain.adapter.MoneyStringTypeConverter
//import com.gmail.jiangyang5157.account_statement.account_cvo.AccountEntity
//import com.gmail.jiangyang5157.account_statement.account_cvo.TransactionEntity
//import com.gmail.jiangyang5157.kotlin_kit.data.adapter.DateStringConverter
//import com.gmail.jiangyang5157.kotlin_kit.data.adapter.MoneyStringConverter
//
//@Database(
//    entities = [
//        AccountEntity::class,
//        TransactionEntity::class
//    ],
//    version = 1
//)
//@TypeConverters(
//    value = [
//        DateStringTypeConverter::class,asd
//        MoneyStringTypeConverter::class
//    ]
//)
//abstract class StatementDb : RoomDatabase() {
//
//    abstract fun statementDao(): StatementDao
//}
