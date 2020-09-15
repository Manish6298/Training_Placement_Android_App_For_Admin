package com.mind.loginregisterapps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class UpdateMyAccount extends AppCompatActivity {

    EditText aname,aid,amobile;
    TextView aemail;
    FirebaseAuth firebaseAuth;

    ProgressDialog progressDialog;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    AdminData ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_my_account);

        aemail=(TextView) findViewById(R.id.updatemail);
        aname=(EditText) findViewById(R.id.updatename);
        aid=(EditText) findViewById(R.id.updateid);
        amobile=(EditText) findViewById(R.id.updatemobile);

        // Class Object Creation

        ad = new AdminData();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating Profile ...");

        firebaseAuth= FirebaseAuth.getInstance();

        firebaseDatabase=FirebaseDatabase.getInstance();
         databaseReference=firebaseDatabase.getReference("Admin").child(firebaseAuth.getCurrentUser().getUid());

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){

            aname.setText(bundle.getString("name"));
            aid.setText(bundle.getString("id"));
            amobile.setText(bundle.getString("mobile"));
            aemail.setText(bundle.getString("email"));
        }
        else Toast.makeText(this, "Data Not Found", Toast.LENGTH_SHORT).show();



    }

    public void updateMyAccount(View view) {
        progressDialog.show();
        ad.setName(aname.getText().toString());
        ad.setId(aid.getText().toString());
        ad.setMobileno(amobile.getText().toString());
        ad.setEmail(aemail.getText().toString());
        databaseReference.setValue(ad);
        progressDialog.dismiss();
        Toast.makeText(this, "Profile Updated Successfully..", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),MyAccount.class));
        finish();

    }
}
