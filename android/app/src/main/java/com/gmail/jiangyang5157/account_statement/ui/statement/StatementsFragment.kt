package com.gmail.jiangyang5157.account_statement.ui.statement

import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.gmail.jiangyang5157.account_statement.R
import com.gmail.jiangyang5157.account_statement.bank_presentation.ui.binding.StatementItem
import com.gmail.jiangyang5157.account_statement.bank_presentation.vm.StatementViewModel
import com.gmail.jiangyang5157.account_statement.router.RouterFragmentGuest
import com.gmail.jiangyang5157.account_statement.router.UriRoute
import com.gmail.jiangyang5157.android.router.core.push
import com.gmail.jiangyang5157.core.data.Status
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_statements.*

@AndroidEntryPoint
class StatementsFragment : Fragment(), RouterFragmentGuest<UriRoute> {

    private val statementViewModel: StatementViewModel by viewModels()

    private val statementItems = mutableListOf<StatementItem>()

    private lateinit var mergeMenuItem: MenuItem
    private lateinit var deleteMenuItem: MenuItem
    private lateinit var selectionMenuItem: MenuItem
    private lateinit var addMenuItem: MenuItem

    interface Mode {
        fun setupView()
        fun setupMenu()
        fun onStatementItemClicked(position: Int)
    }

    private lateinit var defaultMode: Mode
    private lateinit var selectionMode: Mode
    private lateinit var currentMode: Mode

    init {
        defaultMode = object : Mode {

            override fun setupView() {
                (requireActivity() as AppCompatActivity).supportActionBar?.title =
                    getString(R.string.label_page_statements)
                (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(
                    false
                )
                (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(
                    false
                )
                (requireActivity() as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(null)
            }

            override fun setupMenu() {
                mergeMenuItem.isVisible = false
                deleteMenuItem.isVisible = false
                selectionMenuItem.isVisible = true
                addMenuItem.isVisible = true
            }

            override fun onStatementItemClicked(position: Int) {
                router push UriRoute(
                    "app://account-statement/statement" +
                        "?transactions=${Gson().toJson(statementItems[position].statement.transactions)}"
                )
            }
        }

        selectionMode = object : Mode {

            var selectionCount: Int = 0

            override fun setupView() {
                selectionCount = 0
                (requireActivity() as AppCompatActivity).supportActionBar?.title = "$selectionCount"
                (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(
                    true
                )
                (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(
                    true
                )
                (requireActivity() as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(
                    android.R.drawable.ic_menu_close_clear_cancel
                )
            }

            override fun setupMenu() {
                selectionMenuItem.isVisible = false
                mergeMenuItem.isVisible = true
                deleteMenuItem.isVisible = true
                addMenuItem.isVisible = false
            }

            override fun onStatementItemClicked(position: Int) {
                selectionCount += rv_statements.toggleStatementItemSelection(position)
                (requireActivity() as AppCompatActivity).supportActionBar?.title = "$selectionCount"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_statements, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentMode = defaultMode
        currentMode.setupView()

        rv_statements.init()
        statementViewModel.getStatements().observe(viewLifecycleOwner,
            Observer { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.run {
                            statementItems.clear()
                            statementItems.addAll(this.sortedByDescending {
                                it.account.lastModifiedDate
                            }.mapIndexed { index, statement ->
                                StatementItem(
                                    statement,
                                    onClickListener = View.OnClickListener {
                                        currentMode.onStatementItemClicked(index)
                                    }
                                )
                            })
                            rv_statements.updateItems(statementItems)
                        }
                    }
                    else -> {
                    }
                }
            }
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_statements, menu)
        addMenuItem = menu.findItem(R.id.action_add)
        selectionMenuItem = menu.findItem(R.id.action_selection)
        deleteMenuItem = menu.findItem(R.id.action_delete)
        mergeMenuItem = menu.findItem(R.id.action_merge)
        currentMode.setupMenu()
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_add -> {
                router push UriRoute(
                    "app://account-statement/add-statement" +
                        "?account-names=${Gson().toJson(statementItems.map {
                            it.statement.account.name
                        })}"
                )
                return true
            }
            R.id.action_selection -> {
                currentMode = selectionMode
                currentMode.setupView()
                currentMode.setupMenu()
                return true
            }
            R.id.action_delete -> {
                val selection = rv_statements.getSelectedStatementItems()
                rv_statements.clearSelectedStatementItems()
                statementViewModel.deleteAccounts(selection.map {
                    it.statement.account
                })
                currentMode = defaultMode
                currentMode.setupView()
                currentMode.setupMenu()
                return true
            }
            R.id.action_merge -> {
                val selection = rv_statements.getSelectedStatementItems()
                if (selection.size <= 1) {
                    Snackbar.make(
                        this.requireView(),
                        "Merge at least 2 statements",
                        Snackbar.LENGTH_SHORT
                    ).show()
                } else {
                    val etAccountName = EditText(requireContext())
                    etAccountName.hint = getString(R.string.hint_enter_account_name)
                    etAccountName.setSingleLine()
                    AlertDialog.Builder(requireContext())
                        .setView(etAccountName)
                        .setPositiveButton(
                            getString(R.string.label_btn_confirm)
                        ) { _, _ ->
                            val newAccountName = etAccountName.text.toString()
                            when {
                                newAccountName.trim().isEmpty() -> {
                                    Snackbar.make(
                                        this.requireView(),
                                        "Invalid account name: $newAccountName",
                                        Snackbar.LENGTH_SHORT
                                    ).show()
                                }
                                statementItems.map { it.statement.account.name }
                                    .contains(newAccountName) -> {
                                    Snackbar.make(
                                        this.requireView(),
                                        "Duplicated account name: $newAccountName",
                                        Snackbar.LENGTH_SHORT
                                    ).show()
                                }
                                else -> {
                                    rv_statements.clearSelectedStatementItems()
                                    statementViewModel.mergeStatements(
                                        newAccountName,
                                        selection.map {
                                            it.statement
                                        })
                                    currentMode = defaultMode
                                    currentMode.setupView()
                                    currentMode.setupMenu()
                                }
                            }
                        }
                        .setNegativeButton(
                            getString(R.string.label_btn_cancel)
                        ) { dialog, _ -> dialog.cancel() }

                        .show()
                }
                return true
            }
            android.R.id.home -> {
                rv_statements.clearSelectedStatementItems()
                currentMode = defaultMode
                currentMode.setupView()
                currentMode.setupMenu()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
}
