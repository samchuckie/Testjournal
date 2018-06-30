package com.example.asce.logintesttwo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.asce.logintesttwo.Database.EntryDatabase;
import com.example.asce.logintesttwo.Database.EntryRom;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Allentries extends AppCompatActivity implements Entryadapt.ItemClickListener {
    RecyclerView rv;
    RecyclerView.LayoutManager layoutManager;
    private DatabaseReference mDatabase;
    List<jentry> abc;
    List<EntryRom> entryRoms;
    Entryadapt entryadapt;
    EntryDatabase entryDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allentries);
        rv=findViewById(R.id.entry_rv);
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        entryadapt= new Entryadapt(this);
        rv.setAdapter(entryadapt);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        abc= new ArrayList<>();
        entryDatabase= EntryDatabase.getinstance(this);

        LiveData<List<EntryRom>> entryRomLiveData=entryDatabase.entryDao().loadall();
        entryRomLiveData.observe(this, new Observer<List<EntryRom>>() {
            @Override
            public void onChanged(@Nullable List<EntryRom> entryRoms) {
                entryadapt.setentries(entryRoms);
            }
        });

//        mDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                getallchilds(dataSnapshot.child("users").child("id"));
//                Toast.makeText(getApplicationContext(), "some data has changed",Toast.LENGTH_SHORT).show();
//                Log.d("sam" , "data has been changed");
//                Log.i("sam" , "Number of children " + dataSnapshot.child("users").child("id").getChildrenCount() );
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


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
//        entryadapt.setentries(abc);

    }

    public void addentry(View view) {
        Intent intent = new Intent(this,MyDay.class);
        startActivity(intent);
    }

    @Override
    public void onItemClickListener(int itemId) {
        Intent intent=new Intent(this,MyDay.class);
        intent.putExtra(MyDay.EXTRA_TASK_ID, itemId);
        startActivity(intent);
    }
}
