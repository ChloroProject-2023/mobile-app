package vn.edu.usth.mobile_app.ui

import androidx.lifecycle.ViewModel
import com.google.android.material.textfield.TextInputEditText

class AddReviewViewModel : ViewModel() {
    var ratingNum: Float? = null
    var comment: TextInputEditText? = null
}
