package com.hfad.aplicmaterdis.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.hfad.aplicmaterdis.view.viewPagers.EarthFragment
import com.hfad.aplicmaterdis.view.viewPagers.MarsFragment
import com.hfad.aplicmaterdis.view.viewPagers.WeatherFragment

class ViewPagerAdapter(private val fragment: FragmentManager): FragmentStatePagerAdapter(fragment) {

    private val fragments = arrayOf(EarthFragment(), MarsFragment(), WeatherFragment())

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Earth"
            1 -> "Mars"
            2 -> "Weather"
         else -> "Earth"
        }
    }

    override fun getItem(position: Int): Fragment {
       return when(position){
           0 -> fragments[0]
           1 -> fragments[1]
           2 -> fragments[2]
           else -> fragments[0]
       }
    }

    override fun getCount(): Int {
        return fragments.size
    }




}