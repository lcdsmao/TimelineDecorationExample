package com.paranoid.mao.timelinedecorationexample

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class TimelineDecoration(
        private val items: List<Item>
) : RecyclerView.ItemDecoration() {

    private val paint = Paint()
    private val leftInterval = 500
    private val topInterval = 100
    private val circleRadius = 20f
    private val lineHalfWidth = 5f
    private val textMarginStart = 50f
    private val textIntervalTop = 50f
    private val textSize = 48f

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(leftInterval, topInterval, 0, 0)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val childCount = parent.childCount
        if (childCount == 0) return

        val itemCount = parent.adapter?.itemCount ?: return
        val context = parent.context
        (0 until childCount).forEach { i ->

            val child = parent.getChildAt(i)
            val childAdapterPosition = parent.getChildAdapterPosition(child)
            val layoutParams = child.layoutParams as RecyclerView.LayoutParams
            val centerX = child.left - leftInterval / 3
            val centerY = child.top - topInterval + (topInterval + child.height) / 2

            val left = centerX - lineHalfWidth
            val top = if (childAdapterPosition == 0) {
                centerY
            } else {
                child.top - topInterval - layoutParams.topMargin
            }.toFloat()

            val right = centerX + lineHalfWidth
            val bottom = if (childAdapterPosition == itemCount - 1) {
                centerY
            } else {
                child.bottom + layoutParams.bottomMargin
            }.toFloat()

            paint.color = ContextCompat.getColor(context, R.color.colorAccent)
            c.drawRect(left, top, right, bottom, paint)

            paint.color = ContextCompat.getColor(context, R.color.colorMarker)
            c.drawCircle(centerX.toFloat(), centerY.toFloat(), circleRadius, paint)

            val x = child.left - leftInterval - layoutParams.marginStart + textMarginStart
            val y = centerY - textIntervalTop
            val text = items[childAdapterPosition].time
            paint.color = ContextCompat.getColor(context, R.color.colorText)
            paint.textSize = textSize
            c.drawText(text, x, y, paint)
        }
    }
}
