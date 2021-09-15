package com.hfad.aplicmaterdis.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButtonToggleGroup
import com.hfad.aplicmaterdis.R
import com.hfad.aplicmaterdis.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListenerChips()
    }

    private fun initListenerChips() {
        binding.buttonGroup.addOnButtonCheckedListener(object :
            MaterialButtonToggleGroup.OnButtonCheckedListener {
            override fun onButtonChecked(
                group: MaterialButtonToggleGroup?,
                checkedId: Int,
                isChecked: Boolean
            ) {
         val theme = when(checkedId){
              binding.darkStyle.id -> R.style.ThemeNew
              binding.lightStyle.id -> AppCompatDelegate.MODE_NIGHT_NO
              binding.customStyle.id -> AppCompatDelegate.MODE_NIGHT_UNSPECIFIED
             else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
         }
         AppCompatDelegate.setDefaultNightMode(theme)
            }
        })


        /*binding.chip4.setOnClickListener { Toast.makeText(context,binding.chip4.isChecked.toString(),Toast.LENGTH_SHORT).show()
                activity?.setTheme(R.style.ThemeNew)*/

    }


    companion object {

        @JvmStatic
        fun newInstance() = SettingsFragment()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}