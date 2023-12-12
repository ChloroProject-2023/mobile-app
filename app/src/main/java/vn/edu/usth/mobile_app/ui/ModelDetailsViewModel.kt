package vn.edu.usth.mobile_app.ui

import androidx.lifecycle.ViewModel

class ModelDetailsViewModel: ViewModel() {
    private var _modelName: String = ""
    private var _author: String = ""
    private var _description: String = ""
    private var _ratings: Map<String, Int> = emptyMap()

    val modelName: String get() = _modelName
    val author: String get() = _author
    val description: String get() = _description
    val ratings: Map<String, Int> get() = _ratings

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
    }
}