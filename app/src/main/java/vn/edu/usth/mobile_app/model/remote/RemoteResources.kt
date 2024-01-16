package vn.edu.usth.mobile_app.model.remote

import kotlinx.serialization.Serializable
import vn.edu.usth.mobile_app.model.ResourcesData

@Serializable
data class RemoteResources(
    val id: Int = -1,
    val filepath: String = "",
    val type: String = "",
    val date: Long = 1
)

fun RemoteResources.toResourceData(): ResourcesData {
    val fileName = filepath.split("/").last()
    return ResourcesData(
        id = id,
        fileName = fileName,
        type = type,
        date = date
    )
}
