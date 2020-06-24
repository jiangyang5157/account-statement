package com.gmail.jiangyang5157.account_statement.ui.statement

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
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

    private val transactionItems = mutableListOf<TransactionItem>()

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

        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            getString(R.string.label_page_transactions)

        transactionItems.clear()
        transactionItems.addAll(transactions.map { transactionEntity ->
            TransactionItem(transactionEntity)
        })

        rv_transactions.init()
        rv_transactions.updateItems(
            transactionItems.sortedBy {
                it.transaction.money.amount
            }
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_transactions, menu)

        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                rv_transactions?.updateItems(
                    newText?.let { keyword ->
                        transactionItems.filter {
                            it.transaction.description.contains(
                                keyword,
                                true
                            )
                        }.sortedBy {
                            it.transaction.money.amount
                        }
                    } ?: transactionItems.sortedBy {
                        it.transaction.money.amount
                    }
                )
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_visualize -> {
                startActivity(
                    Intent(requireContext(), ChartFlutterActivity::class.java).apply {
                        putExtras(Bundle().apply {
                            putString(ChartFlutterActivity.keyTransactions, Gson().toJson(transactions))
                        })
                    }
                )
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}
