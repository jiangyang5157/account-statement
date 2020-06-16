package com.gmail.jiangyang5157.account_statement.ui.accounts

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.account_statement.R
import com.gmail.jiangyang5157.account_statement.router.RouterFragmentGuest
import com.gmail.jiangyang5157.account_statement.router.UriRoute
import kotlinx.android.synthetic.main.fragment_accounts.*

class AccountsFragment : Fragment(), RouterFragmentGuest<UriRoute> {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_accounts, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab.setOnClickListener {
            // TODO add account into recycleview
        }
    }
}
