package com.gmail.jiangyang5157.account_statement.ui.accounts

import com.gmail.jiangyang5157.account_statement.account.domain.usecase.GetStatementsUseCase
import com.gmail.jiangyang5157.account_statement.account.domain.usecase.UpdateAccountsUseCase
import javax.inject.Inject

class AccountsViewModel @Inject constructor(
    val getStatementsUseCase: GetStatementsUseCase,
    updateAccountsUseCase: UpdateAccountsUseCase
) {
}
