package vn.edu.usth.mobile_app.ui.modeldetails.reviews

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import vn.edu.usth.mobile_app.R
import vn.edu.usth.mobile_app.databinding.ActivityRecyclerViewBinding
import vn.edu.usth.mobile_app.ui.AddReviewActivity

class ReviewActivity: AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerViewBinding
    private val viewModel: ReviewViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fab = ExtendedFloatingActionButton(this)
        fab.text = getString(R.string.Add_Review)
        fab.icon = AppCompatResources.getDrawable(this, R.drawable.baseline_add_24)
        fab.setOnClickListener {
            val intent = Intent(this, AddReviewActivity::class.java)
            startActivity(intent)
        }
        val params = CoordinatorLayout.LayoutParams(
            CoordinatorLayout.LayoutParams.WRAP_CONTENT,
            CoordinatorLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            gravity = Gravity.BOTTOM or Gravity.END
            rightMargin = dpToPx(16)
            bottomMargin = dpToPx(32)
        }
        binding.root.addView(fab, params)

        if(intent.extras != null) {
            viewModel.modelId = intent.extras!!.getInt("modelID")
        }
        viewModel.fetchReviews()

        val toolBar = binding.materialToolbarRVActivity
        toolBar.setNavigationOnClickListener { finish() }
        toolBar.title = getString(R.string.Reviews)

        val recyclerView = binding.recyclerViewRVActivity
        recyclerView.adapter = ReviewAsyncAdapter(viewModel.reviews)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun dpToPx(dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            resources.displayMetrics
        ).toInt()
    }
}