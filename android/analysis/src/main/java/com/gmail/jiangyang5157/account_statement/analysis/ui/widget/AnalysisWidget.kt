package com.gmail.jiangyang5157.account_statement.analysis.ui.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PixelFormat
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.MotionEvent
import com.gmail.jiangyang5157.android.widget.RenderView
import com.gmail.jiangyang5157.kotlin_kit.render.Renderable
import timber.log.Timber

class AnalysisWidget : RenderView, Renderable<Canvas> {

    // TODO
    private var particle: DefaultParticle? = null

    constructor(context: Context)
        : super(context)

    constructor(context: Context, attrs: AttributeSet)
        : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int)
        : super(context, attrs, defStyleAttr)

    init {
        setZOrderOnTop(true)
        holder.setFormat(PixelFormat.TRANSPARENT)
        setRenderable(this)
    }

    override fun onRender(t: Canvas) {
        t.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
        particle?.onRender(t)
    }

    private fun touch(x: Float, y: Float) {
        particle?.apply {
            // TODO
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                Timber.tag("####").d("MotionEvent.ACTION_DOWN")
                touch(event.x, event.y)
                refreshRender()
            }
            MotionEvent.ACTION_MOVE -> {
                touch(event.x, event.y)
            }
            MotionEvent.ACTION_UP -> {
                Timber.tag("####").d("MotionEvent.ACTION_UP")
                refreshRender()
                performClick()
            }
        }
        return super.onTouchEvent(event)
    }

    override fun performClick(): Boolean {
        super.performClick()
        return true
    }
}
