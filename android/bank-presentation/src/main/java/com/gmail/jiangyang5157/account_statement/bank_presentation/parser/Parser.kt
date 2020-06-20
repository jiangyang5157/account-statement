package com.gmail.jiangyang5157.account_statement.bank_presentation.parser

interface Parser<F, T> {

    fun parse(from: F?): T?
}
