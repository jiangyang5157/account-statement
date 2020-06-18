package com.gmail.jiangyang5157.account_statement.feature_account.parser

interface Parser<F, T> {

    fun parse(from: F?): T?
}
