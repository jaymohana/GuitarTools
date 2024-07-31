package com.tools.guitartools.ui

import android.graphics.Color
import android.graphics.Paint

val blackPaint = Paint().apply {
    color = Color.BLACK
    strokeWidth = 3f
    isAntiAlias = true
    textAlign = Paint.Align.CENTER
    textSize = 30f
}

 val greenPaint = Paint().apply {
    color = Color.GREEN
    strokeWidth = 3f
    isAntiAlias = true
    textAlign = Paint.Align.CENTER
    textSize = 30f
}

// Constants for layout
public const val numStrings = 6
public const val circleRadius = 50
public const val circleSpacing = 100
public val noteNames = arrayOf("E", "A", "D", "G", "B", "E")
