package com.gmail.jiangyang5157.account_statement.bank_presentation.parser.anz

import com.gmail.jiangyang5157.account_statement.bank_presentation.parser.CsvField
import com.gmail.jiangyang5157.account_statement.bank_presentation.parser.CsvTransaction
import com.gmail.jiangyang5157.kotlin_kit.data.model.finance.Money
import java.util.*

/**
 * 9th June, 2020:
 *
 * Card,Type,Amount,Details,TransactionDate,ProcessedDate,ForeignCurrencyAmount,ConversionCharge
 */
class AnzCreditTransaction : CsvTransaction {

    var type =
        CsvField<String>(
            1
        )

    override var money =
        CsvField<Money>(
            2
        )

    var details =
        CsvField<String>(
            3
        )

    override var date =
        CsvField<Date>(
            4
        )

    override val description: String
        get() = "${details.value}"

    override fun validate(): Boolean {
        return super.validate() && type.value != null
    }
}
