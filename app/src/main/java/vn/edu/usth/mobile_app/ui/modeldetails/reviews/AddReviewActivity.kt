package vn.edu.usth.mobile_app.ui.modeldetails.reviews

import android.os.Bundle
import android.widget.RatingBar.OnRatingBarChangeListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import vn.edu.usth.mobile_app.R
import vn.edu.usth.mobile_app.databinding.ActivityAddReviewBinding

class AddReviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddReviewBinding
    private val viewModel: AddReviewViewModel? = null
    private var ratingNum = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.materialToolbarAddReview
        toolbar.setNavigationOnClickListener {
            finish()
        }

        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_add_review -> {
                    if (ratingNum <= 5 && ratingNum > 0) {
                        messageDone("Review posted")
                    } else {
                        messageDone("Ratings not found")
                    }
                    true
                }
                else -> false
            }
        }

        val rating = binding.ratingBarItemReview
        rating.onRatingBarChangeListener =
            OnRatingBarChangeListener { ratingBar, rating, b -> ratingNum = rating }
        val comment = binding.editComment
    }

    private fun messageDone(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}