package vn.edu.usth.mobile_app.ui.explore

import androidx.lifecycle.ViewModel
import vn.edu.usth.mobile_app.model.ModelData
import java.util.Date

class ExploreViewModel: ViewModel() {
    private var _modelList: ArrayList<ModelData> = ArrayList()
    val modelList: ArrayList<ModelData> get() = _modelList
    init {
        _modelList.add(ModelData("Model 1", "Author 1", "C:\\", "Linear Regression", "Author 1", Date(1), 1000))
        _modelList.add(ModelData("Model 2", "Author 2", "C:\\", "Linear Regression", "Author 2", Date(1), 1000))
        _modelList.add(ModelData("Model 3", "Author 3", "C:\\", "Linear Regression", "Author 3", Date(1), 1000))
        _modelList.add(ModelData("Model 4", "Author 4", "C:\\", "Linear Regression", "Author 4", Date(1), 1000))
        _modelList.add(ModelData("Model 5", "Author 5", "C:\\", "Linear Regression", "Author 5", Date(1), 1000))
        _modelList.add(ModelData("Model 6", "Author 6", "C:\\", "Linear Regression", "Author 6", Date(1), 1000))
        _modelList.add(ModelData("Model 7", "Author 7", "C:\\", "Linear Regression", "Author 7", Date(1), 1000))
        _modelList.add(ModelData("Model 8", "Author 8", "C:\\", "Linear Regression", "Author 8", Date(1), 1000))
        _modelList.add(ModelData("Model 9", "Author 9", "C:\\", "Linear Regression", "Author 9", Date(1), 1000))
    }
}