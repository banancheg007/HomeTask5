package my.banancheg.com.homework5

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.CompoundButton.OnCheckedChangeListener

import kotlinx.android.synthetic.main.fragment_blue.*

class BlueFragment: Fragment(), View.OnTouchListener, OnCheckedChangeListener, TextWatcher{

    override fun afterTextChanged(s: Editable?) {
        val myIntent = Intent("MY_ACTION")
        myIntent.putExtra("EDIT_TEXT", s.toString())
        context?.sendBroadcast(myIntent)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        val myIntent = Intent("MY_ACTION")
        myIntent.putExtra("SWITCH_STATUS", isChecked)
        context?.sendBroadcast(myIntent)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        val myIntent = Intent("MY_ACTION")
        when(event?.action) {
            MotionEvent.ACTION_UP -> myIntent.putExtra("TOUCH_BUTTON", false)
            MotionEvent.ACTION_DOWN -> myIntent.putExtra("TOUCH_BUTTON", true)
        }
        context?.sendBroadcast(myIntent)
        return false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_blue, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        blue_fragment_button.setOnTouchListener(this)
        blue__fragment_switch.setOnCheckedChangeListener(this)
        blue_fragment_editText.addTextChangedListener(this)
    }
}