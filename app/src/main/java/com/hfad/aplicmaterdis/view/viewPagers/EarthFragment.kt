package com.hfad.aplicmaterdis.view.viewPagers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.api.load
import com.hfad.aplicmaterdis.BuildConfig
import com.hfad.aplicmaterdis.R
import com.hfad.aplicmaterdis.databinding.FragmentEarthBinding



class EarthFragment : Fragment() {
    private var _binding: FragmentEarthBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEarthBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initImage()
    }

    private fun initImage() {
        val apiKey: String = BuildConfig.NASA_API_KEY
        val urlik = "https://api.nasa.gov/planetary/earth/imagery?lon=-95.33&lat=29.78&date=2018-01-01&dim=0.15&api_key=${apiKey}"

        binding.imageView.load(urlik)
    }

    companion object {
        @JvmStatic
        fun newInstance() =EarthFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}