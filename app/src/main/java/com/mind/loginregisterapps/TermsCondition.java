package com.mind.loginregisterapps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TermsCondition extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_condition);

        Toolbar toolbar = findViewById(R.id.toolBarDashboard);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Terms & Conditions");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
}
