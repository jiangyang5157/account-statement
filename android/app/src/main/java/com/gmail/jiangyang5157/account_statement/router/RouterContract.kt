package com.gmail.jiangyang5157.account_statement.router

import com.gmail.jiangyang5157.android.router.core.Route
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import com.gmail.jiangyang5157.android.router.fragment.setup.RouterFragment
import com.gmail.jiangyang5157.android.router.fragment.setup.RouterFragmentActivity
import com.gmail.jiangyang5157.android.router.fragment.setup.expectThisToBeAFragment

/**
 * Implemented by FragmentActivity
 */
interface RouterFragmentActivityHost<T : Route> : RouterFragmentActivity {

    val router: FragmentRouter<T>
}

/**
 * Implemented by Fragment, use [router] value from it's [RouterFragmentActivityHost]
 */
interface RouterFragmentGuest<T : Route> : RouterFragment {

    @Suppress("UNCHECKED_CAST")
    override val router: FragmentRouter<T>
        get() = (expectThisToBeAFragment().activity as RouterFragmentActivityHost<T>).router
}
