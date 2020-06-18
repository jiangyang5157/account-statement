//package com.gmail.jiangyang5157.account_statement.bank.asb
//
//import com.gmail.jiangyang5157.kotlin_kit.data.model.finance.Money
//import java.util.*
//
///**
// * 9th June, 2020:
// *
// * Date,Unique Id,Tran Type,Cheque Number,Payee,Memo,Amount
// */
//class AsbSavingTransaction : CsvTransaction {
//
//    override var date =
//        CsvField<Date>(
//            0
//        )
//
//    var memo =
//        CsvField<String>(
//            5
//        )
//
//    override var money =
//        CsvField<Money>(
//            6
//        )
//
//    override val description: String
//        get() = "${memo.value}"
//}
