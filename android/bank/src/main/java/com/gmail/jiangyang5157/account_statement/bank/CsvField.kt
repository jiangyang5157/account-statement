package com.gmail.jiangyang5157.account_statement.bank

import androidx.annotation.IntRange

data class CsvField<T>(@IntRange(from = 0) val index: Int, var value: T? = null)
