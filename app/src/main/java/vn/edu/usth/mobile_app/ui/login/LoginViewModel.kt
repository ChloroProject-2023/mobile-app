package vn.edu.usth.mobile_app.ui.login

import androidx.lifecycle.ViewModel
import vn.edu.usth.mobile_app.ui.GlobalData

class LoginViewModel: ViewModel() {
    fun submit(username: String, password: String): Boolean {
        GlobalData.userId = 1
        GlobalData.isLogin = true
        GlobalData.isAdmin = true
        return true
    }
}