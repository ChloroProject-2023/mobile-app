package vn.edu.usth.mobile_app.ui.modeldetails

import androidx.lifecycle.ViewModel
import vn.edu.usth.mobile_app.model.ModelData

class ModelDetailsViewModel: ViewModel() {
    private var _modelID: Int = -1
    private var _modelName: String = ""
    private var _author: String = ""
    private var _description: String = ""
    private var _ratings: Map<String, Int> = emptyMap()
    private var _mostUsedModels: List<ModelData> = emptyList()
    private var _similarModels: List<ModelData> = emptyList()

    val modelId: Int get() = _modelID
    val modelName: String get() = _modelName
    val author: String get() = _author
    val description: String get() = _description
    val ratings: Map<String, Int> get() = _ratings
    val mostUsedModels: List<ModelData> get() = _mostUsedModels
    val similarModels: List<ModelData> get() = _similarModels

    init {
        _modelName = "NPK AI"
        _author = "USTH"
        _description = "This model is used to predict the NPK content of soil"
        _ratings = mapOf(
            "1" to 12,
            "2" to 2,
            "3" to 10,
            "4" to 30,
            "5" to 100,)
        _mostUsedModels = listOf(1, 2, 3, 4, 5).map {
            ModelData(
                id = it,
                name = "NPK AI",
                description = "This model is used to predict the NPK content of soil",
                modelPath = "",
                type = "AI",
                creatorName = "USTH",
                createdAt = 1,
                usage = 100
            )
        }
        _similarModels = listOf(1, 2, 3, 4, 5).map {
            ModelData(
                id = it,
                name = "NPK AI",
                description = "This model is used to predict the NPK content of soil",
                modelPath = "",
                type = "AI",
                creatorName = "USTH",
                createdAt = 1,
                usage = 100
            )
        }
    }

    fun setModelID(modelID: Int) {
        _modelID = modelID
    }

    fun fetchModelDetails() {
        // Fetch model details from server
    }
}