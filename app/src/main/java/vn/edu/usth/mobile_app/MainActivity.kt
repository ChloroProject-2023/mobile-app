package vn.edu.usth.mobile_app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import vn.edu.usth.mobile_app.ui.AdminAnalyticsFragment
import vn.edu.usth.mobile_app.ui.HistoryFragment
import vn.edu.usth.mobile_app.ui.LoginActivity
import vn.edu.usth.mobile_app.ui.ExploreFragment


class MainActivity : AppCompatActivity() {
    private var isLogin = false
    private var isAdmin = false
    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (intent.extras != null) {
            isLogin = intent.extras!!.getBoolean("isLogin")
            isAdmin = intent.extras!!.getBoolean("isAdmin")
        }

        val bottomBar = findViewById<BottomNavigationView>(R.id.bottomNavigationView_main)
        bottomBar.menu.findItem(R.id.navAdmin).isVisible = isAdmin
        bottomBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navExplore -> {
                    replaceFragment(ExploreFragment())
                    true
                }

                R.id.navHistory -> {
                    if (!isLogin) {
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    }
                    else {
                        replaceFragment(HistoryFragment())
                    }
                    true
                }

                R.id.navAdmin -> {
                    replaceFragment(AdminAnalyticsFragment())
                    true
                }
                else -> false
            }
        }
        bottomBar.setOnItemReselectedListener {}

        val toolbar = findViewById<Toolbar>(R.id.materialToolbar_main)
        toolbar.background = bottomBar.background
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        replaceFragment(ExploreFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout_main_fragmentContainer, fragment)
        fragmentTransaction.commit()
    }
}