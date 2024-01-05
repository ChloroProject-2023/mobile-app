package vn.edu.usth.mobile_app.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import vn.edu.usth.mobile_app.network.KtorClient

class LoginViewModel: ViewModel() {
    private val _isLogin = MutableLiveData<Boolean>()
    val isLogin: LiveData<Boolean> = _isLogin

    fun submit(username: String, password: String) {
        viewModelScope.launch {
            val response = KtorClient().login(username, password)
            _isLogin.value = response
        }
    }
}