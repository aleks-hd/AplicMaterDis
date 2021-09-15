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
        binding.buttonGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->
            when (checkedId) {
                binding.darkStyle.id -> setIntentTheme("dark")
                binding.lightStyle.id -> setIntentTheme("light")
                binding.customStyle.id -> setIntentTheme("custom")
                else -> ""
            }
        }
    }

    fun setIntentTheme(nameTheme: String) {
        activity?.let {
            val intent = Intent(it, MainActivity::class.java)
            intent.putExtra("CheckTheme", nameTheme)
            startActivity(intent)
        }
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