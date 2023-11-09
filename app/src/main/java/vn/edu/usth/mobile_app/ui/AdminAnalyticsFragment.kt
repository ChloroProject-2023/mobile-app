package vn.edu.usth.mobile_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartView
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import com.github.aachartmodel.aainfographics.aaoptionsmodel.AASeries
import vn.edu.usth.mobile_app.R

class AdminAnalyticsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_admin_analytics, container, false)
        val requestFreqChart = view.findViewById<AAChartView>(R.id.requestFrequencyChart)
        val requestFreqChartModel = AAChartModel()
            .chartType(AAChartType.Areaspline)
            .legendEnabled(false)
            .yAxisTitle("Requests")
            .series(arrayOf(
                    AASeriesElement()
                        .name("Requests")
                        .data(arrayOf(1, 2, 3, 4, 5))
                )
            )
        requestFreqChart.aa_drawChartWithChartModel(requestFreqChartModel)

        val top5ModelData = mapOf(
            "Model 1" to 1,
            "Model 2" to 2,
            "Model 3" to 3,
            "Model 4" to 4,
            "Model 5" to 5,
        )

        val top5ModelsChart = view.findViewById<AAChartView>(R.id.top5ModelsChart)
        val top5ModelsChartModel = AAChartModel()
            .chartType(AAChartType.Bar)
            .legendEnabled(false)
            .categories(top5ModelData.keys.toTypedArray())
            .yAxisTitle("Requests")
            .series(arrayOf(
                AASeriesElement()
                    .data(top5ModelData.values.toTypedArray())
            ))
        top5ModelsChart.aa_drawChartWithChartModel(top5ModelsChartModel)

        return view
    }
}