package vn.edu.usth.mobile_app.ui.modeldetails.reviews

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import vn.edu.usth.mobile_app.databinding.ActivityRecyclerViewBinding

class ReviewActivity: AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerViewBinding
    private val viewModel: ReviewViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.extras != null) {
            viewModel.modelId = intent.extras!!.getInt("modelID")
        }
        viewModel.fetchReviews()

        val toolBar = binding.materialToolbarRVActivity
        toolBar.setNavigationOnClickListener { finish() }
        toolBar.setTitle("Reviews")

        val recyclerView = binding.recyclerViewRVActivity
        recyclerView.adapter = ReviewAsyncAdapter(viewModel.reviews)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}