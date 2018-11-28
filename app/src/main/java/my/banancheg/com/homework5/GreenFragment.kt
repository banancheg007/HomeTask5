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

class GreenFragment: Fragment(){

    val broadcastReceiver: BroadcastReceiver by lazy(this::GreenBroadcastReceiver)

    companion object {
        const val ACTION = "MY_ACTION"
    }

    override fun onStart() {
        super.onStart()
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            addAction(ACTION)
        }
        context?.registerReceiver(broadcastReceiver, intentFilter)
    }

    override fun onStop() {
        super.onStop()
        context?.unregisterReceiver(broadcastReceiver)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_green, container, false)
        return view
    }

    inner class GreenBroadcastReceiver : BroadcastReceiver() {

        private val textViewEdit: TextView = view!!.findViewById(R.id.textView_text_from_editText)
        private val textViewSwitch: TextView= view!!.findViewById(R.id.textView_is_switch)
        private val textViewButton: TextView= view!!.findViewById(R.id.textView_is_button_pressed)


        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                ACTION -> {
                    when {
                        intent!!.hasExtra("EDIT_TEXT") -> {
                            val textEditText = intent.getStringExtra("EDIT_TEXT")
                            textViewEdit.text = "Text: $textEditText"
                        }

                        intent!!.hasExtra("SWITCH_STATUS") -> {
                            val boolSwitch = intent.getBooleanExtra("SWITCH_STATUS", false)
                            if (boolSwitch) textViewSwitch.text = "Switch: \" On\""
                            else textViewSwitch.text = "Switch: \" Off\""
                        }

                        intent!!.hasExtra("TOUCH_BUTTON") -> {
                            val isSwitch = intent?.getBooleanExtra("TOUCH_BUTTON", false)
                            if (isSwitch!!) textViewButton.text = "Button state:\" Pressed\" "
                            else textViewButton.text = "Button state:\" Not pressed\" "
                        }
                    }
                }
            }
        }
    }
}