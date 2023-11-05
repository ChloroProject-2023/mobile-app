package vn.edu.usth.mobile_app.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import vn.edu.usth.mobile_app.MainActivity
import vn.edu.usth.mobile_app.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        val signup = findViewById<TextView>(R.id.button_login_signup)
        signup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        val login = findViewById<Button>(R.id.button_login)
        login.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtras(
                Bundle().apply {putBoolean("isLogin", true)}
            )
            startActivity(intent)
        }
    }
}