package com.gmail.jiangyang5157.account_statement.analysis.ui.widget

import android.graphics.Canvas
import android.graphics.Paint
import com.gmail.jiangyang5157.kotlin_kit.render.Renderable

interface Renderable : Renderable<Canvas> {

    var paint: Paint
}
