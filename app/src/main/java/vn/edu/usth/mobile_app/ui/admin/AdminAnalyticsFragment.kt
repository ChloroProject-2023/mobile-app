package vn.edu.usth.mobile_app.ui.admin

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.asynclayoutinflater.view.AsyncLayoutInflater
import androidx.fragment.app.Fragment
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import vn.edu.usth.mobile_app.R
import vn.edu.usth.mobile_app.databinding.FragmentAdminAnalyticsBinding
import androidx.fragment.app.viewModels
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartView

class AdminAnalyticsFragment : Fragment(R.layout.fragment_admin_analytics) {
    private var _binding: FragmentAdminAnalyticsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FragmentAdminAnalyticsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminAnalyticsBinding.inflate(inflater, container, false)
        val view = binding.root

        val requestFreqFrameLayout = binding.frameLayoutAdminAnalyticsRequestFrequency
        val top5ModelsFrameLayout = binding.frameLayoutAdminAnalyticsTop5Models
        val requestFreqProcessBar = binding.progressBarAdminAnalyticsRequestFrequency
        val top5ModelsProcessBar = binding.progressBarAdminAnalyticsTop5Models

        val requestFreqAsyncInflater = AsyncLayoutInflater(requireContext())
        requestFreqAsyncInflater.inflate(R.layout.fragment_chart, requestFreqFrameLayout) { view1, _, _ ->
            drawRequestFreqChart(view1)
            requestFreqProcessBar.visibility = View.GONE
            requestFreqFrameLayout.addView(view1)
        }

        val top5ModelsAsyncInflater = AsyncLayoutInflater(requireContext())
        top5ModelsAsyncInflater.inflate(R.layout.fragment_chart, top5ModelsFrameLayout) { view1, _, _ ->
            drawTop5ModelsChart(view1)
            top5ModelsProcessBar.visibility = View.GONE
            top5ModelsFrameLayout.addView(view1)
        }

        binding.textViewAdminAnalyticsLast24hRequestValue.text = viewModel.last24hRequest.toString()
        binding.textViewAdminAnalyticsLast24hUsersValue.text = viewModel.last24hUsers.toString()

        return view
    }

    private fun drawRequestFreqChart(view: View){
        val requestFreqAAChart = view.findViewById<AAChartView>(R.id.aaChartView_chartFragment)

        val value = TypedValue()
        context?.theme?.resolveAttribute(android.R.attr.colorPrimary, value, true)
        val color = value.data
        val primaryColor = String.format("#%06X", 0xFFFFFF and color)

        val requestFreqChartModel = AAChartModel()
            .chartType(AAChartType.Areaspline)
            .legendEnabled(false)
            .backgroundColor("#00000000")  // Transparent
            .colorsTheme(arrayOf(primaryColor))
            .yAxisTitle("Requests")
            .series(arrayOf(
                AASeriesElement()
                    .name("Requests")
                    .data(viewModel.requestFreqData)
            )
            )
        requestFreqAAChart.aa_drawChartWithChartModel(requestFreqChartModel)
    }

    private fun drawTop5ModelsChart(view: View){
        val top5ModelsAAChart = view.findViewById<AAChartView>(R.id.aaChartView_chartFragment)

        val value = TypedValue()
        context?.theme?.resolveAttribute(android.R.attr.colorPrimary, value, true)
        val color = value.data
        val primaryColor = String.format("#%06X", 0xFFFFFF and color)

        val top5ModelsChartModel = AAChartModel()
            .chartType(AAChartType.Bar)
            .legendEnabled(false)
            .backgroundColor("#00000000")  // Transparent
            .colorsTheme(arrayOf(primaryColor))
            .categories(viewModel.top5ModelData.keys.toTypedArray())
            .yAxisTitle("Requests")
            .series(arrayOf(
                AASeriesElement()
                    .data(viewModel.top5ModelData.values.toTypedArray())
            ))
        top5ModelsAAChart.aa_drawChartWithChartModel(top5ModelsChartModel)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}