package com.gmail.jiangyang5157.account_statement.bank_presentation.ui.binding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gmail.jiangyang5157.account_statement.bank_data.remote.dto.TransactionDto
import com.gmail.jiangyang5157.account_statement.bank_presentation.R
import com.gmail.jiangyang5157.adapter.multitype.ItemViewBinder
import com.gmail.jiangyang5157.kotlin_kit.utils.RegexUtils

class TransactionItemViewBinder :
    ItemViewBinder<TransactionItem, TransactionItemViewBinder.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDescription: TextView = itemView.findViewById(R.id.tv_description)
        val tvDate: TextView = itemView.findViewById(R.id.tv_date)
        val tvMoney: TextView = itemView.findViewById(R.id.tv_money)
    }

    override fun onBindViewHolder(holder: ViewHolder, item: TransactionItem) {
        holder.tvDescription.text = item.transaction.description
        holder.tvMoney.text = item.transaction.money.amount.toPlainString()
        holder.tvDate.text = "${TransactionDto.DateStringConverter(RegexUtils.DATE_DMY)
            .forward(item.transaction.date)}"
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_transaction, parent, false))
    }
}
