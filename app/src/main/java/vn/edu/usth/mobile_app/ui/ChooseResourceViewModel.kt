package vn.edu.usth.mobile_app.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import vn.edu.usth.mobile_app.model.ResourcesData

class ChooseResourceViewModel: ViewModel() {
    private var _resourceList = MutableLiveData<ArrayList<ResourcesData>>()
    var modelId = -1
    val resourceList: LiveData<ArrayList<ResourcesData>> get() = _resourceList

    init {
        fetchResourceList()
    }

    private fun fetchResourceList() {
        _resourceList.value = arrayListOf(
            ResourcesData(1,"data1.sed", "SedML", 1),
            ResourcesData(2,"data2.sed", "SedML", 1),
            ResourcesData(3,"data3.sed", "SedML", 1),
            ResourcesData(4,"data4.sed", "SedML", 1),
            ResourcesData(5,"data5.sed", "SedML", 1),
            ResourcesData(6,"data6.sed", "SedML", 1),
            ResourcesData(7,"data7.sed", "SedML", 1),
            ResourcesData(8,"data8.sed", "SedML", 1),
            ResourcesData(9,"data9.sed", "SedML", 1),
            ResourcesData(10,"data10.sed", "SedML", 1)
        )
    }

    private fun makeInference(resourceId: Int) {

    }
}