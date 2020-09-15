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

public class DetailUpdateActivity extends AppCompatActivity {

    TextView uTitle,uDescription,upostdate;
    String key="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_update);

        Toolbar toolbar = findViewById(R.id.bgHeader);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        uTitle=(TextView)findViewById(R.id.atitle);
        uDescription=(TextView)findViewById(R.id.adescription);
        upostdate=(TextView)findViewById(R.id.apostdate);

        Bundle mBundle = getIntent().getExtras();

        if (mBundle != null){

            uTitle.setText(mBundle.getString("Title"));
            uDescription.setText( mBundle.getString("Description"));
            upostdate.setText("Post Date :"+mBundle.getString("keyValue"));
            key=mBundle.getString("keyValue");

        }

    }

    public void btnDeletePost(View view) {

        final DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Updates");

        reference.child(key).removeValue();
        Toast.makeText(this, "Post Deleted Successfully", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),SendUpdate.class));
        finish();

    }
}
