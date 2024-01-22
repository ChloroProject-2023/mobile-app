package vn.edu.usth.mobile_app.ui.usermenu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import vn.edu.usth.mobile_app.databinding.ActivityAppVersionBinding

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