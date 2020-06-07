package com.gmail.jiangyang5157.account_statement.router

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.transition.Fade
import com.gmail.jiangyang5157.android.router.core.Route
import com.gmail.jiangyang5157.android.router.fragment.transition.FragmentTransition

class DefaultFragmentTransition : FragmentTransition {

    override fun setup(
      transaction: FragmentTransaction,
      exitFragment: Fragment,
      exitRoute: Route,
      enterFragment: Fragment,
      enterRoute: Route
    ) {
        exitFragment.exitTransition = Fade(Fade.MODE_OUT)
        enterFragment.enterTransition = Fade(Fade.MODE_IN)
    }
}
