package vn.edu.usth.mobile_app.model.remote

import kotlinx.serialization.Serializable
import vn.edu.usth.mobile_app.model.ModelData
import java.text.SimpleDateFormat
import java.util.Locale

@Serializable
data class RemoteModel(
    val id: Int,
    val name: String,
    val type: String,
    val filepath: String,
    val description: String,
    val stars: Float? = null,
    val usageCount: Int,
    val user_id: Int,
    val username: String? = null,
    val createTime: String,
)

fun RemoteModel.toModelData(): ModelData {
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.US)
    val timestamp = format.parse(createTime)?.time

    return ModelData(
        id = id,
        name = name,
        description = description,
        modelPath = filepath,
        type = type,
        creatorName = username!!,
        createdAt = timestamp?: 1,
        usage = usageCount,
    )
}