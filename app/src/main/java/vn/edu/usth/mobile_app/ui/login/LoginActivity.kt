package vn.edu.usth.mobile_app.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import vn.edu.usth.mobile_app.MainActivity
import vn.edu.usth.mobile_app.R
import vn.edu.usth.mobile_app.databinding.ActivityLogInBinding
import vn.edu.usth.mobile_app.ui.signup.SignupActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding
    private val viewModel: LoginViewModel by viewModels()
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "login")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        val usernameField = binding.textInputLayoutLoginUsername
        val passwordField = binding.textInputLayoutLoginPassword

        val username = binding.editTextLoginUsername
        val password = binding.editTextLoginPassword
        passwordField.setEndIconOnClickListener {
            if (password.transformationMethod == null) {
                password.transformationMethod = android.text.method.PasswordTransformationMethod()
                passwordField.setEndIconDrawable(R.drawable.baseline_visibility_24)
            } else {
                password.transformationMethod = null
                passwordField.setEndIconDrawable(R.drawable.baseline_visibility_off_24)
            }
        }


        val signup = binding.buttonLoginSignup
        signup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        val login = binding.buttonLogin
        login.setOnClickListener {
            usernameField.error = null
            passwordField.error = null
            if (username.text.toString().isEmpty()){
                usernameField.error = "Username is required"
                return@setOnClickListener
            }
            if (password.text.toString().isEmpty()){
                passwordField.error = "Password is required"
                return@setOnClickListener
            }
            viewModel.submit(username.text.toString(), password.text.toString())
        }

        viewModel.isLogin.observe(this) {
            Log.d("Login", "observe called")
            if (it) {
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Username or password is incorrect", Toast.LENGTH_SHORT).show()
            }
        }

        this.onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
        })
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as android.view.inputmethod.InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }
}