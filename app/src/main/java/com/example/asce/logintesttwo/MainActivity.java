package com.example.asce.logintesttwo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private static final String TAG =MainActivity.class.getSimpleName() ;
    private FirebaseAuth mAuth;
    String email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void search(View view) {

        Intent intent = new Intent(this ,MyDay.class);
        startActivity(intent);
    }
}
