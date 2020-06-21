package com.gmail.jiangyang5157.account_statement.ui.statement

import android.database.sqlite.SQLiteConstraintException
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gmail.jiangyang5157.account_statement.R
import com.gmail.jiangyang5157.account_statement.bank_domain.entity.AccountEntity
import com.gmail.jiangyang5157.account_statement.bank_domain.entity.TransactionEntity
import com.gmail.jiangyang5157.account_statement.bank_presentation.parser.CsvTransactionParser
import com.gmail.jiangyang5157.account_statement.bank_presentation.parser.anz.AnzCreditParser
import com.gmail.jiangyang5157.account_statement.bank_presentation.parser.anz.AnzSavingsParser
import com.gmail.jiangyang5157.account_statement.bank_presentation.parser.asb.AsbSavingsParser
import com.gmail.jiangyang5157.account_statement.bank_presentation.vm.StatementViewModel
import com.gmail.jiangyang5157.account_statement.router.RouterFragmentGuest
import com.gmail.jiangyang5157.account_statement.router.UriRoute
import com.gmail.jiangyang5157.android.router.core.pop
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_statement.*
import java.io.FileNotFoundException
import java.util.*

@AndroidEntryPoint
class AddStatementFragment : Fragment(), RouterFragmentGuest<UriRoute> {

    private val statementViewModel: StatementViewModel by viewModels()

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

        requireActivity().title = getString(R.string.label_page_add_statement)

        btn_file_browser.setOnClickListener {
            val getContent =
                registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                    tv_file_uri.text = uri.toString()
                }
            getContent.launch("text/*")
        }

        val bankArray = arrayOf(
            "ANZ Saving",
            "ANZ Credit",
            "ASB Saving"
        )
        spinner_bank.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            bankArray
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        btn_add.setOnClickListener {
            val accountName = tiet_account_name.text.let { text ->
                if (text.isNullOrEmpty()) {
                    Snackbar.make(it, "Invalid account name: $text", Snackbar.LENGTH_SHORT).show()
                    null
                } else {
                    text.toString()
                }
            }
            val uri = Uri.parse(tv_file_uri.text.toString())
            val inputStream = try {
                requireContext().contentResolver.openInputStream(uri)
            } catch (e: FileNotFoundException) {
                Snackbar.make(it, "Invalid uri: $uri", Snackbar.LENGTH_SHORT).show()
                null
            }
            if (accountName != null && inputStream != null) {
                val account = AccountEntity(accountName, Date())
                val transactions = mutableListOf<TransactionEntity>()

                when (bankArray[spinner_bank.selectedItemPosition]) {
                    "ANZ Saving" -> {
                        AnzSavingsParser().parse(inputStream)?.map { transaction ->
                            CsvTransactionParser(account.name).parse(
                                transaction
                            )?.also { transition ->
                                transactions.add(transition)
                            }
                        }
                    }
                    "ANZ Credit" -> {
                        AnzCreditParser().parse(inputStream)?.map { transaction ->
                            CsvTransactionParser(account.name).parse(
                                transaction
                            )?.also { transition ->
                                transactions.add(transition)
                            }
                        }
                    }
                    "ASB Saving" -> {
                        AsbSavingsParser().parse(inputStream)?.map { transaction ->
                            CsvTransactionParser(account.name).parse(
                                transaction
                            )?.also { transition ->
                                transactions.add(transition)
                            }
                        }
                    }
                    else -> throw RuntimeException()
                }

                try {
                    statementViewModel.addAccounts(listOf(account))
                    statementViewModel.addTransactions(transactions)
                    router.pop()
                } catch (e: SQLiteConstraintException) {
                    Snackbar.make(it, "Failed: ${e.message}", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
}
