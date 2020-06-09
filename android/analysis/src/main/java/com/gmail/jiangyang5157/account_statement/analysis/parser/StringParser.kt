package com.gmail.jiangyang5157.account_statement.analysis.parser

import com.gmail.jiangyang5157.kotlin_kit.model.Mapper

object StringParser : Mapper<CharSequence, String> {

    override fun map(from: CharSequence): String {
        return from.trim().toString()
    }
}
