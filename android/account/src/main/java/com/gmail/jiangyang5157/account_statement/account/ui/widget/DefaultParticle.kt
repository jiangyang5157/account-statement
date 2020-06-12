package com.gmail.jiangyang5157.account_statement.account.ui.widget

import android.graphics.Canvas
import android.graphics.Paint
import com.gmail.jiangyang5157.kotlin_kit.math.Vector2i

open class DefaultParticle(override var position: Vector2i = Vector2i()) : Particle {

    override var paint: Paint = Paint().apply { isAntiAlias = true }

    override fun onRender(t: Canvas) {
        // TODO
    }
}
