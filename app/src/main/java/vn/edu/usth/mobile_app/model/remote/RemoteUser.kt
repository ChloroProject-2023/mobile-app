package vn.edu.usth.mobile_app.model.remote

import kotlinx.serialization.Serializable
import vn.edu.usth.mobile_app.model.UserData
import vn.edu.usth.mobile_app.model.UserRoles
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Serializable
data class RemoteUser(
    val id: Int? = null,
    val username: String,
    val password: String? = null,
    val role: String? = null,
    val userDetail: RemoteUserDetails,
    val createTime: String? = null,
) {
    @Serializable
    data class RemoteUserDetails(
        val firstname: String,
        val lastname: String,
        val email: String,
    )
}

fun RemoteUser.toUserData(): UserData {
    val userRole = when(role) {
        "admin" -> UserRoles.ADMIN
        "user" -> UserRoles.USER
        else -> UserRoles.GUEST
    }
    val id = id ?: -1
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.US)
    val date = format.parse(createTime!!)?: Date(1)
    val timestamp = date.time

    return UserData(
        id = id,
        username = username,
        firstname = userDetail.firstname,
        lastname = userDetail.lastname,
        email = userDetail.email,
        role = userRole,
        createdAt = timestamp,
    )
}