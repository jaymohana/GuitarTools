package com.tools.guitartools.ui.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class GuitarTunerView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 3f
        isAntiAlias = true
        textAlign = Paint.Align.CENTER
        textSize = 30f
    }

    // Constants for layout
    private val numStrings = 6
    private val circleRadius = 50
    private val circleSpacing = 100
    private val noteNames = arrayOf("E", "A", "D", "G", "B", "E")

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.let {
            tunerLine(canvas)

            // Calculate starting position for drawing notes
            val startX = (width/7) + 25 / 2f
            var increment = 0;
            // Draw note circles and text
            for (i in 0 until numStrings) {

                val cx = startX + i * circleSpacing + increment
                val cy = height / 2f
                canvas.drawCircle(cx, cy, circleRadius.toFloat(), paint)
                canvas.drawText(noteNames[i], cx, cy + circleRadius + 40, paint)
                increment += width/7 - circleSpacing

            }
        }
    }

    private fun tunerLine(canvas: Canvas) {

        // Vertical lines
        val startX = 50/1f
        canvas.drawLine(startX, 250/1f, startX, 500/1f, paint)

        val middleX = (canvas.width/2)/1f
        canvas.drawLine(middleX, 275/1f, middleX, 475/1f, paint)

        val endX = (width - 50)/1f
        canvas.drawLine(endX, 250/1f, endX, 500/1f, paint)

        // Horizontal line
        canvas.drawLine(startX, 375/1f, endX, 375/1f, paint)
    }

}