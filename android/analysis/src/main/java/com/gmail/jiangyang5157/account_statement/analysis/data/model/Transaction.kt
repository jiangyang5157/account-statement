package com.gmail.jiangyang5157.account_statement.analysis.data.model

import com.gmail.jiangyang5157.account_statement.analysis.data.model.Field
import com.gmail.jiangyang5157.kotlin_kit.model.finance.Money
import java.util.*

interface Transaction {

    var date: Field<Date>

    var money: Field<Money>

    val description: String
}
