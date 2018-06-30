package com.example.asce.logintesttwo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.asce.logintesttwo.Database.AppExecutors;
import com.example.asce.logintesttwo.Database.EntryDatabase;
import com.example.asce.logintesttwo.Database.EntryRom;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MyDay extends AppCompatActivity {
    FloatingActionButton addbutton ;
    public static final String EXTRA_TASK_ID = "extraTaskId";
    String daydate,story,storystitle;
    EditText titledittext,desc;
    private DatabaseReference mDatabase;
    int counter;
    String counterstring;
    int entryid = 0;
    private EntryDatabase entryDatabase;
    private EntryRom entryRom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_day);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        addbutton=findViewById(R.id.floatingActionButton);
        titledittext = findViewById(R.id.titler);
        desc = findViewById(R.id.storer);
        counter = 7 ;
        counterstring = String.valueOf(counter);
        entryDatabase=EntryDatabase.getinstance(this);

//           TODO :          if(TextUtils.isEmpty(enteredTask)){
        // we turned of animation
        Intent intent =getIntent();
        if (intent!=null && intent.hasExtra(EXTRA_TASK_ID))
        {
        Log.i("sam", "it has something");
        entryid = intent.getIntExtra(EXTRA_TASK_ID ,entryid);
//        String node = String.valueOf(entryid);
//            mDatabase.child("users").child("id").child(node).addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    jentry jj= dataSnapshot.getValue(jentry.class);
//                Log.i("sam", " " + dataSnapshot.getValue() );
//                Log.i("sam" , "Title -" + jj.aaa() + " Content -  " + jj.bbb() );
////                    getallchilds(dataSnapshot);
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                }
//            });
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                  entryRom=  entryDatabase.entryDao().selecttask(entryid);
                    final String gottentitle =entryRom.getTitle();
                    final String gottendesc = entryRom.getContent();
                    titledittext.setText(gottentitle);
                    desc.setText(gottendesc);

                }
            });


        }





    }

    private void getallchilds(DataSnapshot dataSnapshot) {
        for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
            jentry jj = dataSnapshot1.getValue(jentry.class);
            Log.i("sam", "Succesful man");
            Log.i("sam", jj.aaa() + "   -  " + jj.bbb() + "\n");


        }
    }


    private void writeNewUser(String userId, String name, String content) {
        jentry user = new jentry(name, content);


         mDatabase.child("users").child("id").child(counterstring).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
             @Override
             public void onComplete(@NonNull Task<Void> task) {
                 Log.i("sam" , "Succesfull");
             }
         });
         counter++;
    }



    public void sick(View view) {
        final String gottentitle =titledittext.getText().toString();
        final String gottendesc =desc.getText().toString();
        counterstring = String.valueOf(counter);
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                EntryRom entryRom =new EntryRom(gottentitle,gottendesc);
                entryDatabase.entryDao().insertTask(entryRom);
            }
        });

       // writeNewUser(counterstring ,gottentitle,gottendesc);
    }
}
