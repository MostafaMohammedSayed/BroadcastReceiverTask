package com.example.android.broadcastreceivertask.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fm: FragmentManager,lc: Lifecycle): FragmentStateAdapter(fm,lc){
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> BatteryFragment()
            else -> SimSignalFragment()
        }
    }

}