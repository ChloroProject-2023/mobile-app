package vn.edu.usth.mobile_app

import androidx.lifecycle.ViewModel
import vn.edu.usth.mobile_app.ui.GlobalData

class MainViewModel: ViewModel() {
    fun logout() {
        GlobalData.isLogin = false
        GlobalData.isAdmin = false
        GlobalData.userId = -1
    }
}