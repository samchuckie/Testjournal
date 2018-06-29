package com.example.asce.logintesttwo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MyDay extends AppCompatActivity {
    FloatingActionButton addbutton ;
    String daydate,story,storystitle;
    EditText titledittext,desc;
    private DatabaseReference mDatabase;
    ArrayList<jentry> abc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_day);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        addbutton=findViewById(R.id.floatingActionButton);
        titledittext = findViewById(R.id.titler);
        desc = findViewById(R.id.storer);
        writeNewUser("id","Title ", "sec");
        abc= new ArrayList<>();

//                      if(TextUtils.isEmpty(enteredTask)){
        // we turned of animation




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
        for(int x=0 ;x < 5;x++)
        {
            Log.i("sam", "abc");
        }



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

    }

     private void writeNewUser(String userId, String name, String content) {
        jentry user = new jentry(name, content);

        mDatabase.child("users").child(userId).child("1").setValue(user);
    }



    public void sick(View view) {
        String gottentitle =titledittext.getText().toString();
        desc.setText(gottentitle);
    }
}
