package com.hfad.aplicmaterdis.view.viewPagers

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.slider.Slider
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import com.google.android.youtube.player.YouTubePlayerView
import com.hfad.aplicmaterdis.R
import com.hfad.aplicmaterdis.databinding.FragmentWeatherBinding
import com.hfad.aplicmaterdis.model.TestSlider
import com.hfad.aplicmaterdis.viewModel.ViewModetTest


class WeatherFragment : Fragment() {
    // lateinit var youTubeVideo: YouTubePlayerView
    private var slederData: TestSlider = TestSlider()
    private lateinit var viewModel: ViewModetTest
    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(ViewModetTest::class.java)
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)


        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_view_earth -> {
                    var df = view?.findViewById<ImageView>(R.id.image_navigation_view)
                    df?.setImageResource(android.R.drawable.sym_contact_card)
                    Log.i("TEST", "view_earth")
                    true
                }
                R.id.bottom_view_mars -> {
                    var df = view?.findViewById<ImageView>(R.id.image_navigation_view)
                    df?.setImageResource(android.R.drawable.sym_def_app_icon)
                    Log.i("TEST", "view_mars")
                    true
                }
                R.id.bottom_view_weather -> {
                    var df = view?.findViewById<ImageView>(R.id.image_navigation_view)
                    df?.setImageResource(android.R.drawable.sym_action_email)
                    Log.i("TEST", "view_weather")
                    true
                }
                else -> false
            }
        }
        binding.bottomNavigationView.getOrCreateBadge(R.id.bottom_view_earth)
        binding.bottomNavigationView.getOrCreateBadge(R.id.bottom_view_mars)

        /*youTubeVideo = binding.youtubePlayer
        val listener  = object : YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                p1?.loadVideo("qAHMCZBwYo4")
                p1?.play()
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show()
            }
        }
        youTubeVideo.initialize("AIzaSyCHBBpZ3EKRzf6_m4nsuHW4we6uBYKnnV4", listener)*/


        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { redrer(it) })
        binding.sliderTest.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {

            }

            override fun onStopTrackingTouch(slider: Slider) {
                slederData.znach = slider.value
                viewModel.getLiveDataTest(slider.value)
            }
        })

    }

    private fun redrer(it: TestSlider) {
        var qwe = it.znach.toString()
        binding.textTest.text = it.znach.toString()
        var inte = it.znach?.toInt()
        if (inte < 30) binding.bottomNavigationView.selectedItemId = R.id.bottom_view_earth
        if (inte in 30..59) {
            binding.bottomNavigationView.selectedItemId = R.id.bottom_view_mars
        }
        if (inte > 60){
            binding.bottomNavigationView.selectedItemId = R.id.bottom_view_weather

        }
        val badge =  binding.bottomNavigationView.getBadge(R.id.bottom_view_mars)
        badge?.number = inte
    }


    companion object {

        @JvmStatic
        fun newInstance() = WeatherFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}