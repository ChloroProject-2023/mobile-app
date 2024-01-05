package vn.edu.usth.mobile_app.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class UserRoles {
    @SerialName("admin")
    ADMIN,
    @SerialName("user")
    USER,
    @SerialName("guest")
    GUEST
}