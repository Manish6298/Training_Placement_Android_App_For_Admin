package com.mind.loginregisterapps;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Dashboard extends AppCompatActivity {

    // Declaration

    Button btnProfile,btnVideo,btnUpdates,btnQuiz,btnResume,btnLogout;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = findViewById(R.id.toolBarDashboard);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);


        // Intialization

        btnProfile=(Button)findViewById(R.id.buttonProfile);
        btnVideo=(Button)findViewById(R.id.buttonEdu);
        btnUpdates=(Button)findViewById(R.id.buttonHealth);
        btnQuiz=(Button)findViewById(R.id.buttonGoal);
        btnResume=(Button)findViewById(R.id.buttonFinance);
        btnLogout=(Button)findViewById(R.id.buttonComfort);

        firebaseAuth= FirebaseAuth.getInstance();
        firebaseUser= firebaseAuth.getCurrentUser();

        // Defining Functionality

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Dashboard.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                Toast.makeText(Dashboard.this, "User LogOut...", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  startActivity(new Intent(Dashboard.this, StudentRecord.class));
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  startActivity(new Intent(Dashboard.this,MyAccount.class));
            }
        });

        btnUpdates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  startActivity(new Intent(Dashboard.this,SendUpdate.class));
            }
        });

        btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this,Notes.class));
            }
        });

        btnResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this,About_Us.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.examplemenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.termscondition :
                startActivity(new Intent(getApplicationContext(),TermsCondition.class));
                return true;

            case R.id.AboutApp :
                startActivity(new Intent(getApplicationContext(),About_App.class));
                return true;

            case R.id.rateApp :
                startActivity(new Intent(getApplicationContext(),RatingApp.class));
                return true;

            case R.id.shareApp :

                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plan");
                String shareBody="Click this Link and Download Trainning & Placement App : https://www.tour2tech.com ";
                String shareSubject = " Download Trainning & Placement Android App ";

                sharingIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT,shareSubject);

                startActivity(Intent.createChooser(sharingIntent,"Share Via"));
                return true;


             default: return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you Sure you want to Exit ")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Dashboard.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
           AlertDialog alertDialog=builder.create();
           alertDialog.show();

    }
}
