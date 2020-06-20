package com.gmail.jiangyang5157.account_statement.ui.statement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gmail.jiangyang5157.account_statement.R
import com.gmail.jiangyang5157.account_statement.bank_domain.entity.AccountEntity
import com.gmail.jiangyang5157.account_statement.bank_presentation.vm.StatementViewModel
import com.gmail.jiangyang5157.account_statement.router.RouterFragmentGuest
import com.gmail.jiangyang5157.account_statement.router.UriRoute
import com.gmail.jiangyang5157.android.router.core.pop
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_statement.*
import java.util.*

@AndroidEntryPoint
class AddStatementFragment : Fragment(), RouterFragmentGuest<UriRoute> {

    private val statementViewModel: StatementViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_add_statement, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_add.setOnClickListener {
            statementViewModel.addAccounts(
                // TODO
                listOf(
                    AccountEntity("random name ${Random().nextInt()}", Date())
                )
            )
            router.pop()
        }
    }
}
