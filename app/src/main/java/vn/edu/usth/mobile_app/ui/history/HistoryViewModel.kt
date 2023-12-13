package vn.edu.usth.mobile_app.ui.history

import androidx.lifecycle.ViewModel
import vn.edu.usth.mobile_app.model.HistoryData
import java.util.Date

class HistoryViewModel: ViewModel() {
    private var _historyList: ArrayList<HistoryData> = ArrayList()
    val historyList: ArrayList<HistoryData> get() = _historyList

    init {
        historyList.add(HistoryData("Model 1", Date(1), "data1.sed", "Result 1"))
        historyList.add(HistoryData("Model 2", Date(1), "data2.sed", "Result 2"))
        historyList.add(HistoryData("Model 3", Date(1), "data3.sed", "Result 3"))
        historyList.add(HistoryData("Model 4", Date(1), "data4.sed", "Result 4"))
        historyList.add(HistoryData("Model 5", Date(1), "data5.sed", "Result 5"))
        historyList.add(HistoryData("Model 6", Date(1), "data6.sed", "Result 6"))
        historyList.add(HistoryData("Model 7", Date(1), "data7.sed", "Result 7"))
        historyList.add(HistoryData("Model 8", Date(1), "data8.sed", "Result 8"))
        historyList.add(HistoryData("Model 9", Date(1), "data9.sed", "Result 9"))
        historyList.add(HistoryData("Model 10", Date(1), "data10.sed", "Result 10"))
    }
}