package com.gmail.jiangyang5157.account_statement.bank_presentation.parser

import com.gmail.jiangyang5157.kotlin_kit.data.model.finance.Money
import java.util.*

interface CsvTransaction {

    var date: CsvField<Date>

    var money: CsvField<Money>

    val description: String

    fun validate(): Boolean {
        return date.value != null && money.value != null
    }
}
