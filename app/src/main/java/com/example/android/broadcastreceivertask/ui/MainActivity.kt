package com.example.android.broadcastreceivertask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.broadcastreceivertask.R
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpViewPager()
    }

    private fun setUpViewPager(){
        val mAdapter = ViewPagerAdapter(supportFragmentManager,lifecycle)
        viewPager.adapter = mAdapter
        TabLayoutMediator(pagerHeader, viewPager) { tab, position ->
            tab.text = if (position==0) "Battery" else "Sim Signal"
        }.attach()
    }
}