package vn.edu.usth.mobile_app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import vn.edu.usth.mobile_app.ui.LoginActivity
import vn.edu.usth.mobile_app.ui.UploadFragment


class MainActivity : AppCompatActivity() {
    private var isLogin = false
    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (intent.extras != null) {
            isLogin = intent.extras!!.getBoolean("isLogin")
        }

        val toolbar = findViewById<Toolbar>(R.id.materialToolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        replaceFragment(UploadFragment())

        val bottomBar = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navUpload -> {
                    replaceFragment(UploadFragment())
                    true
                }

                R.id.navHistory -> {
                    if (!isLogin) {
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    }
                    true
                }

                R.id.navSettings -> {
                    true
                }
                else -> false
            }
        }
        bottomBar.setOnItemReselectedListener {}

//        // Set the listener for both navigation bar and navigation drawer
//        // Doesn't work ???

//        NavigationBarView.OnItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.navUpload -> {
//                    true
//                }
//                R.id.navHistory -> {
//                    println("\n\n\nHistory\n\n\n")
//                    if (!isLogin) {
//                        println("\n\n\nNot logged in!\n\n\n")
//                        val intent = Intent(this, LoginActivity::class.java)
//                        startActivity(intent)
//                    }
//                    true
//                }
//                R.id.navSettings -> {
//                  true
//                }
//                else -> false
//            }
//        }
//        NavigationBarView.OnItemReselectedListener { item ->
//            when (item.itemId) {
//                R.id.navUpload -> {}
//                R.id.navHistory -> {}
//                R.id.navSettings -> {}
//            }
//        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}