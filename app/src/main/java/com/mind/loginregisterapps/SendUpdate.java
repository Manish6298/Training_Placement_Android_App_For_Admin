package com.mind.loginregisterapps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SendUpdate extends AppCompatActivity  {

    private RecyclerView mRecyclerView;
    Button update;
    List<UpdateData> myUpdateList;
    ProgressDialog progressDialog;
    MyAdapterUpload myAdapterUpload;

    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_update);

        // ToolBar

        Toolbar toolbar = findViewById(R.id.toolBarSendUpdate);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Send Updates");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView1);
        update=(Button)findViewById(R.id.btnupload);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(SendUpdate.this,1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("LOADING POSTS...");


        myUpdateList=new ArrayList<>();


        final MyAdapterUpload myAdapterUpload = new MyAdapterUpload(SendUpdate.this,myUpdateList);
        mRecyclerView.setAdapter(myAdapterUpload);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Updates");

        progressDialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {

                myUpdateList.clear();

                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {

                    UpdateData updateData = itemSnapshot.getValue(UpdateData.class);
                    updateData.setKey(itemSnapshot.getKey());
                    myUpdateList.add(updateData);

                }

                myAdapterUpload.notifyDataSetChanged();
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
                startActivity(new Intent(SendUpdate.this,UploadUpdate.class));
            }
        });

    }


}
