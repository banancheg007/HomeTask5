package my.banancheg.com.homework5

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_framelayout, MainFragment())
            .commit()
        setContentView(R.layout.activity_main)
    }
}
