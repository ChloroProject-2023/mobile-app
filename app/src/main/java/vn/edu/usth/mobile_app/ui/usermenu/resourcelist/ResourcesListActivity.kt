package vn.edu.usth.mobile_app.ui.usermenu.resourcelist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import vn.edu.usth.mobile_app.databinding.ActivityRecyclerViewBinding

class ResourcesListActivity: AppCompatActivity() {
    private lateinit var resourceAsyncRecyclerAdapter: ResourceAsyncRecyclerAdapter
    private lateinit var binding: ActivityRecyclerViewBinding
    private val viewModel: ResourceViewModel = ResourceViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.materialToolbarRVActivity
        toolbar.setNavigationOnClickListener{
            finish()
        }
        toolbar.setTitle("Resources")

        viewModel.fetchResourceList()
        val resourceList = viewModel.resourceList
        resourceAsyncRecyclerAdapter = ResourceAsyncRecyclerAdapter(resourceList)
        val recyclerView = binding.recyclerViewRVActivity
        recyclerView.adapter = resourceAsyncRecyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }
}