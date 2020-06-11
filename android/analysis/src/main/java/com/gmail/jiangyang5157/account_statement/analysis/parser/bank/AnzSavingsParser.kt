package com.gmail.jiangyang5157.account_statement.analysis.parser.bank

import com.gmail.jiangyang5157.account_statement.analysis.domain.entity.Field
import com.gmail.jiangyang5157.account_statement.analysis.domain.entity.Transaction
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
 * Type,Details,Particulars,Code,Reference,Amount,Date,ForeignCurrencyAmount,ConversionCharge
 */
class AnzSavingTransaction : Transaction {

    var type = Field<String>(0)

    var details = Field<String>(1)

    override var money = Field<Money>(5)

    override var date = Field<Date>(6)

    override val description: String
        get() = "${type.value}, ${details.value}"
}

class AnzSavingsParser : Mapper<InputStream, List<AnzSavingTransaction>> {

    override fun map(from: InputStream): List<AnzSavingTransaction> {
        val ret = mutableListOf<AnzSavingTransaction>()
        IoUtils.read(from, object : IoUtils.OnReadingListener {
            override fun onReadLine(line: CharSequence?): Boolean {
                return line?.run {
                    val transaction = AnzSavingTransaction()
                    val regex = ",".toRegex()
                    split(regex).forEachIndexed { index, value ->
                        try {
                            when (index) {
                                transaction.date.index -> transaction.date.value =
                                    DateParser(RegexUtils.DATE_DMY).map(value)
                                transaction.money.index -> transaction.money.value =
                                    MoneyParser.map(value)
                                transaction.type.index -> transaction.type.value =
                                    StringParser.map(value)
                                transaction.details.index -> transaction.details.value =
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
                        transaction.type.value != null &&
                        transaction.details.value != null
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
        const val TAG = "AnzSavingsParser"
    }
}
