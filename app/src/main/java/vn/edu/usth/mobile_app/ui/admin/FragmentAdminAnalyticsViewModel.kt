package vn.edu.usth.mobile_app.ui.admin

import android.util.Log
import androidx.lifecycle.ViewModel

class FragmentAdminAnalyticsViewModel: ViewModel(){
    private var _requestFreqData: Array<Any> = emptyArray()
    private var _top5ModelData: Map<String, Int> = emptyMap()
    private var _last24hRequest: Int = 0
    private var _last24hUsers: Int = 0

    val requestFreqData: Array<Any> get() = _requestFreqData
    val top5ModelData: Map<String, Int> get() = _top5ModelData
    val last24hRequest: Int get() = _last24hRequest
    val last24hUsers: Int get() = _last24hUsers

    init {
        Log.d("AdminAnalyticsViewModel", "ViewModel created")
        _requestFreqData = arrayOf(1, 2, 3, 4, 5)
        _top5ModelData = mapOf(
            "Model 1" to 1,
            "Model 2" to 2,
            "Model 3" to 3,
            "Model 4" to 4,
            "Model 5" to 5,)
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("AdminAnalyticsViewModel", "ViewModel destroyed")
    }

}