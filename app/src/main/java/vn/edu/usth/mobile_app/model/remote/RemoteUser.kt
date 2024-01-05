package vn.edu.usth.mobile_app.model.remote

import kotlinx.serialization.Serializable

@Serializable
data class RemoteUser(
    val username: String,
    val password: String,
    val userDetails: RemoteUserDetails
) {
    @Serializable
    data class RemoteUserDetails(
        val firstName: String,
        val lastName: String,
        val email: String,
    )
}