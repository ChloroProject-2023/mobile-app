package vn.edu.usth.mobile_app.ui.modeldetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import vn.edu.usth.mobile_app.databinding.ActivityModelDetailsBinding

class ModelDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityModelDetailsBinding
    private val viewModel: ModelDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityModelDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.extras != null) {
            viewModel.setModelID(intent.extras!!.getInt("modelID"))
        }
        viewModel.fetchModelDetails()

        val toolBar = binding.materialToolbarModelDetails
        toolBar.setNavigationOnClickListener { finish() }

        val modelName = binding.textViewModelDetailsTitle
        modelName.text = viewModel.modelName

        val authorName = binding.textViewModelDetailsAuthor
        authorName.text = viewModel.author
        authorName.setOnClickListener() {
            // Open user profile
        }

        val useButton = binding.buttonModelDetailsUse
        useButton.setOnClickListener() {
            // Use model
        }

        val aboutModel = binding.linearLayoutModelDetailsAboutThisModel
        aboutModel.setOnClickListener() {
            // Open about this model
        }

        val description = binding.textViewModelDetailsShortDesc
        description.text = viewModel.description

        val ratings = binding.chartViewModelDetailsRatings
        val ratingsModel = AAChartModel()
            .chartType(AAChartType.Bar)
            .legendEnabled(false)
            .categories(viewModel.ratings.keys.toTypedArray())
            .yAxisTitle("Ratings")
            .series(arrayOf(
                AASeriesElement()
                    .data(viewModel.ratings.values.toTypedArray())
            ))
        ratings.aa_drawChartWithChartModel(ratingsModel)


        val comments = binding.linearLayoutModelDetailsRatingsAndComments
        comments.setOnClickListener() {
            // Open comments
        }

        val dividerItemDecoration = androidx.recyclerview.widget.DividerItemDecoration(this, androidx.recyclerview.widget.DividerItemDecoration.VERTICAL)
        val similarModelsList = binding.recyclerViewModelDetailsSimilarModels
        similarModelsList.adapter = ModelRcmListAdapter(viewModel.similarModels)
        similarModelsList.layoutManager = LinearLayoutManager(this)
        similarModelsList.addItemDecoration(dividerItemDecoration)
        similarModelsList.setHasFixedSize(true)

        val mostUsedModelsList = binding.recyclerViewModelDetailsMostUsedModels
        mostUsedModelsList.adapter = ModelRcmListAdapter(viewModel.mostUsedModels)
        mostUsedModelsList.layoutManager = LinearLayoutManager(this)
        mostUsedModelsList.addItemDecoration(dividerItemDecoration)
        mostUsedModelsList.setHasFixedSize(true)

        val moreSimilarModels = binding.linearLayoutModelDetailsSimilarModels
        moreSimilarModels.setOnClickListener() {
            // Open similar models
        }

        val moreMostUsedModels = binding.linearLayoutModelDetailsMostUsedModels
        moreMostUsedModels.setOnClickListener() {
            // Open most used models
        }
    }
}