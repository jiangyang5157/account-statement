package com.gmail.jiangyang5157.account_statement.bank_presentation.ui.transaction

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import com.gmail.jiangyang5157.account_statement.bank_presentation.ui.binding.TransactionItem
import com.gmail.jiangyang5157.account_statement.bank_presentation.ui.binding.TransactionItemViewBinder
import com.gmail.jiangyang5157.adapter.multitype.MultiTypeAdapter
import com.gmail.jiangyang5157.adapter.multitype.SimpleRecycleViewAdapter
import com.gmail.jiangyang5157.adapter.ui.SimpleRecycleView

class TransactionsRecycleView : SimpleRecycleView {

    override val recycleViewAdapter: SimpleRecycleViewAdapter = MultiTypeAdapter()
        .register(TransactionItem::class.java, TransactionItemViewBinder())

    constructor(context: Context) :
        super(context)

    constructor(context: Context, attrs: AttributeSet?) :
        super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int) :
        super(context, attrs, defStyleAttr)
}
