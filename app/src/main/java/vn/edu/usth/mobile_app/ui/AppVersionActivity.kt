package vn.edu.usth.mobile_app.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import vn.edu.usth.mobile_app.R;
import vn.edu.usth.mobile_app.databinding.ActivityAppVersionBinding;
import vn.edu.usth.mobile_app.ui.modeldetails.ModelDetailsActivity;
import vn.edu.usth.mobile_app.ui.usermenu.UserMenuFragment;

public class AppVersionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_version);

        Toolbar toolbar = findViewById(R.id.app_version_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UserMenuFragment.class));
            }
        });
    }
}