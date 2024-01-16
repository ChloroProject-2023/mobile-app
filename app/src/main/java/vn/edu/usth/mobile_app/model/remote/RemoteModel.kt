package vn.edu.usth.mobile_app.model.remote

import kotlinx.serialization.Serializable
import vn.edu.usth.mobile_app.model.ModelData

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
    return ModelData(
        id = id,
        name = name,
        description = description,
        modelPath = filepath,
        type = type,
        creatorName = username!!,
        createdAt = 0,
        usage = usageCount,
    )
}