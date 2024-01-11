package vn.edu.usth.mobile_app.model.remote

import kotlinx.serialization.Serializable

@Serializable
data class RemoteHistory(
    val resourceId: Int,
    val modelId: Int,
    val userId: Int,
)
