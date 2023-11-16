package vn.edu.usth.mobile_app.ui

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import vn.edu.usth.mobile_app.MainActivity
import vn.edu.usth.mobile_app.R

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val fnField = findViewById<TextInputLayout>(R.id.textInputLayout_signup_fnField)
        val lnField = findViewById<TextInputLayout>(R.id.textInputLayout_signup_lnField)
        val usernameField = findViewById<TextInputLayout>(R.id.textInputLayout_signup_usernameField)
        val passwordField = findViewById<TextInputLayout>(R.id.textInputLayout_signup_pwField)
        val confirmPasswordField = findViewById<TextInputLayout>(R.id.textInputLayout_signup_cfField)

        val firstname = findViewById<TextView>(R.id.editText_signup_firstName)
        val lastname = findViewById<TextView>(R.id.editText_signup_lastName)
        val username = findViewById<TextView>(R.id.editText_signup_username)
        val password = findViewById<TextView>(R.id.editText_signup_password)
        val confirmPassword = findViewById<TextView>(R.id.editText_signup_passCf)

        val backButton = findViewById<MaterialButton>(R.id.button_signup_back)
        backButton.setOnClickListener {
            finish()
        }

        val signup = findViewById<Button>(R.id.button_signup)
        signup.setOnClickListener {
            fnField.error = null
            lnField.error = null
            usernameField.error = null
            passwordField.error = null
            confirmPasswordField.error = null

            if (firstname.text.toString().isEmpty()){
                fnField.error = "First name is required"
                return@setOnClickListener
            }
            if (lastname.text.toString().isEmpty()){
                lnField.error = "Last name is required"
                return@setOnClickListener
            }
            if (username.text.toString().isEmpty()){
                usernameField.error = "Username is required"
                return@setOnClickListener
            }
            if (password.text.toString().isEmpty()){
                passwordField.error = "Password is required"
                return@setOnClickListener
            }
            if (confirmPassword.text.toString().isEmpty()){
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