package com.tools.guitartools.ui.fragment

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import be.tarsos.dsp.pitch.PitchDetector
import com.tools.guitartools.R
import com.tools.guitartools.ui.audio.PitchDetection
import com.tools.guitartools.ui.data.getClosestString
import com.tools.guitartools.ui.views.GuitarTunerView

class TunerFragment: Fragment() {

    private lateinit var pitchDetector: PitchDetection
    private lateinit var textView: TextView
    private lateinit var guitarTunerView: GuitarTunerView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tuner, container, false)
        guitarTunerView = view.findViewById<GuitarTunerView>(R.id.guitarTunerView)

        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textView = view.findViewById(R.id.textView)
        pitchDetector = PitchDetection(requireContext())

        requestPermissionAndStartDetection()
    }

    private fun requestPermissionAndStartDetection() {
        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                startPitchDetection()
            } else {
                // Handle the case when permission is not granted
                textView.text = "Permission denied"
            }
        }

        requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
    }

    private fun startPitchDetection() {
        pitchDetector.startPitchDetection { pitchInHz ->
            activity?.runOnUiThread {
                val guitarString = getClosestString(pitchInHz.toDouble())

                textView.text = guitarString?.name

                guitarTunerView.updatePitchLine(pitchInHz.toDouble(), guitarString)
            }
        }
    }

}