package xyz.wsxyeah.continuousroundrect

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View

class PlaygroundView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val path = Path()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        val out = TypedValue()
        context.theme.resolveAttribute(R.attr.colorAccent, out, true)
        color = out.data
    }

    var radius: Float = 0f
        set(value) {
            if (field == value) {
                return
            }
            field = value
            path.reset()
            path.continuousRoundRect(0f, 0f, width.toFloat(), height.toFloat(), value)
            invalidate()
        }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        path.reset()
        path.continuousRoundRect(0f, 0f, w.toFloat(), h.toFloat(), radius)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

}