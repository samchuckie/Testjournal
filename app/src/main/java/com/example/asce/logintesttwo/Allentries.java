package com.example.asce.logintesttwo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.asce.logintesttwo.Database.EntryDatabase;
import com.example.asce.logintesttwo.Database.EntryRom;

import java.util.List;

public class Allentries extends AppCompatActivity implements Entryadapt.ItemClickListener {
    RecyclerView rv;
    RecyclerView.LayoutManager layoutManager;
    Entryadapt entryadapt;
    EntryDatabase entryDatabase;
    List<EntryRom> entryRomList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allentries);
        entryDatabase =EntryDatabase.getinstance(this);
        rv=findViewById(R.id.entry_rv);
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        entryadapt= new Entryadapt(this);
        rv.setAdapter(entryadapt);
        LiveData<List<EntryRom>> listLiveData = entryDatabase.entryDao().loadall();
        listLiveData.observe(this, new Observer<List<EntryRom>>() {
            @Override
            public void onChanged(@Nullable List<EntryRom> entryRoms) {
                entryadapt.setentries(entryRoms);
            }
        });
       // jentries =new ArrayList<>();
      //  entryRomList= new ArrayList<>();




//        mDatabase.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                final EntryRom jj= dataSnapshot.getValue(EntryRom.class);
//                jj.setId(dataSnapshot.getKey());
//                EntryRom dd= new EntryRom("","","");
//                key=dataSnapshot.getKey();
//                entryRomList.add(jj);
//                Log.e("sam" , "" + dataSnapshot.getValue() + jj.getTitle() + "    " + jj.getContent() + jj.getId());
//                Log.e("sam" , "" + entryRomList.size());
//
//
//
//
//
//            }
//
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        //new async().execute(entryRomList);

    }



    public void addentry(View view) {
        Intent intent = new Intent(this,MyDay.class);
              startActivity(intent);
    }



    @Override
    public void onItemClickListener(String itemId) {
        Intent intent=new Intent(this,MyDay.class);
        intent.putExtra(MyDay.EXTRA_TASK_ID, itemId);
        startActivity(intent);
    }
}
//class async extends AsyncTask<List<EntryRom>,Void,Void>
//{
//
//
//    @Override
//    protected Void doInBackground(List<EntryRom>... lists) {
//        if(lists !=null) {
//
//
//            for (int i = 0; i < lists.length; i++) {
//                Log.e("sam", "yesss" + lists.length);
//            }
//        }
//        else
//        {
//            Log.e("sam", "naaah");
//
//        }
//        return null;
//    }
//}