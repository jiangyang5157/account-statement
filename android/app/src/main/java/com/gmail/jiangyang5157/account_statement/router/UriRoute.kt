package com.gmail.jiangyang5157.account_statement.router

import android.net.Uri
import com.gmail.jiangyang5157.android.router.core.DataRoute
import com.gmail.jiangyang5157.android.router.core.KeyRoute
import com.gmail.jiangyang5157.android.router.core.ParcelableRoute
import com.gmail.jiangyang5157.kotlin_kit.model.Key
import kotlinx.android.parcel.Parcelize

@Parcelize
class UriRoute(override val data: String) : KeyRoute, DataRoute<String>, ParcelableRoute {

    override val key: Key
        get() = Key(Uri.parse(data).let { "${it.scheme}://${it.authority}${it.path}" })

    fun query(name: String): String? = Uri.parse(data).getQueryParameter(name)
}
