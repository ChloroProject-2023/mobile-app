package vn.edu.usth.mobile_app.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import vn.edu.usth.mobile_app.network.KtorClient
import vn.edu.usth.mobile_app.ui.GlobalData

class SignupViewModel: ViewModel() {
    private val _isLogin = MutableLiveData<Boolean>()
    val isLogin: LiveData<Boolean> = _isLogin

    fun submit(
        firstName: String,
        lastName: String,
        username: String,
        password: String
    ) {
        viewModelScope.launch {
            val response = KtorClient.signup(username, password, firstName, lastName)
            _isLogin.value = response
        }
    }
}