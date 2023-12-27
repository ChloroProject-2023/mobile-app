package vn.edu.usth.mobile_app.ui.usermenu.resourcelist

import androidx.lifecycle.ViewModel
import vn.edu.usth.mobile_app.model.ResourcesData

class ResourceViewModel: ViewModel() {
    private var _resourceList: ArrayList<ResourcesData> = arrayListOf()
    val resourceList: ArrayList<ResourcesData> get() = _resourceList

    fun fetchResourceList() {
        _resourceList.add(ResourcesData(1,"data1.sed", "SedML", 1))
        _resourceList.add(ResourcesData(2,"data2.sed", "SedML", 1))
        _resourceList.add(ResourcesData(3,"data3.sed", "SedML", 1))
        _resourceList.add(ResourcesData(4,"data4.sed", "SedML", 1))
        _resourceList.add(ResourcesData(5,"data5.sed", "SedML", 1))
        _resourceList.add(ResourcesData(6,"data6.sed", "SedML", 1))
        _resourceList.add(ResourcesData(7,"data7.sed", "SedML", 1))
        _resourceList.add(ResourcesData(8,"data8.sed", "SedML", 1))
        _resourceList.add(ResourcesData(9,"data9.sed", "SedML", 1))
        _resourceList.add(ResourcesData(10,"data10.sed", "SedML", 1))
    }
}