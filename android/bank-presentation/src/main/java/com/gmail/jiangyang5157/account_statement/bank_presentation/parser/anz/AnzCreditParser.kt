package com.gmail.jiangyang5157.account_statement.bank_presentation.parser.anz

import com.gmail.jiangyang5157.account_statement.bank_data.remote.dto.TransactionDto
import com.gmail.jiangyang5157.account_statement.bank_domain.entity.TransactionEntity
import com.gmail.jiangyang5157.account_statement.bank_presentation.parser.Parser
import com.gmail.jiangyang5157.kotlin_kit.data.model.finance.Money
import com.gmail.jiangyang5157.kotlin_kit.utils.IoUtils
import com.gmail.jiangyang5157.kotlin_kit.utils.RegexUtils
import timber.log.Timber
import java.io.InputStream

class AnzCreditParser : Parser<InputStream, List<AnzCreditTransaction>> {

    override fun parse(from: InputStream?): List<AnzCreditTransaction>? {
        val ret = mutableListOf<AnzCreditTransaction>()
        if (from == null) {
            return ret
        }

        IoUtils.read(from, object : IoUtils.OnReadingListener {
            override fun onReadLine(line: CharSequence?): Boolean {
                return line?.run {
                    val transaction = AnzCreditTransaction()
                    val regex = ",".toRegex()
                    split(regex).forEachIndexed { index, value ->
                        try {
                            when (index) {
                                transaction.date.index -> transaction.date.value =
                                    TransactionDto.DateStringConverter(RegexUtils.DATE_DMY)
                                        .backward(value)
                                transaction.money.index -> transaction.money.value =
                                    TransactionEntity.MoneyDoubleConverter()
                                        .backward(value.toDouble())
                                transaction.type.index -> transaction.type.value =
                                    value.trim()
                                transaction.details.index -> transaction.details.value =
                                    value.trim()
                                else -> Timber.tag(TAG)
                                    .d("Skip value \"$value\" at index \"$index\"")
                            }
                        } catch (e: IllegalArgumentException) {
                            Timber.tag(TAG).d(
                                "Invalid value \"$value\" at index \"$index\", skip line \"$line\""
                            )
                            return true
                        }
                    }
                    if (transaction.validate()) {
                        if (transaction.type.value == "D") {
                            transaction.money.value = transaction.money.value.let {
                                if (it != null) {
                                    Money(-it.amount, it.currency)
                                } else {
                                    it
                                }
                            }
                        }
                        ret.add(transaction)
                    } else {
                        Timber.tag(TAG).d(
                            "Invalid transaction \"$transaction\" at line \"$line\", skip it"
                        )
                    }
                    true
                } ?: false
            }
        })
        return ret
    }

    companion object {
        const val TAG = "AnzCreditParser"
    }
}
