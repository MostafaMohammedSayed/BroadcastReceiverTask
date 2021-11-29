package com.example.android.broadcastreceivertask.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.telephony.TelephonyManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.android.broadcastreceivertask.R
import kotlinx.android.synthetic.main.fragment_sim_signal.*

class SimSignalFragment : Fragment() {

    lateinit var mSimReceiver : BroadcastReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sim_signal, container, false)
    }

    private fun setupSignalBroadcast() {
        mSimReceiver = object : BroadcastReceiver() {
            @RequiresApi(Build.VERSION_CODES.P)
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent?.action == "android.intent.action.SIM_STATE_CHANGED") {
                    val telephoneMgr =
                        context!!.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                    val simState = telephoneMgr.signalStrength?.gsmSignalStrength
                    tvSimStrength.text = simState.toString()
                }

            }

        }
        val intentFilter = IntentFilter("android.intent.action.SIM_STATE_CHANGED")
        context?.registerReceiver(mSimReceiver, intentFilter)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSignalBroadcast()
    }
}