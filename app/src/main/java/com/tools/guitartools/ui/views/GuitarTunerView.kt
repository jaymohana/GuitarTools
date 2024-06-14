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

//        canvas.let {
//            canvas.drawLine(5/2f, 5/2f,6/2f, 10/2f, paint)
//        }

        canvas.let {


            tunerLines2(canvas)

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

    fun tunerLines(canvas: Canvas) {
        var cx = 50/2f
        canvas.drawLine(cx, 500/2f,cx, 1000/2f, paint)
        for (i in 0 until 7) {
            cx += 100/2f

            canvas.drawLine(cx, 600/2f,cx, 900/2f, paint)

        }

        cx = 850/2f
        canvas.drawLine(cx, 500/2f,cx, 1000/2f, paint)
        for (i in 0 until 7) {
            cx += 100/2f

            canvas.drawLine(cx, 600/2f,cx, 900/2f, paint)

        }

        cx = 1650/2f
        canvas.drawLine(cx, 500/2f,cx, 1000/2f, paint)
    }

    fun tunerLines2(canvas: Canvas) {

        var cx = width/17/2f

        var bigLineCyStart = 500/2f
        var bigLineCyEnd = 1000/2f

        var smallLineCyStart = 600/2f
        var smallLineCyEnd = 900/2f

        for (i in 0 until 17) {
            if ((i == 0) || (i == 8) || (i == 16)) {
                canvas.drawLine(cx, bigLineCyStart, cx, bigLineCyEnd, paint)
            }
            else {
                canvas.drawLine(cx, smallLineCyStart, cx, smallLineCyEnd, paint)
            }
            cx += width/17
        }

    }

}