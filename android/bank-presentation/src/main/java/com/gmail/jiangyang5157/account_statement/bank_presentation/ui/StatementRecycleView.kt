package com.gmail.jiangyang5157.account_statement.bank_presentation.ui

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import com.gmail.jiangyang5157.account_statement.bank_presentation.ui.binding.StatementItem
import com.gmail.jiangyang5157.account_statement.bank_presentation.ui.binding.StatementItemViewBinder
import com.gmail.jiangyang5157.adapter.multitype.MultiTypeAdapter
import com.gmail.jiangyang5157.adapter.multitype.SimpleRecycleViewAdapter
import com.gmail.jiangyang5157.adapter.ui.SimpleRecycleView

class StatementRecycleView : SimpleRecycleView {

    override val recycleViewAdapter: SimpleRecycleViewAdapter = MultiTypeAdapter()
        .register(StatementItem::class.java, StatementItemViewBinder())

    constructor(context: Context) :
        super(context)

    constructor(context: Context, attrs: AttributeSet?) :
        super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int) :
        super(context, attrs, defStyleAttr)

    /**
     * @return
     * 1: new selected
     * -1: new unselected
     * 0: nothing changed
     */
    fun toggleStatementItemSelection(position: Int): Int {
        return if (recycleViewAdapter.items.size > position) {
            val item = recycleViewAdapter.items[position] as StatementItem
            item.isSelected = !item.isSelected
            recycleViewAdapter.notifyItemChanged(position)
            if (item.isSelected) {
                1
            } else {
                -1
            }
        } else {
            0
        }
    }

    fun clearSelectedStatementItems() {
        recycleViewAdapter.items.forEach {
            (it as StatementItem).isSelected = false
        }
        recycleViewAdapter.notifyDataSetChanged()
    }

    @Suppress("UNCHECKED_CAST")
    fun getSelectedStatementItems(): List<StatementItem> {
        return recycleViewAdapter.items.filter {
            (it as StatementItem).isSelected
        } as List<StatementItem>
    }
}
