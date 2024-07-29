package com.tools.guitartools.ui.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.tools.guitartools.ui.data.GuitarString

class GuitarTunerView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private lateinit var canvas: Canvas
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

    private val paint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 3f
        isAntiAlias = true
        textAlign = Paint.Align.CENTER
        textSize = 30f
    }

    private val paintGreen = Paint().apply {
        color = Color.GREEN
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
        this.canvas = canvas
        super.onDraw(canvas)

        canvas.let {
            tunerLine(canvas)

            // Calculate starting position for drawing notes
            // make its own method
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

            pitchLinePosition?.let {
                canvas.drawLine(it, 235/1f, it, 535/1f, paintGreen)
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

    fun updatePitchLine(frequency: Double?, string: GuitarString?) {

        if (string != null) {
            val startRange = string?.frequencyRange?.first
            val endRange = string?.frequencyRange?.second
            val perfectPitch = string?.perfectPitch

            val ratio = (frequency?.minus(perfectPitch!!))?.div((endRange?.minus(startRange!!)!!))


            val test = canvasCenterX + (ratio?.times((canvasWidth - 100))!!)
            pitchLinePosition = test.toFloat()
        } else {

        }


        invalidate()
    }

}