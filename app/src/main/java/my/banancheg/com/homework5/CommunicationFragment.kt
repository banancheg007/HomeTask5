package my.banancheg.com.homework5

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class CommunicationFragment: Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity!!.title = "Fragments communication"
        val view: View = inflater!!.inflate(R.layout.fragment_communication, container, false)

        val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_blue, BlueFragment())
        fragmentTransaction.addToBackStack(null)
        //fragmentTransaction.commit()

        fragmentTransaction.replace(R.id.fragment_green, GreenFragment())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
        return view
    }
}