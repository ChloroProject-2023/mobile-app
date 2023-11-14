package vn.edu.usth.mobile_app.model

import java.util.Date

class HistoryData(
    private var modelName: String,
    private var date: Date,
    private var uploadedDataPath: String,
    private var result: String
) {
    fun getModelName(): String {
        return modelName
    }
    fun setModelName(modelName: String) {
        this.modelName = modelName
    }

    fun getDate(): Date {
        return date
    }
    fun setDate(timestamp: Long) {
        this.date = Date(timestamp)
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