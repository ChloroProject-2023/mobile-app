package vn.edu.usth.mobile_app.ui.admin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import kotlinx.coroutines.launch

class FragmentAdminAnalyticsViewModel: ViewModel(){
    val requestFreqData = mutableListOf<Pair<Long, Int>>()
    val top5ModelData = mutableMapOf<String, Int>()
    private var _last24hRequest: Int = 0
    private var _last24hUsers: Int = 0
    val requestFreqModelProducer: ChartEntryModelProducer = ChartEntryModelProducer()
    val top5ModelModelProducer: ChartEntryModelProducer = ChartEntryModelProducer()

    val last24hRequest: Int get() = _last24hRequest
    val last24hUsers: Int get() = _last24hUsers

    init {
        viewModelScope.launch {
            // Fetch data from server
            fetchRequestFreqData()
            fetchTop5ModelData()
        }

        // Mock data
        requestFreqData.addAll(listOf(
            Pair(0, 0),
            Pair(1, 1),
            Pair(2, 2),
            Pair(3, 3),
            Pair(4, 4),
            Pair(5, 5),
            Pair(6, 6),
            Pair(7, 7),
            Pair(8, 8),
            Pair(9, 9),)
        )
        top5ModelData.putAll(
            mapOf(
                "Model 1" to 1,
                "Model 2" to 2,
                "Model 3" to 3,
                "Model 4" to 4,
                "Model 5" to 5,
            )
        )
    }

    private fun fetchRequestFreqData() {
    }
    private fun fetchTop5ModelData() {
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("AdminAnalyticsViewModel", "ViewModel destroyed")
    }

}