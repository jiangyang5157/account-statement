package com.gmail.jiangyang5157.account_statement.bank_presentation.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.AttrRes
import com.gmail.jiangyang5157.account_statement.bank_presentation.ui.binding.SpendItem
import com.gmail.jiangyang5157.account_statement.bank_presentation.ui.binding.SpendItemViewBinder
import com.gmail.jiangyang5157.adapter.multitype.MultiTypeAdapter
import com.gmail.jiangyang5157.adapter.multitype.SimpleRecycleViewAdapter
import com.gmail.jiangyang5157.adapter.ui.SimpleRecycleView
import com.gmail.jiangyang5157.core.ui.widget.Widget

class SpendRecycleView : Widget, SimpleRecycleView {

    override fun view(): View = this

    override val recycleViewAdapter: SimpleRecycleViewAdapter = MultiTypeAdapter()
        .register(SpendItem::class.java, SpendItemViewBinder())

    private constructor(context: Context) :
        super(context)

    constructor(context: Context, attrs: AttributeSet?) :
        super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int) :
        super(context, attrs, defStyleAttr)

    class Builder : Widget.Builder<SpendRecycleView> {

        override fun build(context: Context): SpendRecycleView {
            return SpendRecycleView(
                context
            )
        }
    }
}
