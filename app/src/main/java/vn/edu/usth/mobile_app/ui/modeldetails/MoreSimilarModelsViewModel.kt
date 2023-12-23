package vn.edu.usth.mobile_app.ui.modeldetails

import androidx.lifecycle.ViewModel
import vn.edu.usth.mobile_app.model.ModelData

class MoreSimilarModelsViewModel: ViewModel() {
    private var _models: ArrayList<ModelData> = arrayListOf()

    val models: ArrayList<ModelData> get() = _models

    fun getSimilarModels() {
        _models = listOf(1, 2, 3, 4, 5).map {
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
        } as ArrayList<ModelData>
    }
}