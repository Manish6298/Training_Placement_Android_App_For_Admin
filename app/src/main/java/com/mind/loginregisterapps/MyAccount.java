package com.mind.loginregisterapps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyAccount extends AppCompatActivity {

    TextView aemail,aname,aid,amobile;
    FirebaseAuth firebaseAuth;

    ProgressDialog progressDialog;

    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        // ToolBar

        Toolbar toolbar = findViewById(R.id.bgHeader);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        aemail=(TextView)findViewById(R.id.txtaemail);
        aname=(TextView)findViewById(R.id.txtaname);
        aid=(TextView)findViewById(R.id.txtaid);
        amobile=(TextView)findViewById(R.id.txt_mobileno);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("LOADING ...");

        firebaseAuth= FirebaseAuth.getInstance();

        firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference("Admin").child(firebaseAuth.getCurrentUser().getUid());

        progressDialog.show();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                AdminData member = dataSnapshot.getValue(AdminData.class);
                aname.setText(member.getName());
                aemail.setText(member.getEmail());
                aid.setText(member.getId());
                amobile.setText(member.getMobileno());
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

                Toast.makeText(MyAccount.this, "Retrieve Failed !", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });


    }

    public void UpdateData(View view) {

        startActivity(new Intent(getApplicationContext(),UpdateMyAccount.class)
        .putExtra("name",aname.getText().toString())
        .putExtra("id",aid.getText().toString())
        .putExtra("mobile",amobile.getText().toString())
         .putExtra("email",aemail.getText().toString())
        );

    }
}
