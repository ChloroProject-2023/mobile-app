package vn.edu.usth.mobile_app.model

import kotlinx.serialization.Serializable

@Serializable
data class ModelData (
    var id: Int = -1,
    var name: String = "",
    var description: String = "",
    var modelPath: String = "",
    var type: String = "",
    var creatorName: String = "",
    var createdAt: Long = 1,
    var usage: Int = 0
)