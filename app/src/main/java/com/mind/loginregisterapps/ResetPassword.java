package com.mind.loginregisterapps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {

    EditText resetpass;
    Button btnreset;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        progressDialog= new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        resetpass =(EditText) findViewById(R.id.txtforgetemail);
        btnreset = (Button) findViewById(R.id.resetbtn);
        mAuth = FirebaseAuth.getInstance();

        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String useremail = resetpass.getText().toString().trim();
                if (TextUtils.isEmpty(useremail)){
                    Toast.makeText(ResetPassword.this, "Enter Email !!", Toast.LENGTH_SHORT).show();
                }
                else {
                    mAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()){
                                progressDialog.show();
                                Toast.makeText(ResetPassword.this, "Email Sent to your mail Account..", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                startActivity(new Intent(ResetPassword.this,MainActivity.class));
                            }
                            else
                            {
                                String msg = task.getException().getMessage();
                                Toast.makeText(ResetPassword.this, "Error Occurred"+msg, Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }

                        }
                    });
                }
            }
        });


    }
}
