package com.gmail.jiangyang5157.account_statement.ui.statement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.account_statement.R
import com.gmail.jiangyang5157.account_statement.bank_domain.entity.TransactionEntity
import com.gmail.jiangyang5157.account_statement.bank_presentation.ui.binding.TransactionItem
import com.gmail.jiangyang5157.account_statement.router.RouterFragmentGuest
import com.gmail.jiangyang5157.account_statement.router.UriRoute
import com.gmail.jiangyang5157.android.router.core.route
import com.gmail.jiangyang5157.core.ext.fromJson
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_transactions.*

@AndroidEntryPoint
class TransactionsFragment : Fragment(), RouterFragmentGuest<UriRoute> {

    private val route: UriRoute by route()
    private val transactions by lazy {
        route.query("transactions")?.let {
            Gson().fromJson<List<TransactionEntity>>(it)
        } ?: emptyList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_transactions, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().title = getString(R.string.label_page_transactions)

        rv_transactions.init()
        rv_transactions.addItems(
            transactions.map {
                TransactionItem(it)
            }
        )
    }
}
