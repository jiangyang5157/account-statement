package com.gmail.jiangyang5157.account_statement.ui.statement

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.account_statement.R
import com.gmail.jiangyang5157.account_statement.bank_domain.entity.TransactionEntity
import com.gmail.jiangyang5157.account_statement.bank_presentation.ui.binding.SpendItem
import com.gmail.jiangyang5157.account_statement.router.RouterFragmentGuest
import com.gmail.jiangyang5157.account_statement.router.UriRoute
import com.gmail.jiangyang5157.android.router.core.push
import com.gmail.jiangyang5157.android.router.core.route
import com.gmail.jiangyang5157.core.ext.fromJson
import com.gmail.jiangyang5157.kotlin_kit.data.model.finance.Money
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_statement.*

@AndroidEntryPoint
class StatementFragment : Fragment(), RouterFragmentGuest<UriRoute> {

    private val route: UriRoute by route()
    private val transactions by lazy {
        route.query("transactions")?.let {
            Gson().fromJson<List<TransactionEntity>>(it)
        } ?: emptyList()
    }

    private val spendItems = mutableListOf<SpendItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_statement, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.label_page_statement)

        val map = hashMapOf<String, Money>()
        transactions.forEach {
            val desc = it.description
            val money = it.money
            map[desc] = map[desc]?.let { previous ->
                previous + money
            } ?: let { money }
        }
        spendItems.addAll(map.map { entry ->
            SpendItem(
                entry.key,
                entry.value,
                View.OnClickListener {
                    router push UriRoute(
                        "app://account-statement/transactions" +
                            "?transactions=${Gson().toJson(transactions.filter { it.description == entry.key })}"
                    )
                }
            )
        })

        rv_spend.init()
        rv_spend.updateItems(spendItems.sortedBy {
            it.spend.amount
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_statement, menu)

        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                rv_spend.updateItems(
                    newText?.let { keyword ->
                        spendItems.filter { it.description.contains(keyword, true) }.sortedBy {
                            it.spend.amount
                        }
                    } ?: spendItems.sortedBy {
                        it.spend.amount
                    }
                )
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}
