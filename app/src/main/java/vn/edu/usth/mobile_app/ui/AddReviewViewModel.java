package vn.edu.usth.mobile_app.ui;

import androidx.lifecycle.ViewModel;

import com.google.android.material.textfield.TextInputEditText;


public class AddReviewViewModel extends ViewModel {
    private Float ratingNum;
    private TextInputEditText comment;

    public Float getRatingNum() {
        return ratingNum;
    }

    public void setRatingNum(Float ratingNum) {
        this.ratingNum = ratingNum;
    }

    public TextInputEditText getComment() {
        return comment;
    }

    public void setComment(TextInputEditText comment) {
        this.comment = comment;
    }
}
