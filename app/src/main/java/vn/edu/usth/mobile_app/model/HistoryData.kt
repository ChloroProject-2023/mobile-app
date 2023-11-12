package vn.edu.usth.mobile_app.model

import java.text.SimpleDateFormat
import java.util.Date

class HistoryData(
    private var modelName: String,
    private var date: String,
    private var uploadedDataPath: String,
    private var result: String
) {
    fun getModelName(): String {
        return modelName
    }
    fun setModelName(modelName: String) {
        this.modelName = modelName
    }

    fun getDate(): String {
        return date
    }
    fun setDate(timestamp: Long) {
        val time = Date(timestamp)
        val format = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        this.date = format.format(time)
    }

    fun getUploadedData(): String {
        return uploadedDataPath
    }
    fun setUploadedData(uploadedData: String) {
        this.uploadedDataPath = uploadedData
    }

    fun getResult(): String {
        return result
    }
    fun setResult(result: String) {
        this.result = result
    }
}