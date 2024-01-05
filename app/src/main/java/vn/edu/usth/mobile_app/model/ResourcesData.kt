package vn.edu.usth.mobile_app.model

import kotlinx.serialization.Serializable

@Serializable
data class ResourcesData(
    val id: Int = -1,
    val fileName: String = "",
    val type: String = "",
    val date: Long = 1
)
