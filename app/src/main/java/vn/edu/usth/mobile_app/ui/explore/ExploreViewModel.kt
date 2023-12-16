package vn.edu.usth.mobile_app.ui.explore

import androidx.lifecycle.ViewModel
import vn.edu.usth.mobile_app.model.ModelData

class ExploreViewModel: ViewModel() {
    private var _modelList: ArrayList<ModelData> = ArrayList()
    val modelList: ArrayList<ModelData> get() = _modelList
    init {
        _modelList.add(ModelData(1,"Model 1", "Author 1", "C:\\", "Linear Regression", "Author 1", 1, 1000))
        _modelList.add(ModelData(2,"Model 2", "Author 2", "C:\\", "Linear Regression", "Author 2", 1, 1000))
        _modelList.add(ModelData(3,"Model 3", "Author 3", "C:\\", "Linear Regression", "Author 3", 1, 1000))
        _modelList.add(ModelData(4,"Model 4", "Author 4", "C:\\", "Linear Regression", "Author 4", 1, 1000))
        _modelList.add(ModelData(5,"Model 5", "Author 5", "C:\\", "Linear Regression", "Author 5", 1, 1000))
        _modelList.add(ModelData(6,"Model 6", "Author 6", "C:\\", "Linear Regression", "Author 6", 1, 1000))
        _modelList.add(ModelData(7,"Model 7", "Author 7", "C:\\", "Linear Regression", "Author 7", 1, 1000))
        _modelList.add(ModelData(8,"Model 8", "Author 8", "C:\\", "Linear Regression", "Author 8", 1, 1000))
        _modelList.add(ModelData(9,"Model 9", "Author 9", "C:\\", "Linear Regression", "Author 9", 1, 1000))
    }
}