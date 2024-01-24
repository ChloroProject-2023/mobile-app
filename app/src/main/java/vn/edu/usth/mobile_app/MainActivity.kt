package vn.edu.usth.mobile_app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import vn.edu.usth.mobile_app.databinding.ActivityMainBinding
import vn.edu.usth.mobile_app.ui.GlobalData
import vn.edu.usth.mobile_app.ui.admin.AdminAnalyticsFragment
import vn.edu.usth.mobile_app.ui.login.LoginActivity
import vn.edu.usth.mobile_app.ui.explore.ExploreFragment
import vn.edu.usth.mobile_app.ui.usermenu.UserMenuFragment
import vn.edu.usth.mobile_app.util.DataStoreUtil
import vn.edu.usth.mobile_app.util.SecurityUtil

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "mpap_datastore")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val fragmentManager = supportFragmentManager
    private lateinit var dataStoreUtil: DataStoreUtil
    private lateinit var securityUtil: SecurityUtil
    private fun initializeSecuredStore(){
        val dataStore = this.dataStore
        securityUtil = SecurityUtil()
        dataStoreUtil = DataStoreUtil(dataStore, securityUtil)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            replaceFragment(ExploreFragment())
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (!::dataStoreUtil.isInitialized) initializeSecuredStore()

        DataStoreUtil.getInstance(this.dataStore, securityUtil)
        val bottomBar = binding.bottomNavigationViewMain
        bottomBar.menu.findItem(R.id.navAdmin).isVisible = GlobalData.isAdmin
        // If not login, change menu icon and title to login

        if(!GlobalData.isLogin) {
            val menu = bottomBar.menu.findItem(R.id.navMenu)
            menu.title = "Login"
            menu.icon = AppCompatResources.getDrawable(this, R.drawable.baseline_login_24)
        }

        // Example of using DataStoreUtil to store and retrieve data
        lifecycleScope.launch {
            dataStoreUtil.setData(stringPreferencesKey("host"), "http://192.168.1.7")
            dataStoreUtil.setData(stringPreferencesKey("security_port"), "8080")
            dataStoreUtil.setData(stringPreferencesKey("user_port"), "8081")

            dataStoreUtil.setSecuredData(stringPreferencesKey("username"), "admin")

            Log.d("DataStore", dataStoreUtil.getData(stringPreferencesKey("host")).first())
            Log.d("DataStore", dataStoreUtil.getSecuredData(stringPreferencesKey("username")).first())
        }

        bottomBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navExplore -> {
                    replaceFragment(ExploreFragment())
                    true
                }

                R.id.navMenu -> {
                    if (!GlobalData.isLogin) {
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
                if(GlobalData.isLogin) { return@setOnItemReselectedListener }
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        val toolbar = binding.materialToolbarMain
        toolbar.background = bottomBar.background
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout_main_fragmentContainer, fragment)
        fragmentTransaction.commit()
    }
}