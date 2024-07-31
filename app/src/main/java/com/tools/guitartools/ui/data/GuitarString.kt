package com.tools.guitartools.ui.data

data class GuitarString(val name: String, val perfectPitch: Double, val frequencyRange: Pair<Double, Double>)

val guitarStrings = listOf(
    GuitarString("D2", 73.42, 70.01 to 75.0),
    GuitarString("D#2/Eb2", 77.78, 75.01 to 79.0),
    GuitarString("E2", 82.41, 79.0 to 85.0), // 6
    GuitarString("F2", 87.3, 85.01 to 90.0),
    GuitarString("F#2/Gb2", 98.0, 90.01 to 100.0),
    GuitarString("G#2/Ab2", 103.8, 100.01 to 106.5),
    GuitarString("A2", 110.0, 106.51 to 113.5), // 5
    GuitarString("A#2/Bb2", 116.5, 113.0 to 120.0),
    GuitarString("B2", 123.47, 120.01 to 127.0),
    GuitarString("C3", 130.8, 127.01 to 135.0),
    GuitarString("C#3/Db3", 138.6, 135.01 to 142.0),
    GuitarString("D3", 146.83, 142.01 to 150.0), // 4
    GuitarString("D#3/Eb3", 155.56, 150.01 to 160.0),
    GuitarString("E3", 164.8, 160.01 to 169.0),
    GuitarString("F3", 176.6, 169.01 to 180.0),
    GuitarString("F#3/Gb3", 185.0, 180.01 to 190.0),
    GuitarString("G3", 196.0, 190.01 to 200.0), // 3
    GuitarString("G#3/Ab3", 207.6, 200.01 to 215.0),
    GuitarString("A3", 220.0, 215.01 to 225.0),
    GuitarString("A#3/Bb3", 233.0, 225.01 to 240.0),
    GuitarString("B3", 246.94, 240.01 to 255.0), // 2
    GuitarString("C4", 261.6, 255.01 to 270.0),
    GuitarString("C#4/Db4", 277.18, 270.01 to 285.0),
    GuitarString("D4", 293.66, 285.01 to 300.0),
    GuitarString("D#4/Eb4", 311.13, 300.01 to 320.0),
    GuitarString("E4", 329.63, 320.01 to 340.0), // 1
    GuitarString("F4", 349.23, 340.01 to 360.0),
)

fun getClosestString(frequency: Double): GuitarString? {
    return guitarStrings.find { frequency in it.frequencyRange.first..it.frequencyRange.second }
}