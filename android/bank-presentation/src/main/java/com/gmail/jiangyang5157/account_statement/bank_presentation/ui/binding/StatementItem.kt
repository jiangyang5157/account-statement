package com.gmail.jiangyang5157.account_statement.bank_presentation.ui.binding

import android.view.View
import com.gmail.jiangyang5157.account_statement.bank_domain.entity.StatementEntity

data class StatementItem(
    val statement: StatementEntity,
    val onClickListener: View.OnClickListener? = null
)
