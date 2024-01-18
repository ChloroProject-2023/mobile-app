package vn.edu.usth.mobile_app.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import vn.edu.usth.mobile_app.model.ModelData
import vn.edu.usth.mobile_app.model.ResourcesData
import vn.edu.usth.mobile_app.model.UserData
import vn.edu.usth.mobile_app.network.KtorClient
import vn.edu.usth.mobile_app.ui.GlobalData

class ProfileViewModel: ViewModel() {
    private val _userData = MutableLiveData<UserData>()
    private val _resourceList = MutableLiveData<ArrayList<ResourcesData>>()
    private val _modelList = MutableLiveData<ArrayList<ModelData>>()
    val resourceList: LiveData<ArrayList<ResourcesData>> get() = _resourceList
    val modelList: LiveData<ArrayList<ModelData>> get() = _modelList
    val userData: LiveData<UserData> get() = _userData

    init {
        getUserData()
        fetchResourceList()
        fetchModelList()
    }

    private fun getUserData() {
        viewModelScope.launch {
            val userData = KtorClient.getProfile(GlobalData.username)
            _userData.value = userData
        }
    }
    private fun fetchResourceList() {
        viewModelScope.launch {
            val resourcesData = KtorClient.getUserResources(GlobalData.userId)
            if (resourcesData.isEmpty()) {
                return@launch
            }
            _resourceList.value = resourcesData as ArrayList<ResourcesData>
        }
    }
    private fun fetchModelList() {
        viewModelScope.launch {
            val modelData = KtorClient.getModelsByUser(GlobalData.userId)
            if (modelData.isEmpty()) {
                return@launch
            }
            _modelList.value = modelData as ArrayList<ModelData>
        }
    }
    fun deleteResource(resourceId: Int): Boolean {
        var result = false
        viewModelScope.launch {
            val response = KtorClient.deleteResource(resourceId)
            Log.d("DELETE", response.toString())
            result = response
        }
        return result
    }
}