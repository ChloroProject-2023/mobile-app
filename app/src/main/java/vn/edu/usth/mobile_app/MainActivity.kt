package vn.edu.usth.mobile_app

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import vn.edu.usth.mobile_app.databinding.ActivityMainBinding
import vn.edu.usth.mobile_app.ui.admin.AdminAnalyticsFragment
import vn.edu.usth.mobile_app.ui.login.LoginActivity
import vn.edu.usth.mobile_app.ui.explore.ExploreFragment
import vn.edu.usth.mobile_app.ui.usermenu.UserMenuFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.extras != null) {
            viewModel.setIsLogin(intent.extras!!.getBoolean("isLogin"))
            viewModel.setIsAdmin(intent.extras!!.getBoolean("isAdmin"))
            viewModel.setUserId(intent.extras!!.getInt("userId"))
        }

        val bottomBar = binding.bottomNavigationViewMain
        bottomBar.menu.findItem(R.id.navAdmin).isVisible = viewModel.isAdmin

        // If not login, change menu icon and title to login
        if(!viewModel.isLogin) {
            val menu = bottomBar.menu.findItem(R.id.navMenu)
            menu.title = "Login"
            menu.icon = AppCompatResources.getDrawable(this, R.drawable.baseline_login_24)
        }

        bottomBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navExplore -> {
                    replaceFragment(ExploreFragment())
                    true
                }

                R.id.navMenu -> {
                    if (!viewModel.isLogin) {
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    }
                    else {
                        replaceFragment(UserMenuFragment())
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
        bottomBar.setOnItemReselectedListener { item ->
            if(item.itemId == R.id.navMenu) {
                if(viewModel.isLogin) { return@setOnItemReselectedListener }
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        val toolbar = binding.materialToolbarMain
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