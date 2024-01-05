package vn.edu.usth.mobile_app.model

import kotlinx.serialization.Serializable

@Serializable
data class UserData (
    var id: Int = -1,
    var firstname: String = "",
    var lastname: String = "",
    var username: String = "",
    var email: String = "",
    var avatarUrl: String = "",
    var role: UserRoles = UserRoles.USER,
    var historyList: List<HistoryData> = ArrayList(),
    var createdAt: Long = 1,
)