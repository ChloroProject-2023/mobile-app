package vn.edu.usth.mobile_app.ui.modeldetails

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import vn.edu.usth.mobile_app.databinding.ActivityRecyclerViewBinding
import vn.edu.usth.mobile_app.ui.explore.ExploreListRecyclerAdapter

class MoreSimilarModelsActivity: AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerViewBinding
    private val viewModel: MoreSimilarModelsViewModel by viewModels()

    private lateinit var exploreListRecyclerAdapter: ExploreListRecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.materialToolbarRVActivity
        toolbar.setNavigationOnClickListener { finish() }
        toolbar.setTitle("Similar Models")

        viewModel.getSimilarModels()
        exploreListRecyclerAdapter = ExploreListRecyclerAdapter(viewModel.models)
        
        val recyclerView = binding.recyclerViewRVActivity
        recyclerView.adapter = exploreListRecyclerAdapter
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        recyclerView.setItemViewCacheSize(10)
        recyclerView.setHasFixedSize(true)

        val dividerItemDecoration = androidx.recyclerview.widget.DividerItemDecoration(this, androidx.recyclerview.widget.DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }
}