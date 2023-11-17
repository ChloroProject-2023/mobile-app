package vn.edu.usth.mobile_app.ui.signup

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import vn.edu.usth.mobile_app.MainActivity
import vn.edu.usth.mobile_app.databinding.ActivitySignUpBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val viewModel: SignupViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val fnField = binding.textInputLayoutSignupFnField
        val lnField = binding.textInputLayoutSignupLnField
        val usernameField = binding.textInputLayoutSignupUsernameField
        val passwordField = binding.textInputLayoutSignupPwField
        val confirmPasswordField = binding.textInputLayoutSignupCfField

        val firstname = binding.editTextSignupFirstName
        val lastname = binding.editTextSignupLastName
        val username = binding.editTextSignupUsername
        val password = binding.editTextSignupPassword
        val confirmPassword = binding.editTextSignupPassCf

        val backButton = binding.buttonSignupBack
        backButton.setOnClickListener {
            finish()
        }

        val signup = binding.buttonSignup
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
            if (password.text.toString() != confirmPassword.text.toString()){
                confirmPasswordField.error = "Password does not match"
                return@setOnClickListener
            }

            val result = viewModel.submit(
                firstname.text.toString(),
                lastname.text.toString(),
                username.text.toString(),
                password.text.toString()
            )
            if (!result) {
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

        val login = binding.buttonSignupLogin
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