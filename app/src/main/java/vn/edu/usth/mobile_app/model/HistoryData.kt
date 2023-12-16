package vn.edu.usth.mobile_app.model

data class HistoryData(
    var id: Int = -1,
    var modelName: String = "",
    var date: Long = 1,
    var uploadedDataPath: String = "",
    var result: String = ""
)