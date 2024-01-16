package vn.edu.usth.mobile_app.ui.usermenu.resourcelist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import vn.edu.usth.mobile_app.model.ResourcesData
import vn.edu.usth.mobile_app.network.KtorClient
import vn.edu.usth.mobile_app.ui.GlobalData
import java.io.File

class ResourceViewModel: ViewModel() {
    private var _resourceList = MutableLiveData<ArrayList<ResourcesData>>()
    val resourceList: LiveData<ArrayList<ResourcesData>> get() = _resourceList

    init {
        fetchResourceList()
    }

    private fun fetchResourceList() {
        viewModelScope.launch {
            val resourcesData = KtorClient.getUserResources(GlobalData.userId)
            if (resourcesData.isEmpty()) {
                return@launch
            }
            _resourceList.value = resourcesData as ArrayList<ResourcesData>
        }
//        _resourceList.value = arrayListOf(
//            ResourcesData(1,"data1.sed", "SedML", 1),
//            ResourcesData(2,"data2.sed", "SedML", 1),
//            ResourcesData(3,"data3.sed", "SedML", 1),
//            ResourcesData(4,"data4.sed", "SedML", 1),
//            ResourcesData(5,"data5.sed", "SedML", 1),
//            ResourcesData(6,"data6.sed", "SedML", 1),
//            ResourcesData(7,"data7.sed", "SedML", 1),
//            ResourcesData(8,"data8.sed", "SedML", 1),
//            ResourcesData(9,"data9.sed", "SedML", 1),
//            ResourcesData(10,"data10.sed", "SedML", 1)
//        )
    }

    fun uploadResource(file: File) {
        viewModelScope.launch {
            val response = KtorClient.uploadResource(GlobalData.userId, file)
            Log.d("UPLOAD", response)
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