package vn.edu.usth.mobile_app.ui.usermenu.uploadedmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import vn.edu.usth.mobile_app.model.ModelData

class UploadedModelsViewModel: ViewModel() {
    private val _uploadedModelsList = MutableLiveData<ArrayList<ModelData>>()
    val uploadedModelsList: LiveData<ArrayList<ModelData>> get() = _uploadedModelsList

    init {
        viewModelScope.launch {
            delay(3000)
            loadUploadedModels()
            delay(1000)
            addMore()
            Log.d("UploadedModelsViewModel", "addMore() called")
            Log.d("UploadedModelsViewModel", uploadedModelsList.value?.size.toString())
        }
    }

    private fun loadUploadedModels() {
        _uploadedModelsList.value =
            arrayListOf(
                ModelData(1,"Model 1", "Author 1", "C:\\", "Linear Regression", "Author 1", 1, 1000),
                ModelData(2,"Model 2", "Author 2", "C:\\", "Linear Regression", "Author 2", 1, 1000),
                ModelData(3,"Model 3", "Author 3", "C:\\", "Linear Regression", "Author 3", 1, 1000),
                ModelData(4,"Model 4", "Author 4", "C:\\", "Linear Regression", "Author 4", 1, 1000),
            )
    }

    private fun addMore() {
        val newList = ArrayList<ModelData>(uploadedModelsList.value!!)
        newList.addAll(
            arrayListOf(
                ModelData(5,"Model 5", "Author 5", "C:\\", "Linear Regression", "Author 5", 1, 1000),
                ModelData(6,"Model 6", "Author 6", "C:\\", "Linear Regression", "Author 6", 1, 1000),
                ModelData(7,"Model 7", "Author 7", "C:\\", "Linear Regression", "Author 7", 1, 1000),
                ModelData(8,"Model 8", "Author 8", "C:\\", "Linear Regression", "Author 8", 1, 1000),
                ModelData(9,"Model 9", "Author 9", "C:\\", "Linear Regression", "Author 9", 1, 1000),
                ModelData(10,"Model 10", "Author 10", "C:\\", "Linear Regression", "Author 10", 1, 1000)
            )
        )
        _uploadedModelsList.value = newList
    }
}