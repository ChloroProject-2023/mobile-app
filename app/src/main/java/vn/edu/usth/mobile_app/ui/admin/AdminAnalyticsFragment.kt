package vn.edu.usth.mobile_app.ui.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import vn.edu.usth.mobile_app.R
import vn.edu.usth.mobile_app.databinding.FragmentAdminAnalyticsBinding
import androidx.fragment.app.viewModels
import com.patrykandpatrick.vico.core.axis.Axis
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.entryOf

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

        drawRequestFreqChart()
        drawTop5ModelsChart()

        binding.textViewAdminAnalyticsLast24hRequestValue.text = viewModel.last24hRequest.toString()
        binding.textViewAdminAnalyticsLast24hUsersValue.text = viewModel.last24hUsers.toString()

        return view
    }

    private fun drawRequestFreqChart(){
        val chart = binding.chartViewAdminAnalyticsRequestFrequency
        val entryList = viewModel.requestFreqData.map {entryOf(it.first ,it.second)}
        chart.entryProducer = ChartEntryModelProducer(entryList)
    }

    private fun drawTop5ModelsChart(){
        val chart = binding.chartViewAdminAnalyticsTop5Models

        // Have to convert x label from String (receive) to Int (to put into chart)
        // then to String (format axis label)
        val xAxisValueFormatter = AxisValueFormatter<AxisPosition.Horizontal.Bottom> { value, _ ->
            viewModel.top5ModelData.keys.elementAt(value.toInt())
        }
        val xAxis = chart.bottomAxis as Axis
        xAxis.valueFormatter = xAxisValueFormatter
        val data = viewModel.top5ModelData.map { (key, value) ->
            entryOf(
                viewModel.top5ModelData.keys.indexOf(key),
                value
            )
        }
        chart.entryProducer = ChartEntryModelProducer(data)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}