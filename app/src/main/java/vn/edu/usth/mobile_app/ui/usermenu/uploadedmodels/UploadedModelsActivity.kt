package vn.edu.usth.mobile_app.ui.usermenu.uploadedmodels

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import vn.edu.usth.mobile_app.databinding.ActivityRecyclerViewBinding
import vn.edu.usth.mobile_app.model.ModelData

class UploadedModelsActivity: AppCompatActivity() {
    private lateinit var _binding: ActivityRecyclerViewBinding
    private  val viewModel: UploadedModelsViewModel by viewModels()
    private val binding get() = _binding
    private lateinit var uploadedModelsAsyncRecyclerAdapter: UploadedModelsAsyncAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.materialToolbarRVActivity
        toolbar.setNavigationOnClickListener{
            finish()
        }
        toolbar.setTitle("Uploaded Models")

        uploadedModelsAsyncRecyclerAdapter = UploadedModelsAsyncAdapter()
        val listObserver = Observer<ArrayList<ModelData>> {
            uploadedModelsAsyncRecyclerAdapter.updateList(it)
        }
        viewModel.uploadedModelsList.observe(this, listObserver)

        val recyclerView = binding.recyclerViewRVActivity
        recyclerView.adapter = uploadedModelsAsyncRecyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }
}