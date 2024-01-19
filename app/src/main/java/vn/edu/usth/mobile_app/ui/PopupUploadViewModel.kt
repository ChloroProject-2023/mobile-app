package vn.edu.usth.mobile_app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import vn.edu.usth.mobile_app.network.KtorClient
import java.io.File

class PopupUploadViewModel: ViewModel() {
    lateinit var modelName: String
    lateinit var description: String
    lateinit var file: File
    lateinit var modelType: String
    var fileType = "json"

    fun uploadModel(
    ) {
        viewModelScope.launch {
            KtorClient.uploadModel(
                GlobalData.userId, modelName, description, modelType, file)
        }
    }
}