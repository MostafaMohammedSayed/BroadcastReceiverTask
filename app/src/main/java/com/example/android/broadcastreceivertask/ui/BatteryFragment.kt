package com.example.android.broadcastreceivertask.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.example.android.broadcastreceivertask.R
import kotlinx.android.synthetic.main.fragment_battery.*

class BatteryFragment : Fragment() {
    lateinit var mReceiver: BroadcastReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_battery, container, false)
    }

    private fun setupBatteryBroadcast() {
        mReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent?.action == "android.intent.action.BATTERY_CHANGED") {
                    val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                    tvBatteryLevel.text = level.toString().plus(" ").plus("%")
                    batteryLevelProgress.progress = level
                }
            }

        }
        val intentFilter = IntentFilter("android.intent.action.BATTERY_CHANGED")
        context?.registerReceiver(mReceiver, intentFilter)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBatteryBroadcast()
    }

    override fun onDestroyView() {
        context?.unregisterReceiver(mReceiver)
        super.onDestroyView()
    }
}