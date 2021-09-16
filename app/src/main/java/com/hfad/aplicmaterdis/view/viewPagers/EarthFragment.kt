package com.hfad.aplicmaterdis.view.viewPagers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hfad.aplicmaterdis.R


class EarthFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_earth, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() =EarthFragment()
    }
}