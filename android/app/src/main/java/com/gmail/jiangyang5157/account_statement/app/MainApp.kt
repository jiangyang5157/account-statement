package com.gmail.jiangyang5157.account_statement.app

import com.gmail.jiangyang5157.core.App

class MainApp : App() {

    override fun inject() {
        DaggerMainAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }
}
