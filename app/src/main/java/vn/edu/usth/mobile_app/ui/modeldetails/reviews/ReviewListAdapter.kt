package vn.edu.usth.mobile_app.ui.modeldetails.reviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vn.edu.usth.mobile_app.databinding.ItemListReviewBinding
import vn.edu.usth.mobile_app.model.ReviewData
import java.text.DateFormat

class ReviewListAdapter(
    private val reviewList: List<ReviewData>
): RecyclerView.Adapter<ReviewListAdapter.ViewHolder>() {
    class ViewHolder(binding: ItemListReviewBinding): RecyclerView.ViewHolder(binding.root) {
        val authorName = binding.textViewItemReviewUsername
        val ratingBar = binding.ratingBarItemReview
        val date = binding.textViewItemReviewDate
        val comment = binding.textViewItemReviewReview

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemListReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.authorName.text = reviewList[position].authorName
        holder.ratingBar.rating = reviewList[position].stars.toFloat()
        val dateFormat = DateFormat.getDateInstance(DateFormat.SHORT)
        val dateString = dateFormat.format(reviewList[position].date)
        holder.date.text = dateString
        holder.comment.text = reviewList[position].comment
    }
}