package vn.edu.usth.mobile_app.ui.modeldetails.reviews

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vn.edu.usth.mobile_app.R
import vn.edu.usth.mobile_app.databinding.ItemListReviewBinding
import vn.edu.usth.mobile_app.model.ReviewData
import vn.edu.usth.mobile_app.util.AsyncCell
import java.text.DateFormat

class ReviewAsyncAdapter(
    private val reviewList: List<ReviewData>
): RecyclerView.Adapter<ReviewAsyncAdapter.ViewHolder>() {
    class ViewHolder(view: ViewGroup): RecyclerView.ViewHolder(view) {}

    class ReviewAsyncCell(context: Context): AsyncCell(context, R.layout.item_list_review) {
        lateinit var binding: ItemListReviewBinding

        override fun createDataBindingView(view: View): View {
            binding = ItemListReviewBinding.bind(view)
            return binding.root
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val frame = ReviewAsyncCell(parent.context)
        frame.inflate()
        return ViewHolder(frame)
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cell = holder.itemView as ReviewAsyncCell

        cell.bindWhenInflated {
            cell.binding.textViewItemReviewUsername.text = reviewList[position].authorName
            cell.binding.ratingBarItemReview.rating = reviewList[position].stars.toFloat()
            val dateFormat = DateFormat.getDateInstance(DateFormat.SHORT)
            val dateString = dateFormat.format(reviewList[position].date)
            cell.binding.textViewItemReviewDate.text = dateString
            cell.binding.textViewItemReviewReview.text = reviewList[position].comment
        }
    }
}