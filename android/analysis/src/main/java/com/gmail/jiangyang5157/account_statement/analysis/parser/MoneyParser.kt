package com.gmail.jiangyang5157.account_statement.analysis.parser

import com.gmail.jiangyang5157.kotlin_kit.model.Mapper
import com.gmail.jiangyang5157.kotlin_kit.model.finance.Money

object MoneyParser : Mapper<CharSequence, Money> {

    override fun map(from: CharSequence): Money {
        try {
            return Money(from.trim().toString().toDouble())
        } catch (e: Exception) {
            when (e) {
                is NumberFormatException,
                is IllegalArgumentException -> {
                    throw IllegalArgumentException("Cannot map $from to [Money]")
                }
                else -> throw e
            }
        }
    }
}
