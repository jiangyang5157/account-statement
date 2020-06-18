package com.gmail.jiangyang5157.account_statement.feature_account.ui.binding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gmail.jiangyang5157.account_statement.feature_account.R
import com.gmail.jiangyang5157.adapter.multitype.ItemViewBinder

class AccountItemViewBinder : ItemViewBinder<AccountItem, AccountItemViewBinder.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvLastModifiedDate: TextView = itemView.findViewById(R.id.tv_last_modified_date)
        val tvTransactionCount: TextView = itemView.findViewById(R.id.tv_transaction_count)
    }

    override fun onBindViewHolder(holder: ViewHolder, item: AccountItem) {
        holder.tvName.text = item.name
        holder.tvLastModifiedDate.text = item.lastModifiedDate.toString()
        holder.tvTransactionCount.text = item.transactionCount.toString()
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_account, parent, false))
    }
}
