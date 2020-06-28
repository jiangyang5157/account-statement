package com.gmail.jiangyang5157.account_statement.bank_data.remote.dto

import com.gmail.jiangyang5157.account_statement.bank_domain.entity.AccountEntity
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * AccountDto is service model
 *
 * [AccountDto.Converter] account model between [AccountDto] and [AccountEntity]
 */
data class AccountDto(

    @field:SerializedName("name")
    val name: String
) {

    class Converter :
        com.gmail.jiangyang5157.kotlin_kit.data.model.Converter<AccountDto, AccountEntity> {

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
}
