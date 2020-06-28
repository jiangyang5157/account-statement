package com.gmail.jiangyang5157.account_statement.bank_presentation.ui.statement

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import com.gmail.jiangyang5157.account_statement.bank_presentation.ui.binding.SpendItem
import com.gmail.jiangyang5157.account_statement.bank_presentation.ui.binding.SpendItemViewBinder
import com.gmail.jiangyang5157.adapter.multitype.MultiTypeAdapter
import com.gmail.jiangyang5157.adapter.multitype.SimpleRecycleViewAdapter
import com.gmail.jiangyang5157.adapter.ui.SimpleRecycleView

class StatementRecycleView : SimpleRecycleView {

    override val recycleViewAdapter: SimpleRecycleViewAdapter = MultiTypeAdapter()
        .register(SpendItem::class.java, SpendItemViewBinder())

    constructor(context: Context) :
        super(context)

    constructor(context: Context, attrs: AttributeSet?) :
        super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int) :
        super(context, attrs, defStyleAttr)
}
