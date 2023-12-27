package vn.edu.usth.mobile_app.ui.modeldetails.reviews

import androidx.lifecycle.ViewModel
import vn.edu.usth.mobile_app.model.ReviewData

class ReviewViewModel: ViewModel() {
    private val _reviews: ArrayList<ReviewData> = arrayListOf()
    var modelId = -1

    val reviews: ArrayList<ReviewData> get() = _reviews

    fun fetchReviews() {
        _reviews.add(ReviewData(1,1,"User 1", 5, 1,"This is a very good model"))
        _reviews.add(ReviewData(2,2,"User 2", 4, 1,"This is a good model"))
        _reviews.add(ReviewData(3,3,"User 3", 3, 1,"This is a model"))
        _reviews.add(ReviewData(4,4,"User 4", 2, 1,"This is a bad model"))
        _reviews.add(ReviewData(5,5,"User 5", 1, 1,"This is a very bad model"))
        _reviews.add(ReviewData(6,6,"User 6", 5, 1,""))
        _reviews.add(ReviewData(7,7,"User 7", 4, 1,"Ok"))
    }
}