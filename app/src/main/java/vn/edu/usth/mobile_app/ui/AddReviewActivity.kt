package vn.edu.usth.mobile_app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.Toast;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;

import vn.edu.usth.mobile_app.R;
import vn.edu.usth.mobile_app.databinding.ActivityAddReviewBinding;
import vn.edu.usth.mobile_app.ui.modeldetails.ModelDetailsActivity;

public class AddReviewActivity extends AppCompatActivity {
    private ReviewInfo reviewInfo;
    private ReviewManager manager;
    private ActivityAddReviewBinding binding;
    private AddReviewViewModel viewModel;
    float ratingNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        RatingBar rating = findViewById(R.id.ratingBar_itemReview);
        TextInputEditText comment = findViewById(R.id.edit_comment);

        binding = ActivityAddReviewBinding.inflate(getLayoutInflater());

        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {

                ratingNum = rating;
            }
        });

        Button btn = findViewById(R.id.add_review_back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ModelDetailsActivity.class));
            }
        });

        Button btn1 = findViewById(R.id.add_review_done);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ratingNum <=5 && ratingNum > 0) {
                    messageDone("Posted your review to the list");
                } else {
                    messageDone("Not found ratings");
                }
            }
        });
    }

    public void activateReviewInfo() {
        manager = ReviewManagerFactory.create(this);
        Task<ReviewInfo> request = manager.requestReviewFlow();
        request.addOnCompleteListener((task) -> {
            if (task.isSuccessful()) {
                // We can get the ReviewInfo object
                reviewInfo = task.getResult();
            } else {
                // There was some problem, log or handle the error code.
                Toast.makeText(this, "Review failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void startReviewFlow() {
        Task<Void> flow = manager.launchReviewFlow(this,reviewInfo);
        flow.addOnCompleteListener(task -> {
            Toast.makeText(this, "Complete Review", Toast.LENGTH_SHORT).show();
        });
    }

    private void messageDone(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}