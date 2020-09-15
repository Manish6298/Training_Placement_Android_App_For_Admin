package com.mind.loginregisterapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    TextView Email,Eno,Mno,Name,Date,Gender,Address,Year,Branch,SSC,HSC,Diploma,Fe,Se,Te,Be,Liveback,Deadback,Hobbies,Internship,Achivement;
    String Key="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = findViewById(R.id.bgHeader);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Profile

        Email=(TextView)findViewById(R.id.txtEmail);
        Eno=(TextView)findViewById(R.id.txtEnroll);
        Mno=(TextView)findViewById(R.id.txtMobile);
        Name=(TextView)findViewById(R.id.txtName);
        Date=(TextView)findViewById(R.id.txtDOB);
        Gender=(TextView)findViewById(R.id.txtGender);
        Address=(TextView)findViewById(R.id.txtAddress);

        // Qualifiation

        Year=(TextView)findViewById(R.id.txtYear);
        Branch=(TextView)findViewById(R.id.txtBranch);
        SSC=(TextView)findViewById(R.id.txtSSC);
        HSC=(TextView)findViewById(R.id.txtHSC);
        Diploma=(TextView) findViewById(R.id.txtDiploma);
        Fe=(TextView)findViewById(R.id.txtFE);
        Se=(TextView)findViewById(R.id.txtSE);
        Te=(TextView)findViewById(R.id.txtTE);
        Be=(TextView)findViewById(R.id.txtBE);
        Liveback=(TextView)findViewById(R.id.txtLive);
        Deadback=(TextView)findViewById(R.id.txtDEAD);

        // Skills

        Hobbies=(TextView)findViewById(R.id.txtHobbies);
        Internship=(TextView)findViewById(R.id.txtIntern);
        Achivement=(TextView)findViewById(R.id.txtAchivement);



        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null){

            // Profile

            Email.setText(mBundle.getString("Email"));
            Eno.setText( mBundle.getString("Eno"));
            Mno.setText(mBundle.getString("Mno"));
            Name.setText(mBundle.getString("Name"));
            Date.setText(mBundle.getString("Date"));
            Gender.setText(mBundle.getString("Gender"));
            Address.setText(mBundle.getString("Address"));
            Key = mBundle.getString("StudentKey");


            // Qualification

            SSC.setText(mBundle.getString("Ssc")+"%");
            HSC.setText(mBundle.getString("Hsc")+"%");
            Diploma.setText(mBundle.getString("Diploma")+"%");
            Fe.setText(mBundle.getString("FE")+" SGPA");
            Se.setText(mBundle.getString("SE")+" SGPA");
            Te.setText(mBundle.getString("TE")+" SGPA");
            Be.setText(mBundle.getString("BE")+" SGPA");
            Liveback.setText(mBundle.getString("Live"));
            Deadback.setText(mBundle.getString("Dead"));
            Year.setText(mBundle.getString("Year"));
            Branch.setText(mBundle.getString("Branch"));



            // Skills

            Hobbies.setText(mBundle.getString("Hobbies"));
            Internship.setText(mBundle.getString("Internship"));
            Achivement.setText(mBundle.getString("Achivement"));



            //foodImage.setImageResource(mBundle.getInt("Image"));
          /*
            Glide.with(this)
                    .load(mBundle.getString("Image"))
                    .into(foodImage);


           */
        }

    }

    public void btnDeleteRecord(View view) {

        final DatabaseReference reference= FirebaseDatabase.getInstance().getReference("StudentRegister");

        reference.child(Key).removeValue();
        Toast.makeText(this, "Record Deleted Successfully", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),StudentRecord.class));
        finish();

    }
}
