package vn.edu.usth.mobile_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import vn.edu.usth.mobile_app.R
import vn.edu.usth.mobile_app.model.ModelData
import java.util.Date

class ExploreFragment : Fragment() {
    private lateinit var exploreListRecyclerAdapter: ExploreListRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val modelList = ArrayList<ModelData>()
        modelList.add(ModelData("Model 1", "Author 1", "C:\\", "Linear Regression", "Author 1", Date(1), 1000))
        modelList.add(ModelData("Model 2", "Author 2", "C:\\", "Linear Regression", "Author 2", Date(1), 1000))
        modelList.add(ModelData("Model 3", "Author 3", "C:\\", "Linear Regression", "Author 3", Date(1), 1000))
        modelList.add(ModelData("Model 4", "Author 4", "C:\\", "Linear Regression", "Author 4", Date(1), 1000))
        modelList.add(ModelData("Model 5", "Author 5", "C:\\", "Linear Regression", "Author 5", Date(1), 1000))
        modelList.add(ModelData("Model 6", "Author 6", "C:\\", "Linear Regression", "Author 6", Date(1), 1000))
        modelList.add(ModelData("Model 7", "Author 7", "C:\\", "Linear Regression", "Author 7", Date(1), 1000))
        modelList.add(ModelData("Model 8", "Author 8", "C:\\", "Linear Regression", "Author 8", Date(1), 1000))
        modelList.add(ModelData("Model 9", "Author 9", "C:\\", "Linear Regression", "Author 9", Date(1), 1000))
        exploreListRecyclerAdapter = ExploreListRecyclerAdapter(modelList)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_recycler_view, container, false)
        val recyclerView = view.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recyclerView_recyclerView)
        recyclerView.adapter = exploreListRecyclerAdapter
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        recyclerView.setItemViewCacheSize(10)

        val dividerItemDecoration = androidx.recyclerview.widget.DividerItemDecoration(context, androidx.recyclerview.widget.DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)

        return view
    }
}