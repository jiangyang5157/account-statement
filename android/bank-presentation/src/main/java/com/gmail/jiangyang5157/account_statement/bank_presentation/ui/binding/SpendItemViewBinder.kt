package com.gmail.jiangyang5157.account_statement.bank_presentation.ui.binding

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gmail.jiangyang5157.account_statement.bank_presentation.R
import com.gmail.jiangyang5157.adapter.multitype.ItemViewBinder

class SpendItemViewBinder :
    ItemViewBinder<SpendItem, SpendItemViewBinder.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDescription: TextView = itemView.findViewById(R.id.tv_description)
        val tvMoney: TextView = itemView.findViewById(R.id.tv_money)
    }

    override fun onBindViewHolder(holder: ViewHolder, item: SpendItem) {
        holder.tvDescription.text = item.description
        holder.tvMoney.text = item.spend.amount.toPlainString()
        holder.itemView.setOnClickListener(item.onClickListener)
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_spend, parent, false))
    }
}
