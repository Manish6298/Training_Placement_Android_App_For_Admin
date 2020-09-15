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

public class NotesDetailActivity extends AppCompatActivity {

    TextView uTitle,uDescription,upostdate;
    String key="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_detail);

        // Code for ToolBar

        Toolbar toolbar = findViewById(R.id.bgHeader);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        uTitle=(TextView)findViewById(R.id.notetitle);
        uDescription=(TextView)findViewById(R.id.notedescription);
        upostdate=(TextView)findViewById(R.id.notedate);


        Bundle mBundle = getIntent().getExtras();

        if (mBundle != null){

            uTitle.setText(mBundle.getString("NoteTitle"));
            uDescription.setText( mBundle.getString("NoteDescription"));
            upostdate.setText("Note Date :"+mBundle.getString("NotekeyValue"));
            key=mBundle.getString("NotekeyValue");

        }

    }

    public void btnDeleteNotes(View view) {

        final DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Notes");
        reference.child(key).removeValue();
        Toast.makeText(this, "Post Deleted Successfully", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),Notes.class));
        finish();

    }

}
