package com.mind.loginregisterapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class RatingApp extends AppCompatActivity {

    TextView txtrating,later;
    EditText feedback;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_app);

        Toolbar toolbar = findViewById(R.id.toolBarRatingBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Rate Us");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        feedback=(EditText)findViewById(R.id.feedbackdata);
        txtrating=(TextView)findViewById(R.id.txtRate);
        ratingBar=(RatingBar)findViewById(R.id.ratingBar);
        later=(TextView)findViewById(R.id.maybelater);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                txtrating.setText("Rate :"+v);
                Toast.makeText(RatingApp.this, "Thank-you For Rating.Do Give Feedback too..", Toast.LENGTH_SHORT).show();
            }
        });

        later.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Dashboard.class));
                finish();
            }

        });

    }

    public void feedbackSubmit(View view) {
        if(TextUtils.isEmpty(feedback.getText())){

            Toast.makeText(getApplicationContext() ,"Feedback Should not be Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "Feedback Submitted.Thank-you For Your Valuable Time", Toast.LENGTH_SHORT).show();
        startActivity(new Intent( getApplicationContext(),Dashboard.class));
    }
}
