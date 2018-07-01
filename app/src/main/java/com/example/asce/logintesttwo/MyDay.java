package com.example.asce.logintesttwo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.asce.logintesttwo.Database.AppExecutors;
import com.example.asce.logintesttwo.Database.EntryDatabase;
import com.example.asce.logintesttwo.Database.EntryRom;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class MyDay extends AppCompatActivity {
    Button addbutton ;
    public static final String EXTRA_TASK_ID = "extraTaskId";
    String daydate,story,storystitle;
    EditText titledittext,desc;
    private DatabaseReference mDatabase;
    String extraname=null;
    private EntryDatabase entryDatabase;
    private EntryRom entryRom;
     String gottentitle;
     String gottendesc;
     String keys;

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
            extraname=intent.getStringExtra(EXTRA_TASK_ID);
        Log.i("sam", "it has something that is " + extraname );
//

        }





    }




    public void sick(View view) {

       getdata();
       writeNewUser(gottentitle,gottendesc);
    }

    public void delete(View view) {
        getdata();
        deleteuser(extraname,null,null);
    }

    public void update(View view) {
        getdata();
        updateuser(extraname,gottentitle,gottendesc);

    }
    private void writeNewUser( String name, String content) {
        String key = mDatabase.push().getKey();
        Log.i("sam",key);

        final EntryRom user = new EntryRom(name, content);
        user.setId(key);
        keys=key;
        Map<String, Object> postValues = user.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put( key, postValues);

        mDatabase.updateChildren(childUpdates);
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                entryDatabase.entryDao().insertTask(user);
            }
        });

    }
    private void updateuser(String key ,String name, String content) {
        Log.i("sam",key);
        final EntryRom user = new EntryRom(name, content);
        user.setId(key);
        Map<String, Object> postValues = user.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put( key, postValues);
        mDatabase.updateChildren(childUpdates);
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                entryDatabase.entryDao().updateTask(user);
            }
        });

    }
    private void deleteuser(final String key , String name, String content) {
        Log.i("sam",key);
        final EntryRom user = new EntryRom(name, content);
        Map<String, Object> postValues = user.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put( key, postValues);
        mDatabase.updateChildren(childUpdates);
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                EntryRom todelete = entryDatabase.entryDao().selecttask(key);
                Log.e("sam" , "Title = " + todelete.getTitle() + "Content = " + todelete.getContent());
               entryDatabase.entryDao().deleteTask(todelete);
            }
        });

    }
    void getdata()
    {
        gottentitle=titledittext.getText().toString();
        gottendesc =desc.getText().toString();
    }
}
