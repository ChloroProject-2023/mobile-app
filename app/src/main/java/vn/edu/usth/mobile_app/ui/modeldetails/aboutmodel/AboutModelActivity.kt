package vn.edu.usth.mobile_app.ui.modeldetails.aboutmodel

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import vn.edu.usth.mobile_app.databinding.ActivityAboutModelBinding

class AboutModelActivity: AppCompatActivity() {
    private lateinit var binding: ActivityAboutModelBinding
    private val viewModel: AboutModelViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutModelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.materialToolbarAboutModel
        toolbar.setNavigationOnClickListener{
            finish()
        }

        if (intent.hasExtra("modelId")) {
            viewModel.setModelId(intent.getIntExtra("modelId", -1))
        }
        viewModel.fetchModelDetails()

        val description = binding.textViewAboutModelDescription
        val inferences = binding.textViewAboutModelInferences
        val parameters = binding.textViewAboutModelParameters
        val createdAt = binding.textViewAboutModelCreatedAt
        val totalRatings = binding.textViewAboutModelTotalRatings

        description.text = viewModel.description
        inferences.text = viewModel.inferences.toString()
        parameters.text = viewModel.parameters.toString()
        val date = java.util.Date(viewModel.createdAt)
        createdAt.text = date.toString()
        totalRatings.text = viewModel.totalRatings.toString()
    }
}