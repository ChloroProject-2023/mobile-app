package vn.edu.usth.mobile_app.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import vn.edu.usth.mobile_app.databinding.ActivityRecyclerViewBinding
import vn.edu.usth.mobile_app.model.ResourcesData

class ChooseResourceActivity: AppCompatActivity() {
    private lateinit var chooseResourceAsyncAdapter: ChooseResourceAsyncAdapter
    private lateinit var binding: ActivityRecyclerViewBinding
    private val viewModel: ChooseResourceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.hasExtra("modelID")) {
            viewModel.modelId = intent.getIntExtra("modelID", -1)
        }

        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.materialToolbarRVActivity
        toolbar.setNavigationOnClickListener{
            finish()
        }
        toolbar.setTitle("Choose Resource")

        chooseResourceAsyncAdapter = ChooseResourceAsyncAdapter(viewModel)
        val listObserver = androidx.lifecycle.Observer<ArrayList<ResourcesData>> {
            chooseResourceAsyncAdapter.updateList(it)
        }
        viewModel.resourceList.observe(this, listObserver)

        val recyclerView = binding.recyclerViewRVActivity
        recyclerView.adapter = chooseResourceAsyncAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }
}