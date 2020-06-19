package com.gmail.jiangyang5157.account_statement

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gmail.jiangyang5157.account_statement.router.RouterFragmentActivityHost
import com.gmail.jiangyang5157.account_statement.router.UriRoute
import com.gmail.jiangyang5157.android.router.core.MultiRouter
import com.gmail.jiangyang5157.android.router.core.clear
import com.gmail.jiangyang5157.android.router.core.push
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), RouterFragmentActivityHost<UriRoute> {

    @Inject
    lateinit var multiRouter: MultiRouter<String, UriRoute>

    override val router: FragmentRouter<UriRoute>
        get() = multiRouter[HomeActivity::class.java.name] as FragmentRouter<UriRoute>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        if (null == savedInstanceState) {
            router {
                clear() push UriRoute("app://account-statement/accounts")
            }
        }
        router.setup(savedInstanceState, R.id.content_router)
    }

    override fun onBackPressed() {
        router.popRetainRootImmediateOrFinish()
    }
}
