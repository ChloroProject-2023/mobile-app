package vn.edu.usth.mobile_app

import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private var _userId: Int = -1
    private var _isLogin: Boolean = false
    private var _isAdmin: Boolean = false

    val userId: Int get() = _userId
    val isLogin: Boolean get() = _isLogin
    val isAdmin: Boolean get() = _isAdmin

    fun setUserId(userId: Int) {
        _userId = userId
    }
    fun setIsLogin(isLogin: Boolean) {
        _isLogin = isLogin
    }
    fun setIsAdmin(isAdmin: Boolean) {
        _isAdmin = isAdmin
    }
}