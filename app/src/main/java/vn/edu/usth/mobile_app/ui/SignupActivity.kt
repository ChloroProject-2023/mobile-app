package vn.edu.usth.mobile_app.ui

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import vn.edu.usth.mobile_app.MainActivity
import vn.edu.usth.mobile_app.R

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val fnField = findViewById<TextView>(R.id.editText_signup_firstName)
        val lnField = findViewById<TextView>(R.id.editText_signup_lastName)
        val usernameField = findViewById<TextView>(R.id.editText_signup_username)
        val passwordField = findViewById<TextView>(R.id.editText_signup_password)
        val confirmPasswordField = findViewById<TextView>(R.id.editText_signup_passCf)

        val backButton = findViewById<MaterialButton>(R.id.button_signup_back)
        backButton.setOnClickListener {
            finish()
        }

        val signup = findViewById<Button>(R.id.button_signup)
        signup.setOnClickListener {

            if (fnField.text.toString().isEmpty()){
                fnField.error = "First name is required"
                return@setOnClickListener
            }
            if (lnField.text.toString().isEmpty()){
                lnField.error = "Last name is required"
                return@setOnClickListener
            }
            if (usernameField.text.toString().isEmpty()){
                usernameField.error = "Username is required"
                return@setOnClickListener
            }
            if (passwordField.text.toString().isEmpty()){
                passwordField.error = "Password is required"
                return@setOnClickListener
            }
            if (confirmPasswordField.text.toString().isEmpty()){
                confirmPasswordField.error = "Please confirm your password"
                return@setOnClickListener
            }


            val intent = Intent(this, MainActivity::class.java)
            intent.putExtras(
                Bundle().apply {
                    putBoolean("isLogin", true)
                    putBoolean("isAdmin", true)
                }
            )
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        val login = findViewById<TextView>(R.id.button_signup_login)
        login.setOnClickListener {
            finish()
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as android.view.inputmethod.InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }
}