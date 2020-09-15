package com.mind.loginregisterapps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Notes extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    FloatingActionButton update;

    List<NotesData> myNotesList;

    ProgressDialog progressDialog;

    MyAdapterNotes myAdapterNotes;

    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        Toolbar toolbar = findViewById(R.id.toolBarNotes);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Notes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerViewNotes);
        update=(FloatingActionButton)findViewById(R.id.btnnotes);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(Notes.this,1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("LOADING NOTES...");

        myNotesList=new ArrayList<>();

        final MyAdapterNotes myAdapterNotes = new MyAdapterNotes(Notes.this,myNotesList);
        mRecyclerView.setAdapter(myAdapterNotes);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Notes");

        progressDialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {

                myNotesList.clear();

                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {

                    NotesData notesData = itemSnapshot.getValue(NotesData.class);
                    notesData.setKey(itemSnapshot.getKey());
                    myNotesList.add(notesData);

                }

                myAdapterNotes.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

                progressDialog.dismiss();

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Notes.this,UploadNotes.class));
            }
        });

    }
}
