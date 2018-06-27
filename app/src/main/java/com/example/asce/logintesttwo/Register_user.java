package com.example.asce.logintesttwo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register_user extends AppCompatActivity {
    private FirebaseAuth newuser;
    private String  email;
    private String  password;
    private String  confirmpassword;
    private EditText emailtv,passwordtv,confirmpasswordtv;
    private static final String TAG =Register_user.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        newuser= FirebaseAuth.getInstance();
        emailtv=findViewById(R.id.reguser);
        passwordtv=findViewById(R.id.regpass);
        confirmpasswordtv=findViewById(R.id.confirmpassword);
        email =emailtv.getText().toString();
        password=passwordtv.getText().toString();;
        confirmpassword=confirmpasswordtv.getText().toString();
        newuser.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "Successful signi");
                            FirebaseUser user = newuser.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });




    }
}
