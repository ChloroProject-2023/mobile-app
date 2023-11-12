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

class HistoryFragment : Fragment() {
    private lateinit var expandableCardRecyclerAdapter: ExpandableCardRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        val historyList = ArrayList<HistoryData>()
        historyList.add(HistoryData("Model 1", "01/01/2021 00:00:00", "data1.sed", "Result 1"))
        historyList.add(HistoryData("Model 2", "02/01/2021 00:00:00", "data2.sed", "Result 2"))
        historyList.add(HistoryData("Model 3", "03/01/2021 00:00:00", "data3.sed", "Result 3"))
        historyList.add(HistoryData("Model 4", "04/01/2021 00:00:00", "data4.sed", "Result 4"))
        historyList.add(HistoryData("Model 5", "05/01/2021 00:00:00", "data5.sed", "Result 5"))
        historyList.add(HistoryData("Model 6", "06/01/2021 00:00:00", "data6.sed", "Result 6"))
        historyList.add(HistoryData("Model 7", "07/01/2021 00:00:00", "data7.sed", "Result 7"))
        historyList.add(HistoryData("Model 8", "08/01/2021 00:00:00", "data8.sed", "Result 8"))
        historyList.add(HistoryData("Model 9", "09/01/2021 00:00:00", "data9.sed", "Result 9"))
        historyList.add(HistoryData("Model 10", "10/01/2021 00:00:00", "data10.sed", "Result 10"))
        expandableCardRecyclerAdapter = ExpandableCardRecyclerAdapter(historyList)
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