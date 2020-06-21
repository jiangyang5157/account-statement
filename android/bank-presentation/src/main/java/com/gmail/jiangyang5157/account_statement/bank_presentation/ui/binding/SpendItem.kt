package com.gmail.jiangyang5157.account_statement.bank_presentation.ui.binding

import android.view.View
import com.gmail.jiangyang5157.kotlin_kit.data.model.finance.Money

data class SpendItem(
    val description: String,
    val spend: Money,
    val onClickListener: View.OnClickListener? = null
)
