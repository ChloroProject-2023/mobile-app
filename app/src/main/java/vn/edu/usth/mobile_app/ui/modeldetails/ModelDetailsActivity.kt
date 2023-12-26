package vn.edu.usth.mobile_app.ui.modeldetails

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.asynclayoutinflater.view.AsyncLayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartView
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import vn.edu.usth.mobile_app.R
import vn.edu.usth.mobile_app.databinding.ActivityModelDetailsBinding
import vn.edu.usth.mobile_app.ui.modeldetails.aboutmodel.AboutModelActivity
import vn.edu.usth.mobile_app.ui.modeldetails.popularmodels.MorePopularModelsActivity
import vn.edu.usth.mobile_app.ui.modeldetails.reviews.ReviewActivity
import vn.edu.usth.mobile_app.ui.modeldetails.similarmodels.MoreSimilarModelsActivity


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
            val intent = Intent(this, AboutModelActivity::class.java)
            intent.putExtra("modelId", viewModel.modelId)
            startActivity(intent)
        }

        val description = binding.textViewModelDetailsShortDesc
        description.text = viewModel.description

        val ratingsFrame = binding.frameLayoutModelDetailsRatings
        val progressBar = binding.linearProgressIndicatorModelDetailsRatings

        val asyncLayoutInflater = AsyncLayoutInflater(this)
        asyncLayoutInflater.inflate(R.layout.fragment_chart, ratingsFrame) { view, _, _ ->
            drawRatingChart(view)
            progressBar.visibility = View.GONE
            ratingsFrame.addView(view)
        }

        val comments = binding.linearLayoutModelDetailsRatingsAndComments
        comments.setOnClickListener() {
            val intent = Intent(this, ReviewActivity::class.java)
            intent.putExtra("modelID", viewModel.modelId)
            startActivity(intent)
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
            val intent = Intent(this, MoreSimilarModelsActivity::class.java)
            startActivity(intent)
        }

        val moreMostUsedModels = binding.linearLayoutModelDetailsMostUsedModels
        moreMostUsedModels.setOnClickListener() {
            val intent = Intent(this, MorePopularModelsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun drawRatingChart(view: View){
        val ratings = view.findViewById<AAChartView>(R.id.aaChartView_chartFragment)

        // Get primary color
        val value = TypedValue()
        theme.resolveAttribute(android.R.attr.colorPrimary, value, true)
        val color = value.data
        val primaryColor = String.format("#%06X", 0xFFFFFF and color)

        val ratingsModel = AAChartModel()
            .chartType(AAChartType.Bar)
            .legendEnabled(false)
            .backgroundColor("#00000000")  // Transparent
            .colorsTheme(arrayOf(primaryColor))
            .categories(viewModel.ratings.keys.toTypedArray())
            .series(arrayOf(
                AASeriesElement()
                    .data(viewModel.ratings.values.toTypedArray())
            ))
        ratings.aa_drawChartWithChartModel(ratingsModel)
    }
}