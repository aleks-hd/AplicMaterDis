package com.hfad.aplicmaterdis.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.hfad.aplicmaterdis.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       val keyCheckTheme = intent.extras?.getString("CheckTheme")

        when(keyCheckTheme){
              "dark" -> setTheme(R.style.ThemeNew)
            "light" ->  setTheme(R.style.ThemeNewLight)
            else -> Toast.makeText(this,"Тема не найдена", Toast.LENGTH_SHORT).show()
        }

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
