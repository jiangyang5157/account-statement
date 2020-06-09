package com.gmail.jiangyang5157.account_statement.analysis.domain.model

import androidx.annotation.IntRange

data class Field<T>(@IntRange(from = 0) val index: Int, var value: T? = null)
