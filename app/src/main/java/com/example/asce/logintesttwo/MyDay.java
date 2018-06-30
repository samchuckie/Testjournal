package com.example.asce.logintesttwo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    Button addbutton ;
    public static final String EXTRA_TASK_ID = "extraTaskId";
    String daydate,story,storystitle;
    EditText titledittext,desc;
    private DatabaseReference mDatabase;
    String username;
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


         mDatabase.child("users").child("id").child(userId).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
             @Override
             public void onComplete(@NonNull Task<Void> task) {
                 Log.i("sam" , "Succesfull");
             }
         });

    }



    public void sick(View view) {
        final String gottentitle =titledittext.getText().toString();
        final String gottendesc =desc.getText().toString();
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                 entryRom =new EntryRom(gottentitle,gottendesc);
                entryDatabase.entryDao().insertTask(entryRom);
                EntryRom last=  entryDatabase.entryDao().lasttask();
                int ids=last.getId();
                 username=String.valueOf(ids);
                Log.i("sam", username);

                writeNewUser(username,gottentitle,gottendesc);
            }
        });

       // writeNewUser(counterstring ,gottentitle,gottendesc);
    }

    public void delete(View view) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                entryRom=  entryDatabase.entryDao().selecttask(entryid);
                entryDatabase.entryDao().deleteTask(entryRom);
            }
        });
    }

    public void update(View view) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final String gottentitle =titledittext.getText().toString();
                final String gottendesc =desc.getText().toString();
                EntryRom entryRom =new EntryRom(gottentitle,gottendesc);
                entryRom.setId(entryid);

                entryDatabase.entryDao().updateTask(entryRom);
                username=String.valueOf(entryid);
                writeNewUser(username,gottentitle,gottendesc);

            }
        });

    }
    private void updateuser(String userId, String name, String content) {
        jentry user = new jentry(name, content);


        mDatabase.child("users").child("id").child(userId).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.i("sam" , "Succesfull");
            }
        });

    }
}
