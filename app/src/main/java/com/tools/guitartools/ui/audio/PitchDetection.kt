package com.tools.guitartools.ui.audio

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioRecord
import androidx.core.app.ActivityCompat
import be.tarsos.dsp.AudioDispatcher
import be.tarsos.dsp.io.android.AudioDispatcherFactory
import be.tarsos.dsp.pitch.PitchDetectionHandler
import be.tarsos.dsp.pitch.PitchProcessor

class PitchDetection (private val context: Context) {
    private var dispatcher: AudioDispatcher? = null

    private fun checkPermissions(): Boolean {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
    }

    fun requestAudioRecordPermissions(activity: Activity) {
        ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.RECORD_AUDIO), 200)
    }

    fun startPitchDetection(onPitchDetected: (Float) -> Unit) {
        if (!checkPermissions()) {
            throw SecurityException("Permission to record audio not granted")
        }

        val sampleRate = 44100
        val bufferSize = AudioRecord.getMinBufferSize(sampleRate,
            AudioFormat.CHANNEL_IN_MONO,
            AudioFormat.ENCODING_PCM_16BIT)

        dispatcher = AudioDispatcherFactory.fromDefaultMicrophone(sampleRate, bufferSize, 0)

        val pitchDetectionHandler = PitchDetectionHandler { result, _ ->
            val pitchInHz = result.pitch
            onPitchDetected(pitchInHz)
        }

        val pitchProcessor = PitchProcessor(
            PitchProcessor.PitchEstimationAlgorithm.FFT_YIN,
            sampleRate.toFloat(),
            bufferSize,
            pitchDetectionHandler
        )

        dispatcher?.addAudioProcessor(pitchProcessor)

        Thread(dispatcher, "Audio Dispatcher").start()
    }
}