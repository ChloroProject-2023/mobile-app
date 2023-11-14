package vn.edu.usth.mobile_app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vn.edu.usth.mobile_app.model.HistoryData
import vn.edu.usth.mobile_app.R
import java.util.Date

class HistoryFragment : Fragment() {
    private lateinit var expandableCardRecyclerAdapter: HistoryListRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        val historyList = ArrayList<HistoryData>()
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
        expandableCardRecyclerAdapter = HistoryListRecyclerAdapter(historyList)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recycler_view, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView_recyclerView)
        recyclerView.adapter = expandableCardRecyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        return view
    }
}