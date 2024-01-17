package vn.edu.usth.mobile_app.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import vn.edu.usth.mobile_app.R
import vn.edu.usth.mobile_app.databinding.ActivityAppVersionBinding
import vn.edu.usth.mobile_app.ui.usermenu.UserMenuFragment

class AppVersionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAppVersionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppVersionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar = binding.materialToolbarAppVersion
        toolbar.setNavigationOnClickListener {finish()}
    }
}