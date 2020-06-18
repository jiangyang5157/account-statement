package com.gmail.jiangyang5157.account_statement.bank.anz//package com.gmail.jiangyang5157.account_statement.account.data.csv.adapter
//
//import com.gmail.jiangyang5157.account_statement.account.data.csv.model.AnzSavingTransaction
//import com.gmail.jiangyang5157.kotlin_kit.data.model.Mapper
//import com.gmail.jiangyang5157.kotlin_kit.utils.IoUtils
//import com.gmail.jiangyang5157.kotlin_kit.utils.RegexUtils
//import timber.log.Timber
//import java.io.InputStream
//
//class AnzSavingsParser : Mapper<InputStream, List<AnzSavingTransaction>> {
//
//    override fun map(from: InputStream): List<AnzSavingTransaction> {
//        val ret = mutableListOf<AnzSavingTransaction>()
//        IoUtils.read(from, object : IoUtils.OnReadingListener {
//            override fun onReadLine(line: CharSequence?): Boolean {
//                return line?.run {
//                    val transaction = AnzSavingTransaction()
//                    val regex = ",".toRegex()
//                    split(regex).forEachIndexed { index, value ->
//                        try {
//                            when (index) {
//                                transaction.date.index -> transaction.date.value =
//                                    DateStringConverter.stringToDate(value, RegexUtils.DATE_DMY)
//                                transaction.money.index -> transaction.money.value =
//                                    MoneyStringConverter.stringToMoney(value)
//                                transaction.type.index -> transaction.type.value =
//                                    value.trim()
//                                transaction.details.index -> transaction.details.value =
//                                    value.trim()
//                                else -> Timber.tag(TAG)
//                                    .d("Skip value \"$value\" at index \"$index\"")
//                            }
//                        } catch (e: IllegalArgumentException) {
//                            Timber.tag(TAG)
//                                .d("Invalid value \"$value\" at index \"$index\", skip line \"$line\"")
//                            return true
//                        }
//                    }
//                    if (transaction.date.value != null &&
//                        transaction.money.value != null &&
//                        transaction.type.value != null &&
//                        transaction.details.value != null
//                    ) {
//                        ret.add(transaction)
//                    } else {
//                        Timber.tag(TAG)
//                            .d("Incomplete transaction \"$transaction\" at line \"$line\", skip it")
//                    }
//                    true
//                } ?: false
//            }
//        })
//        return ret
//    }
//
//    companion object {
//        const val TAG = "AnzSavingsParser"
//    }
//}
