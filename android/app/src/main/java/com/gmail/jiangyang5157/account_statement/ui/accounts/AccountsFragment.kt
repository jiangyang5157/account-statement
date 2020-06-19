package com.gmail.jiangyang5157.account_statement.ui.accounts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gmail.jiangyang5157.account_statement.R
import com.gmail.jiangyang5157.account_statement.feature_account.vm.AccountsViewModel
import com.gmail.jiangyang5157.account_statement.router.RouterFragmentGuest
import com.gmail.jiangyang5157.account_statement.router.UriRoute
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_accounts.*

@AndroidEntryPoint
class AccountsFragment : Fragment(), RouterFragmentGuest<UriRoute> {

    private val accountsViewModel: AccountsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_accounts, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: get statements and display

        fab.setOnClickListener {
            // TODO: add statement
        }
    }
}
