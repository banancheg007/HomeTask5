package my.banancheg.com.homework5

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_system_info.*
import my.banancheg.com.homework5.FragmentSystemInfo.SystemBroadcastReceiver
import java.text.SimpleDateFormat
import java.util.*

class FragmentSystemInfo: Fragment(){

    val broadcastReceiver: BroadcastReceiver by lazy(this::SystemBroadcastReceiver)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_system_info, container, false)
    }

    override fun onStart() {
        super.onStart()
        context!!.registerReceiver(broadcastReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            addAction(ConnectivityManager.CONNECTIVITY_ACTION)
            addAction(Intent.ACTION_HEADSET_PLUG)
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
            addAction(Intent.ACTION_TIME_CHANGED)
            addAction(Intent.ACTION_TIMEZONE_CHANGED)
        })
    }

    override fun onStop() {
        super.onStop()
        context?.unregisterReceiver(broadcastReceiver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            updateInternetTextView()
        updateChargingTextView(intent = Intent())
        updateHeadPhonesTextView(intent = Intent())
        updateDateAndTimeZoneTextView()
    }

    fun updateInternetTextView(){
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager.activeNetworkInfo == null){
            textview_internet_connection_details.text = "INTERNET OFF"
        }
        else if (connectivityManager.activeNetworkInfo.isConnected && connectivityManager.activeNetworkInfo != null) {
            textview_internet_connection_details.text = "INTERNET ON"
        }
    }

    fun updateChargingTextView(intent: Intent){
        when(intent.action){
            Intent.ACTION_POWER_CONNECTED -> {
                textview_device_charging_state.setText("Charging ONN")
            }
            Intent.ACTION_POWER_DISCONNECTED -> {
                textview_device_charging_state.setText("Charging OFF")
            }
        }
    }

    fun updateHeadPhonesTextView(intent: Intent){
        val state = intent.getIntExtra("state", 0)
        when (state) {
            0 -> textview_headphones_or_headset_plugged.text = "HEADSET IS DISCONNECTED"
            1 -> textview_headphones_or_headset_plugged.text = "HEADSET IS CONNECTED"
        }
    }

    fun updateDateAndTimeZoneTextView(){
        val timeZone = TimeZone.getDefault()
        val simpleDateFormat = SimpleDateFormat("HH:mm:ss")
        val timeText =
            "Current time:  ${simpleDateFormat.format(Date())} Current timezone: ${timeZone.getDisplayName(false, TimeZone.SHORT)}"
        textview_current_date_and_time_zone.setText(timeText)
    }

    inner class SystemBroadcastReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            when (intent.action) {
                ConnectivityManager.CONNECTIVITY_ACTION -> {
                    updateInternetTextView()
                }
                Intent.ACTION_HEADSET_PLUG -> {
                    updateHeadPhonesTextView(intent)
                }
                Intent.ACTION_POWER_CONNECTED -> {
                    updateChargingTextView(intent)
                }
                Intent.ACTION_POWER_DISCONNECTED -> {
                    updateChargingTextView(intent)
                }
                Intent.ACTION_TIME_CHANGED -> {
                    updateDateAndTimeZoneTextView()
                }
                Intent.ACTION_TIMEZONE_CHANGED -> {
                    updateDateAndTimeZoneTextView()
                }
            }
        }
    }
}