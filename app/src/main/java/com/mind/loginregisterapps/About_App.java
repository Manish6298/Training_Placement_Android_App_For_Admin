package com.mind.loginregisterapps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class About_App extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about__app);

        Toolbar toolbar = findViewById(R.id.toolBarNotes);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("About App");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
}
