package com.mind.loginregisterapps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private RelativeLayout rlayout;
    private Animation animation;

    AdminData ad;

    ProgressDialog progressDialog;

    EditText email,name,id,pass,repass,mobile_no;
    Button register;

    //-------------- Firebase ---------------

    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.bgHeader);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rlayout = findViewById(R.id.rlayout);
        animation = AnimationUtils.loadAnimation(this,R.anim.uptodowndiagonal);
        rlayout.setAnimation(animation);

        firebaseAuth = FirebaseAuth.getInstance();


        storedata();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void storedata()
    {


        // Progress Dialog Init

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("REGISTERING ...");

        // Init For Profile Details of Admin

        email=(EditText)findViewById(R.id.aemail);
        name=(EditText)findViewById(R.id.aname);
        id=(EditText)findViewById(R.id.aid);
        pass=(EditText)findViewById(R.id.apass);
        repass=(EditText)findViewById(R.id.arepass);
        mobile_no=(EditText)findViewById(R.id.amobile);

        register=(Button)findViewById(R.id.aregister);

        // Class Object Creation

        ad = new AdminData();

        // Firebase Database Refrence

        database= FirebaseDatabase.getInstance();
        myRef=database.getReference();

        // Creating Functionality for Button

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.show();

                //Local Variable to store Password

                String password=pass.getText().toString().trim();
                String repassword = repass.getText().toString().trim();

                // Admin Profile Data Store

                ad.setName(name.getText().toString());
                ad.setEmail(email.getText().toString());
                ad.setId(id.getText().toString());
                ad.setMobileno(mobile_no.getText().toString());
                // Validate Data


                if(TextUtils.isEmpty(ad.email)){

                    Toast.makeText(RegisterActivity.this, "Please Enter Email ", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }

                if(TextUtils.isEmpty(ad.name)){

                    Toast.makeText(RegisterActivity.this, "Please Enter Name ", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }


                if(TextUtils.isEmpty(password)){

                    Toast.makeText(RegisterActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }

                if(TextUtils.isEmpty(repassword)){

                    Toast.makeText(RegisterActivity.this, "Please Enter RePassword", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }

                if(password.length()<6){

                    Toast.makeText(RegisterActivity.this, "Password Must be more than 6 digit & less than 1 digit", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }


                if(TextUtils.isEmpty(ad.id)){

                    Toast.makeText(RegisterActivity.this, "Please Enter ID Number ", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }

                if(TextUtils.isEmpty(ad.mobileno)){

                    Toast.makeText(RegisterActivity.this, "Please Enter Mobile Number ", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }

                if(ad.mobileno.length()<10){

                    Toast.makeText(RegisterActivity.this, "Mobile no. must be more 10 digit number! Enter Valid number. ", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

                if(ad.mobileno.length()>10){

                    Toast.makeText(RegisterActivity.this, "Mobile no. must be less than 10 digit number! Enter Valid number. ", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }


                if(password.equals(repassword)){

                    firebaseAuth.createUserWithEmailAndPassword(ad.email, password)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete( Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        // Sign in success, update UI with the signed-in user's information ;

                                        myRef.child("Admin").child(firebaseAuth.getCurrentUser().getUid()).setValue(ad);
                                        progressDialog.dismiss();
                                        Toast.makeText(RegisterActivity.this, "Registeration Done", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));



                                    } else {

                                        String msg= task.getException().toString();
                                        Toast.makeText(RegisterActivity.this, "Error :"+ msg, Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();

                                    }

                                    // ...
                                }
                            });

                }

            }
        });


    }
}
