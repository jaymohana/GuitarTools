package com.tools.guitartools.ui.audio

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioRecord
import androidx.core.app.ActivityCompat
import android.media.MediaRecorder
import com.tools.guitartools.MainActivity
import kotlin.concurrent.thread

class AudioInput (private val context: Context, private val activity: MainActivity) {

    private val sampleRate = 44100
    private val bufferSize = AudioRecord.getMinBufferSize(sampleRate,
        AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT)

    private var audioRecord: AudioRecord? = null

    init {
        if ((ActivityCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO)
                    == PackageManager.PERMISSION_GRANTED)) {
            initialiseAudioRecord()
        } else {
            ActivityCompat.requestPermissions(
                activity, arrayOf( Manifest.permission.RECORD_AUDIO), 200)
        }
    }

    @SuppressLint("MissingPermission")
    private fun initialiseAudioRecord() {
        audioRecord = AudioRecord(
            MediaRecorder.AudioSource.MIC,
            sampleRate,
            AudioFormat.CHANNEL_IN_MONO,
            AudioFormat.ENCODING_PCM_16BIT,
            bufferSize)
    }


    fun startRecording(onAudioDataReceived: (ShortArray) -> Unit) {
        audioRecord?.startRecording()

        thread {
            val buffer = ShortArray(bufferSize)
            while (audioRecord?.recordingState == AudioRecord.RECORDSTATE_RECORDING) {
                val readSize = audioRecord!!.read(buffer, 0, buffer.size)
                if (readSize > 0) {
                    onAudioDataReceived(buffer.copyOf(readSize))
                }
            }
        }
    }
}