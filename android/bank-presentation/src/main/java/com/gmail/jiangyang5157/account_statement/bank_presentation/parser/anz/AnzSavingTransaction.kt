package com.gmail.jiangyang5157.account_statement.bank_presentation.parser.anz

import com.gmail.jiangyang5157.account_statement.bank_presentation.parser.CsvField
import com.gmail.jiangyang5157.account_statement.bank_presentation.parser.CsvTransaction
import com.gmail.jiangyang5157.kotlin_kit.data.model.finance.Money
import java.util.*

/**
 * 9th June, 2020:
 *
 * Type,Details,Particulars,Code,Reference,Amount,Date,ForeignCurrencyAmount,ConversionCharge
 */
class AnzSavingTransaction : CsvTransaction {

    var type =
        CsvField<String>(
            0
        )

    var details =
        CsvField<String>(
            1
        )

    override var money =
        CsvField<Money>(
            5
        )

    override var date =
        CsvField<Date>(
            6
        )

    override val description: String
        get() = "${type.value}, ${details.value}"

    override fun validate(): Boolean {
        return super.validate()
    }
}
