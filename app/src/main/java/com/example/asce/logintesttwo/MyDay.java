package com.example.asce.logintesttwo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MyDay extends AppCompatActivity {
    FloatingActionButton addbutton ;
    String daydate,story,storystitle;
    EditText titledittext,desc;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_day);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        addbutton=findViewById(R.id.floatingActionButton);
        titledittext = findViewById(R.id.titler);
        desc = findViewById(R.id.storer);
//        writeNewUser("id","Title ", "First stuff man");
        // we turned of animation



    }
//    private void writeNewUser(String userId, String name, String content) {
//        jentry user = new jentry(name, content);
//
//        mDatabase.child("users").child(userId).child("3").setValue(user);
//    }


    public void sick(View view) {
        String gottentitle =titledittext.getText().toString();
        desc.setText(gottentitle);
    }
}
