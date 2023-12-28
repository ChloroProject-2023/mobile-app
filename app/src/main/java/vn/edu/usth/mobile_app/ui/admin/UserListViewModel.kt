package vn.edu.usth.mobile_app.ui.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import vn.edu.usth.mobile_app.model.UserData
import vn.edu.usth.mobile_app.model.UserRoles

class UserListViewModel: ViewModel() {
    private val _userList = mutableListOf<UserData>()
    val userList get() = _userList

    init {
        viewModelScope.launch {
            fetchUserList()
        }
    }

    private fun fetchUserList() {
        // Dummy data
        for (i in 1..10) {
            _userList.add(
                UserData(
                    id = 1,
                    firstname = "Nguyen",
                    lastname = "Van A",
                    username = "Test",
                    email = "test@abc.com",
                    avatarUrl = "",
                    role = UserRoles.USER,
                    historyList = emptyList())
            )
        }
    }
}