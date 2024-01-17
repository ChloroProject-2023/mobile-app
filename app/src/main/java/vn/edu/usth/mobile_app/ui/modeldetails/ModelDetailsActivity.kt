package vn.edu.usth.mobile_app.ui.modeldetails

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.entryOf
import vn.edu.usth.mobile_app.databinding.ActivityModelDetailsBinding
import vn.edu.usth.mobile_app.ui.PopupUpload
import vn.edu.usth.mobile_app.ui.modeldetails.aboutmodel.AboutModelActivity
import vn.edu.usth.mobile_app.ui.modeldetails.popularmodels.MorePopularModelsActivity
import vn.edu.usth.mobile_app.ui.modeldetails.reviews.ReviewActivity
import vn.edu.usth.mobile_app.ui.modeldetails.similarmodels.MoreSimilarModelsActivity
import vn.edu.usth.mobile_app.ui.profile.ProfileActivity


class ModelDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityModelDetailsBinding
    private val viewModel: ModelDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModelDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val useButton = binding.buttonModelDetailsUse
//        useButton.setOnClickListener() {
//            startActivity(Intent(this, PopupUpload::class.java))
//        }

        val aboutModel = binding.linearLayoutModelDetailsAboutThisModel
        aboutModel.setOnClickListener() {
            val intent = Intent(this, AboutModelActivity::class.java)
            intent.putExtra("modelId", viewModel.modelId)
            startActivity(intent)
        }

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
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        val description = binding.textViewModelDetailsShortDesc
        description.text = viewModel.description

        drawRatingChart()

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

    private fun drawRatingChart(){
        val chart = binding.chartViewModelDetailsRatings
        val entryList = viewModel.ratings.map { (key, value) ->
            entryOf(
                key.toFloat(),
                value.toFloat()
            )
        }.toList()
        chart.entryProducer = ChartEntryModelProducer(entryList)
    }
}