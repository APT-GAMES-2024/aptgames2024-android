package com.onemonth.aptgame.view.custom

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatTextView
import com.onemonth.aptgame.R

class OutlineTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private var strokeColor: Int
    private var strokeWidthVal: Float

    init {
        context.obtainStyledAttributes(attrs, R.styleable.OutlineTextView).apply {
            try {
                strokeWidthVal = getFloat(R.styleable.OutlineTextView_textStrokeWidth, 3f)
                strokeColor = getColor(R.styleable.OutlineTextView_textStrokeColor, Color.WHITE)
            } finally {
                recycle()
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        val states: ColorStateList = textColors

        // 테두리 그리기
        paint.apply {
            style = Paint.Style.STROKE
            strokeWidth = strokeWidthVal
        }
        setTextColor(strokeColor)
        super.onDraw(canvas)

        // 내부 텍스트 그리기
        paint.style = Paint.Style.FILL
        setTextColor(states)
        super.onDraw(canvas)
    }

    fun setStrokeWidth(width: Float) {
        strokeWidthVal = width
        invalidate()
    }

    fun setStrokeColor(@ColorInt color: Int) {
        strokeColor = color
        invalidate()
    }

    fun setStrokeWidthDp(widthDp: Float) {
        strokeWidthVal = context.resources.displayMetrics.density * widthDp
        invalidate()
    }
}
