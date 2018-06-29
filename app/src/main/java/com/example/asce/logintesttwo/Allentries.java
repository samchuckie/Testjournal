package com.example.asce.logintesttwo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Allentries extends AppCompatActivity {
    RecyclerView rv;
    RecyclerView.LayoutManager layoutManager;
    private DatabaseReference mDatabase;
    List<jentry> abc;
    Entryadapt entryadapt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allentries);
        rv=findViewById(R.id.entry_rv);
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        entryadapt= new Entryadapt();
        rv.setAdapter(entryadapt);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        abc= new ArrayList<>();

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getallchilds(dataSnapshot.child("users").child("id"));
                Toast.makeText(getApplicationContext(), "some data has changed",Toast.LENGTH_SHORT).show();
                Log.d("sam" , "data has been changed");
                Log.i("sam" , "Number of children " + dataSnapshot.child("users").child("id").getChildrenCount() );

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void getallchilds(DataSnapshot dataSnapshot) {
        for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
        {
            jentry jj= dataSnapshot1.getValue(jentry.class);
            abc.add(jj);
            Log.i("sam","array added");
            Log.i("sam" , "" + abc.size());



        }
        for (int x=0 ;x<abc.size();x++)
        {
            Log.i("sam", abc.get(x).aaa() + "   -  " + abc.get(x).bbb() + "\n");
        }
        entryadapt.setentries(abc);

    }
}
