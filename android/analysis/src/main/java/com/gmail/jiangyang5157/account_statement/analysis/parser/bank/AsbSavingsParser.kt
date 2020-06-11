package com.gmail.jiangyang5157.account_statement.analysis.parser.bank

import com.gmail.jiangyang5157.account_statement.analysis.data.model.Field
import com.gmail.jiangyang5157.account_statement.analysis.data.model.Transaction
import com.gmail.jiangyang5157.account_statement.analysis.parser.MoneyParser
import com.gmail.jiangyang5157.account_statement.analysis.parser.DateParser
import com.gmail.jiangyang5157.account_statement.analysis.parser.StringParser
import com.gmail.jiangyang5157.kotlin_kit.model.Mapper
import com.gmail.jiangyang5157.kotlin_kit.model.finance.Money
import com.gmail.jiangyang5157.kotlin_kit.utils.IoUtils
import com.gmail.jiangyang5157.kotlin_kit.utils.RegexUtils
import timber.log.Timber
import java.io.InputStream
import java.util.*

/**
 * 9th June, 2020:
 *
 * Date,Unique Id,Tran Type,Cheque Number,Payee,Memo,Amount
 */
class AsbSavingTransaction :
    Transaction {

    override var date =
        Field<Date>(
            0
        )

    var memo =
        Field<String>(
            5
        )

    override var money =
        Field<Money>(
            6
        )

    override val description: String
        get() = "${memo.value}"
}

class AsbSavingsParser : Mapper<InputStream, List<AsbSavingTransaction>> {

    override fun map(from: InputStream): List<AsbSavingTransaction> {
        val ret = mutableListOf<AsbSavingTransaction>()
        IoUtils.read(from, object : IoUtils.OnReadingListener {
            override fun onReadLine(line: CharSequence?): Boolean {
                return line?.run {
                    val transaction = AsbSavingTransaction()
                    val regex = ",".toRegex()
                    split(regex).forEachIndexed { index, value ->
                        try {
                            when (index) {
                                transaction.date.index -> transaction.date.value =
                                    DateParser(RegexUtils.DATE_YMD).map(value)
                                transaction.money.index -> transaction.money.value =
                                    MoneyParser.map(value)
                                transaction.memo.index -> transaction.memo.value =
                                    StringParser.map(value)
                                else -> Timber.tag(TAG)
                                    .d("Skip value \"$value\" at index \"$index\"")
                            }
                        } catch (e: IllegalArgumentException) {
                            Timber.tag(TAG)
                                .d("Invalid value \"$value\" at index \"$index\", skip line \"$line\"")
                            return true
                        }
                    }
                    if (transaction.date.value != null &&
                        transaction.money.value != null &&
                        transaction.memo.value != null
                    ) {
                        ret.add(transaction)
                    } else {
                        Timber.tag(TAG)
                            .d("Incomplete transaction \"$transaction\" at line \"$line\", skip it")
                    }
                    true
                } ?: false
            }
        })
        return ret
    }

    companion object {
        const val TAG = "AsbSavingsParser"
    }
}
