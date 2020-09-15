package com.mind.loginregisterapps;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class UploadUpdate extends AppCompatActivity {


    EditText title,description;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_update);

        title=(EditText)findViewById(R.id.txttitle);
        description=(EditText)findViewById(R.id.txtdescription);
        b1=(Button)findViewById(R.id.uploaddata);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sending Updates...");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String myCurrentDateTime = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

                UpdateData updateData = new UpdateData(title.getText().toString(),description.getText().toString());
                progressDialog.show();
                FirebaseDatabase.getInstance().getReference("Updates").child(myCurrentDateTime).setValue(updateData).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {


                        if (task.isSuccessful()){

                            Toast.makeText(UploadUpdate.this, "Update Successfully Sent", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UploadUpdate.this,"Failed...", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    }
                });


            }
        });


    }


}
