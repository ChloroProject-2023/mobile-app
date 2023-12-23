package vn.edu.usth.mobile_app.ui.modeldetails

import androidx.lifecycle.ViewModel

class AboutModelViewModel: ViewModel() {
    private var _modelId: Int = 0
    private var _description: String = ""
    private var _modelName: String = ""
    private var _inferences: Int = 0
    private var _parameters: Int = 0
    private var _createdAt: Long = 1
    private var _totalRatings: Int = 0

    val modelId: Int get() = _modelId
    val description: String get() = _description
    val modelName: String get() = _modelName
    val inferences: Int get() = _inferences
    val parameters: Int get() = _parameters
    val createdAt: Long get() = _createdAt
    val totalRatings: Int get() = _totalRatings

    fun setModelId(modelId: Int) {
        _modelId = modelId
    }

    fun fetchModelDetails() {

    }
}