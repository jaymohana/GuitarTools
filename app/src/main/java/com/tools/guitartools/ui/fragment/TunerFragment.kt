package com.tools.guitartools.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tools.guitartools.R
import com.tools.guitartools.ui.views.GuitarTunerView

class TunerFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tuner, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get reference to GuitarTunerView
        val guitarTunerView = view.findViewById<GuitarTunerView>(R.id.guitarTunerView)
        // Additional setup if needed
    }

}