package vn.edu.usth.mobile_app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
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

        val similarModels = binding.linearLayoutModelDetailsSimilarModels
        similarModels.setOnClickListener() {
            // Open similar models
        }

        val mostUsedModels = binding.linearLayoutModelDetailsMostUsedModels
        mostUsedModels.setOnClickListener() {
            // Open most used models
        }
    }
}