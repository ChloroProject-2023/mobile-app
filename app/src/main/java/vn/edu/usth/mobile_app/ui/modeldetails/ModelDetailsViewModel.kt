package vn.edu.usth.mobile_app.ui.modeldetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import vn.edu.usth.mobile_app.model.ModelData
import vn.edu.usth.mobile_app.network.KtorClient

class ModelDetailsViewModel: ViewModel() {
    private var _modelID: Int = -1
    private val _model = MutableLiveData<ModelData>()
    val ratings = mutableMapOf<Int, Int>()
    val mostUsedModels = mutableListOf<ModelData>()
    val similarModels = mutableListOf<ModelData>()

    val modelId: Int get() = _modelID
    val model: LiveData<ModelData> get() = _model

    init {
        ratings.putAll(mapOf(
            1 to 12,
            2 to 2,
            3 to 10,
            4 to 30,
            5 to 100,))

        mostUsedModels.addAll(
            listOf(1, 2, 3, 4, 5).map {
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
        )

        similarModels.addAll(
            listOf(1, 2, 3, 4, 5).map {
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
        })
    }

    fun setModelID(modelID: Int) {
        _modelID = modelID
    }

    fun fetchModelDetails() {
        viewModelScope.launch {
            val model = KtorClient.getModel(modelId)
            _model.value = model
        }
    }
}