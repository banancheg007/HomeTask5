package my.banancheg.com.homework5

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class CommunicationFragment: Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity!!.title = "Fragments communication"
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val view: View = inflater!!.inflate(R.layout.fragment_communication, container, false)
        return view
    }
}