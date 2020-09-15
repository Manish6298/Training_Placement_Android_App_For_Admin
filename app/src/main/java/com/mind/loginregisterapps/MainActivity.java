package com.mind.loginregisterapps;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btRegister;
    private TextView tvLogin,tvForget;

    private EditText login_email,login_password;
    private Button loginbtn;
    private FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btRegister  = findViewById(R.id.btRegister);
        tvLogin     = findViewById(R.id.tvLogin);
        tvForget = findViewById(R.id.tvForgot);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("LOGGING IN ...");

        btRegister.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();

        login_email=(EditText)findViewById(R.id.etemail);
        login_password=(EditText)findViewById(R.id.etpass);
        loginbtn=(Button)findViewById(R.id.btLogin);

        // Reset Password

        tvForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ResetPassword.class));
            }
        });

        // Login Screen

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                String email=login_email.getText().toString().trim();
                String password = login_password.getText().toString().trim();

                if(TextUtils.isEmpty(email)){

                    Toast.makeText(MainActivity.this, "Email Should not be Empty", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }

                if(TextUtils.isEmpty(password)){

                    Toast.makeText(MainActivity.this, "Password Should not be Empty", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }

                if(password.length()<6){

                    Toast.makeText(MainActivity.this, "Short Password,length must be more than 6", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }


                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    progressDialog.dismiss();
                                    startActivity(new Intent(getApplicationContext(), Dashboard.class));


                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(MainActivity.this, "Login Failed or User Not Found", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }

                                // ...
                            }
                        });


            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {

        if (currentUser != null) {
            // User is signed in
            Intent i = new Intent(MainActivity.this, Dashboard.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        } else {
            // User is signed out

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        if (v==btRegister){
            Intent intent   = new Intent(MainActivity.this,RegisterActivity.class);
            Pair[] pairs    = new Pair[1];
            pairs[0] = new Pair<View,String>(tvLogin,"tvLogin");
            ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
            startActivity(intent,activityOptions.toBundle());
        }
    }
}
