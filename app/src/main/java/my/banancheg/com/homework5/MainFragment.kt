package my.banancheg.com.homework5

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(), View.OnClickListener {

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.button_system_info -> {
                replaceFragment(FragmentSystemInfo())
            }
            R.id.button_fragments_communication -> {
                replaceFragment(CommunicationFragment())
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    private fun replaceFragment(fragment: Fragment){
        fragmentManager?.apply {
            beginTransaction()
                .replace(R.id.fragment_framelayout, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity!!.title = "MySystemApp"
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        super.onViewCreated(view, savedInstanceState)
        button_fragments_communication.setOnClickListener(this)
        button_system_info.setOnClickListener(this)
    }

}