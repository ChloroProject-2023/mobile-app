package vn.edu.usth.mobile_app.ui.signup

import androidx.lifecycle.ViewModel
import vn.edu.usth.mobile_app.ui.GlobalData

class SignupViewModel: ViewModel() {
    fun submit(
        firstName: String,
        lastName: String,
        username: String,
        password: String
    ): Boolean {
        GlobalData.userId = 1
        GlobalData.isLogin = true
        GlobalData.isAdmin = true
        return true
    }
}