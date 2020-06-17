//package com.gmail.jiangyang5157.account_statement.account.data.csv.adapter
//
//import com.gmail.jiangyang5157.account_statement.account.data.csv.model.CsvTransaction
//import com.gmail.jiangyang5157.account_statement.account_cvo.TransactionEntity
//
//object CsvTransactionEntityConverter {
//
//    fun csvTransactionToEntity(accountName: String, src: CsvTransaction?): TransactionEntity? {
//        return src?.let {
//            val date = it.date.value
//            val money = it.money.value
//            if (date != null && money != null) {
//                TransactionEntity(
//                    accountName = accountName,
//                    date = date,
//                    money = money,
//                    description = it.description
//                )
//            } else {
//                null
//            }
//        }
//    }
//}
