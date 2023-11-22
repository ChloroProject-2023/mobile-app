package vn.edu.usth.mobile_app.ui.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import vn.edu.usth.mobile_app.R
import vn.edu.usth.mobile_app.databinding.FragmentAdminAnalyticsBinding
import androidx.fragment.app.viewModels

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

        val requestFreqChart = binding.chartViewAdminAnalyticsRequestFrequency
        val requestFreqChartModel = AAChartModel()
            .chartType(AAChartType.Areaspline)
            .legendEnabled(false)
            .yAxisTitle("Requests")
            .series(arrayOf(
                    AASeriesElement()
                        .name("Requests")
                        .data(viewModel.requestFreqData)
                )
            )
        requestFreqChart.aa_drawChartWithChartModel(requestFreqChartModel)

        val top5ModelsChart = binding.chartViewAdminAnalyticsTop5Models
        val top5ModelsChartModel = AAChartModel()
            .chartType(AAChartType.Bar)
            .legendEnabled(false)
            .categories(viewModel.top5ModelData.keys.toTypedArray())
            .yAxisTitle("Requests")
            .series(arrayOf(
                AASeriesElement()
                    .data(viewModel.top5ModelData.values.toTypedArray())
            ))
        top5ModelsChart.aa_drawChartWithChartModel(top5ModelsChartModel)

        binding.textViewAdminAnalyticsLast24hRequestValue.text = viewModel.last24hRequest.toString()
        binding.textViewAdminAnalyticsLast24hUsersValue.text = viewModel.last24hUsers.toString()

        return view
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}