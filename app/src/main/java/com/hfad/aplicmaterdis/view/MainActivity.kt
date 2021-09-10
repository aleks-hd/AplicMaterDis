package com.hfad.aplicmaterdis.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hfad.aplicmaterdis.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setFragment()
    }

    private fun setFragment() {

        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.main, MainFragment.newInstance())
            .commit()
    }
}
