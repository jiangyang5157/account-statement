package com.gmail.jiangyang5157.account_statement

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.account_statement.router.RouterFragmentGuest
import com.gmail.jiangyang5157.account_statement.router.UriRoute
import com.gmail.jiangyang5157.android.router.core.push
import com.gmail.jiangyang5157.android.router.core.route
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_accounts.*

class AccountsFragment : Fragment(), RouterFragmentGuest<UriRoute> {

    private val route: UriRoute by route()

    override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_accounts, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_info.text = "AccountsFragment\n\n" +
            "# route=${route.data}\n\n"

        btn_1.setOnClickListener {
            router push UriRoute(
                "app://account-statement/accounts"
            )
        }

        fab.setOnClickListener {
            Snackbar.make(it, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}
