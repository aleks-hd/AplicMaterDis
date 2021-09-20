package com.hfad.aplicmaterdis.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.hfad.aplicmaterdis.view.viewPagers.EarthFragment
import com.hfad.aplicmaterdis.view.viewPagers.MarsFragment
import com.hfad.aplicmaterdis.view.viewPagers.WeatherFragment

class ViewPagerAdapter(private val fragment: FragmentManager): FragmentStatePagerAdapter(fragment, BEHAVIOR_SET_USER_VISIBLE_HINT) {

    private val fragments = arrayOf( MarsFragment(), EarthFragment(),WeatherFragment())

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Mars"
            1 -> "Earth"
            2 -> "Weather"
         else -> "Mars"
        }
    }

    override fun getItem(position: Int): Fragment {
       return when(position){
           0 -> fragments[0]
           1 -> fragments[1]
           2 -> fragments[2]
           else -> fragments[1]
       }
    }

    override fun getCount(): Int {
        return fragments.size
    }




}