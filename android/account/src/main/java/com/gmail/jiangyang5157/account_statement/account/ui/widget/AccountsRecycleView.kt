package com.gmail.jiangyang5157.account_statement.account.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.AttrRes
import com.gmail.jiangyang5157.account_statement.account.ui.bind.AccountItem
import com.gmail.jiangyang5157.account_statement.account.ui.bind.AccountItemViewBinder
import com.gmail.jiangyang5157.adapter.multitype.MultiTypeAdapter
import com.gmail.jiangyang5157.adapter.multitype.SimpleRecycleViewAdapter
import com.gmail.jiangyang5157.adapter.ui.SimpleRecycleView
import com.gmail.jiangyang5157.core.ui.widget.Widget

class AccountsRecycleView : Widget, SimpleRecycleView {

    override fun view(): View = this

    override val recycleViewAdapter: SimpleRecycleViewAdapter = MultiTypeAdapter()
        .register(AccountItem::class.java, AccountItemViewBinder())

    private constructor(context: Context) :
        super(context)

    constructor(context: Context, attrs: AttributeSet?) :
        super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int) :
        super(context, attrs, defStyleAttr)

    class Builder : Widget.Builder<AccountsRecycleView> {

        override fun build(context: Context): AccountsRecycleView {
            return AccountsRecycleView(context)
        }
    }
}
