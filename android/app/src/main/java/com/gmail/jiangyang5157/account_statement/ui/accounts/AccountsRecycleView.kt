package com.gmail.jiangyang5157.account_statement.ui.accounts

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import com.gmail.jiangyang5157.adapter.multitype.SimpleRecycleViewAdapter
import com.gmail.jiangyang5157.adapter.ui.SimpleRecycleView

class AccountsRecycleView : SimpleRecycleView {

    override val recycleViewAdapter: SimpleRecycleViewAdapter
        get() = TODO("Not yet implemented")

    constructor(context: Context) :
        super(context)

    constructor(context: Context, attrs: AttributeSet?) :
        super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int) :
        super(context, attrs, defStyleAttr)

}
