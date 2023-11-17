package vn.edu.usth.mobile_app.ui.signup

import androidx.lifecycle.ViewModel

class SignupViewModel: ViewModel() {
    fun submit(
        firstName: String,
        lastName: String,
        username: String,
        password: String
    ): Boolean {
        return true
    }
}