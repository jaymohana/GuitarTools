package com.tools.guitartools.ui.views

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.tools.guitartools.ui.blackPaint
import com.tools.guitartools.ui.circleRadius
import com.tools.guitartools.ui.circleSpacing
import com.tools.guitartools.ui.data.GuitarString
import com.tools.guitartools.ui.greenPaint
import com.tools.guitartools.ui.noteNames
import com.tools.guitartools.ui.numStrings

class GuitarTunerView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private var pitchLinePosition: Float? = null
    private var canvasWidth = 0
    private var canvasHeight = 0
    private var canvasCenterX = 0f
    private var canvasCenterY = 0f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        // Update the canvas size and center position
        canvasWidth = w
        canvasHeight = h
        canvasCenterX = w / 2f
        canvasCenterY = h / 2f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.let {
            drawTunerPitchLine(canvas)

            pitchLinePosition?.let {
                canvas.drawLine(it, 235/1f, it, 535/1f, greenPaint)
            }
        }
    }

    private fun drawTunerPitchLine(canvas: Canvas) {
        // Vertical lines
        val startX = 50/1f
        canvas.drawLine(startX, 250/1f, startX, 500/1f, blackPaint)

        val middleX = (canvas.width/2)/1f
        canvas.drawLine(middleX, 275/1f, middleX, 475/1f, blackPaint)

        val endX = (width - 50)/1f
        canvas.drawLine(endX, 250/1f, endX, 500/1f, blackPaint)

        // Horizontal line
        canvas.drawLine(startX, 375/1f, endX, 375/1f, blackPaint)
    }

    // For later usage
    private fun drawStandardTuningNotes(canvas: Canvas) {
        val startX = (width/7) + 25 / 2f
        var increment = 0;
        // Draw note circles and text
        for (i in 0 until numStrings) {
            val cx = startX + i * circleSpacing + increment
            val cy = (height - 400)/1f
            canvas.drawCircle(cx, cy, circleRadius.toFloat(), blackPaint)
            canvas.drawText(noteNames[i], cx, cy + circleRadius + 40, blackPaint)
            increment += width/7 - circleSpacing
        }
    }

    fun updatePitchLine(frequency: Double, string: GuitarString?) {
        if (string != null) {
            val startRange = string.frequencyRange.first
            val endRange = string.frequencyRange.second
            val perfectPitch = string.perfectPitch
            val ratio = (frequency - perfectPitch)/(endRange - startRange)
            val pitchLine = canvasCenterX + (ratio * ((canvasWidth - 100)))

            pitchLinePosition = pitchLine.toFloat()
        }

        invalidate()
    }
}