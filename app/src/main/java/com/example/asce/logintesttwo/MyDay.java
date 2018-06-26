package com.example.asce.logintesttwo;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;

import com.example.asce.logintesttwo.Database.Journals;

public class MyDay extends AppCompatActivity {
    FloatingActionButton addbutton ;
    Journals journals;
    String daydate,story,storystitle;
    EditText titledittext,desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_day);
        titledittext=findViewById(R.id.titler);
        desc=findViewById(R.id.storer);

        storystitle=desc.getText().toString();
        story=titledittext.getText().toString();
        daydate="june";

        addbutton=findViewById(R.id.floatingActionButton);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                journals.setmDate(daydate);
                journals.setmDescription(story);
                journals.setmTitle(storystitle);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.mydaymenu ,menu);
        return super.onCreateOptionsMenu(menu);


    }
}
