package com.gmail.jiangyang5157.account_statement.account_service.dto

import com.gmail.jiangyang5157.account_statement.account_cvo.AccountEntity
import com.gmail.jiangyang5157.kotlin_kit.data.model.Converter
import java.util.*

class AccountDtoConverter : Converter<AccountDto, AccountEntity> {

    override fun backward(b: AccountEntity?): AccountDto? =
        b?.let {
            AccountDto(it.name)
        }

    override fun forward(a: AccountDto?): AccountEntity? =
        a?.let {
            AccountEntity(
                it.name,
                Date()
            )
        }
}

