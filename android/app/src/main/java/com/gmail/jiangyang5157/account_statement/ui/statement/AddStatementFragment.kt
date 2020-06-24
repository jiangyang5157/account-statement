package com.gmail.jiangyang5157.account_statement.ui.statement

import android.database.sqlite.SQLiteConstraintException
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gmail.jiangyang5157.account_statement.R
import com.gmail.jiangyang5157.account_statement.bank_domain.entity.TransactionEntity
import com.gmail.jiangyang5157.account_statement.bank_presentation.parser.CsvTransactionParser
import com.gmail.jiangyang5157.account_statement.bank_presentation.parser.anz.AnzCreditParser
import com.gmail.jiangyang5157.account_statement.bank_presentation.parser.anz.AnzSavingsParser
import com.gmail.jiangyang5157.account_statement.bank_presentation.parser.asb.AsbSavingsParser
import com.gmail.jiangyang5157.account_statement.bank_presentation.vm.StatementViewModel
import com.gmail.jiangyang5157.account_statement.router.RouterFragmentGuest
import com.gmail.jiangyang5157.account_statement.router.UriRoute
import com.gmail.jiangyang5157.android.router.core.pop
import com.gmail.jiangyang5157.android.router.core.route
import com.gmail.jiangyang5157.core.ext.fromJson
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_statement.*
import java.io.FileNotFoundException

@AndroidEntryPoint
class AddStatementFragment : Fragment(), RouterFragmentGuest<UriRoute> {

    private val route: UriRoute by route()
    private val accountNames by lazy {
        route.query("account-names")?.let {
            Gson().fromJson<List<String>>(it)
        } ?: emptyList()
    }

    private val statementViewModel: StatementViewModel by viewModels()

    private val bankArray = arrayOf(
        "ANZ Saving",
        "ANZ Credit",
        "ASB Saving"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_add_statement, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            getString(R.string.label_page_add_statement)

        btn_file_browser.setOnClickListener {
            val getContent =
                registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                    tv_file_uri.text = uri.toString()
                }
            getContent.launch("text/*")
        }

        spinner_bank.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            bankArray
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_add_statement, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_create -> {
                val accountName = tiet_account_name.text.toString().trim().let {
                    when {
                        it.isEmpty() -> {
                            Snackbar.make(
                                requireView(),
                                "Invalid account name: $it",
                                Snackbar.LENGTH_SHORT
                            ).show()
                            return super.onOptionsItemSelected(item)
                        }
                        accountNames.contains(it) -> {
                            Snackbar.make(
                                requireView(),
                                "Duplicated account name: $it",
                                Snackbar.LENGTH_SHORT
                            ).show()
                            return super.onOptionsItemSelected(item)
                        }
                        else -> {
                            it
                        }
                    }
                }
                val uri = Uri.parse(tv_file_uri.text.toString())
                val inputStream = try {
                    requireContext().contentResolver.openInputStream(uri)
                } catch (e: FileNotFoundException) {
                    Snackbar.make(requireView(), "Invalid uri: $uri", Snackbar.LENGTH_SHORT).show()
                    return super.onOptionsItemSelected(item)
                }

                val transactions = mutableListOf<TransactionEntity>()
                when (bankArray[spinner_bank.selectedItemPosition]) {
                    "ANZ Saving" -> AnzSavingsParser().parse(inputStream)
                    "ANZ Credit" -> AnzCreditParser().parse(inputStream)
                    "ASB Saving" -> AsbSavingsParser().parse(inputStream)
                    else -> throw RuntimeException()
                }?.map { transaction ->
                    CsvTransactionParser(accountName).parse(
                        transaction
                    )?.also { transition ->
                        transactions.add(transition)
                    }
                }

                try {
                    statementViewModel.addStatement(accountName, transactions)
                    router.pop()
                } catch (e: SQLiteConstraintException) {
                    Snackbar.make(
                        requireView(),
                        "Failed: ${e.message}",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
}
