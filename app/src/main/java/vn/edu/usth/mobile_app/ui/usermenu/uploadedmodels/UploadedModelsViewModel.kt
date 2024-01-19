package vn.edu.usth.mobile_app.ui.usermenu.uploadedmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import vn.edu.usth.mobile_app.model.ModelData
import vn.edu.usth.mobile_app.network.KtorClient
import vn.edu.usth.mobile_app.ui.GlobalData

class UploadedModelsViewModel: ViewModel() {
    private val _uploadedModelsList = MutableLiveData<ArrayList<ModelData>>()
    val uploadedModelsList: LiveData<ArrayList<ModelData>> get() = _uploadedModelsList

    init {
        fetchUploadedModels()
    }

    fun fetchUploadedModels() {
        viewModelScope.launch {
            val uploadedModels = KtorClient.getModelsByUser(GlobalData.userId)
            if (uploadedModels.isEmpty()) {
                return@launch
            }
            _uploadedModelsList.value = uploadedModels as ArrayList<ModelData>
        }
    }

    private fun deleteModel(modelId: Int) {
        viewModelScope.launch {
            val response = KtorClient.deleteModel(modelId)
            Log.d("DELETE", response.toString())
        }
    }
}