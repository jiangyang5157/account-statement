package com.gmail.jiangyang5157.account_statement.feature_account.ui.render

import android.graphics.Canvas
import android.graphics.Paint
import com.gmail.jiangyang5157.kotlin_kit.render.Renderable

interface Renderable : Renderable<Canvas> {

    var paint: Paint
}
