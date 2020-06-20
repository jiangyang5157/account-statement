package com.gmail.jiangyang5157.account_statement.bank_presentation.ui.binding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gmail.jiangyang5157.account_statement.bank_presentation.R
import com.gmail.jiangyang5157.adapter.multitype.ItemViewBinder

class StatementItemViewBinder :
    ItemViewBinder<StatementItem, StatementItemViewBinder.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_account_name)
        val tvLastModifiedDate: TextView = itemView.findViewById(R.id.tv_account_last_modified_date)
        val tvTransactionCount: TextView = itemView.findViewById(R.id.tv_transaction_count)
    }

    override fun onBindViewHolder(holder: ViewHolder, item: StatementItem) {
        holder.tvName.text = item.statement.account.name
        holder.tvLastModifiedDate.text = item.statement.account.lastModifiedDate.toString()
        holder.tvTransactionCount.text = item.statement.transactions.size.toString()
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_statement, parent, false))
    }
}
