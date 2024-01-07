package vn.edu.usth.mobile_app.model.remote

import kotlinx.serialization.Serializable
import vn.edu.usth.mobile_app.model.ModelData

@Serializable
data class RemoteModel(
    val id: Int,
    val name: String,
    val type: String,
    val filePath: String,
    val description: String,
    val stars: Float? = null,
    val userId: Int,
    val username: String? = null,
    val createdTime: String,
)

fun RemoteModel.toModelData(): ModelData {
    return ModelData(
        id = id,
        name = name,
        description = description,
        modelPath = filePath,
        type = type,
        creatorName = username!!,
        createdAt = createdTime.toLong(),
        usage = 0
    )
}