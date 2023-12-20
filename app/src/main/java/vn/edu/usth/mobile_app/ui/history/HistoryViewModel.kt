package vn.edu.usth.mobile_app.ui.history

import androidx.lifecycle.ViewModel
import vn.edu.usth.mobile_app.model.HistoryData

class HistoryViewModel: ViewModel() {
    private var _historyList: ArrayList<HistoryData> = ArrayList()
    val historyList: ArrayList<HistoryData> get() = _historyList

    init {
        historyList.add(HistoryData(1,"Model 1", 1, "data1.sed", "Result 1"))
        historyList.add(HistoryData(2,"Model 2", 1, "data2.sed", "Result 2"))
        historyList.add(HistoryData(3,"Model 3", 1, "data3.sed", "Result 3"))
        historyList.add(HistoryData(4,"Model 4", 1, "data4.sed", "Result 4"))
        historyList.add(HistoryData(5,"Model 5", 1, "data5.sed", "Result 5"))
        historyList.add(HistoryData(6,"Model 6", 1, "data6.sed", "Result 6"))
        historyList.add(HistoryData(7,"Model 7", 1, "data7.sed", "Result 7"))
        historyList.add(HistoryData(8,"Model 8", 1, "data8.sed", "Result 8"))
        historyList.add(HistoryData(9,"Model 9", 1, "data9.sed", "Result 9"))
        historyList.add(HistoryData(10,"Model 10", 1, "data10.sed", "Result 10"))
    }
}