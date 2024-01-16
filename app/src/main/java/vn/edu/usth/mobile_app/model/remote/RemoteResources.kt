package vn.edu.usth.mobile_app.model.remote

import kotlinx.serialization.Serializable
import vn.edu.usth.mobile_app.model.ResourcesData
import java.text.SimpleDateFormat
import java.util.Locale

@Serializable
data class RemoteResources(
    val id: Int,
    val filepath: String,
    val type: String,
    val createTime: String
)

fun RemoteResources.toResourceData(): ResourcesData {
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.US)
    val timestamp = format.parse(createTime)?.time

    val fileName = filepath.split("/").last()
    return ResourcesData(
        id = id,
        fileName = fileName,
        type = type,
        date = timestamp?: 1,
    )
}
