package com.gmail.jiangyang5157.account_statement.ui.statement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_statements.*

@AndroidEntryPoint
class StatementsFragment : Fragment(), RouterFragmentGuest<UriRoute> {

    private val statementViewModel: StatementViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_statements, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_statements.init()
        statementViewModel.getStatements().observe(viewLifecycleOwner,
            Observer { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.run {
                            rv_statements.addItems(this.map {
                                StatementItem(
                                    it,
                                    onClickListener = View.OnClickListener {
                                        router push UriRoute("app://account-statement/statement")

                                    }
                                )
                            })
                        }
                    }
                    else -> {
                    }
                }
            }
        )

        fab.setOnClickListener {
            router push UriRoute("app://account-statement/add-statement")
        }
    }
}
