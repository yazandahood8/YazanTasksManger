package com.yazan.dahood.yazantasksmanger;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    private EditText etemail,etFullName,etPassw1,etPassw2;
    private Button btnSignUp;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etemail=(EditText) findViewById(R.id.etemail);
        etFullName=(EditText) findViewById(R.id.etFulName);
        etPassw1=(EditText) findViewById(R.id.etpassw1);
        etPassw2=(EditText) findViewById(R.id.etPassw2);
        btnSignUp=(Button) findViewById(R.id.btnSignUp);


        auth=FirebaseAuth.getInstance();


        evenHandler();
    }
    private void dataHuntler()
    {
        String stEmail = etemail.getText().toString();
        String stPassw1 = etPassw1.getText().toString();
        String stPassw2 = etPassw2.getText().toString();
        String stFullName = etFullName.getText().toString();
        boolean isok=true;
        if (stEmail.length() == 3) {
            etemail.setError("Wrong Text");
            isok = false;
        }
        if (stPassw1.length()==3){
            etPassw1.setError("Wrong Password");

        }
        if (isok) {


            creatAcount(stEmail, stPassw1);
        }

    }


    private void evenHandler()
    {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
               // Intent i=new Intent(SignUpActivity.this,TasksListsActivity.class);
              // startActivity(i);
                dataHuntler();


            }
        });
    }



    private FirebaseAuth.AuthStateListener authStateListener=new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            //4.
            FirebaseUser user=firebaseAuth.getCurrentUser();
            if(user!=null)
            {
                //user is signed in
                Toast.makeText(SignUpActivity.this, "user is signed in.", Toast.LENGTH_SHORT).show();

            }
            else
            {
                //user signed out
                Toast.makeText(SignUpActivity.this, "user signed out.", Toast.LENGTH_SHORT).show();

            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }
    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListener!=null)
            auth.removeAuthStateListener(authStateListener);
    }

    private void creatAcount(String email, String passw) {
        auth.createUserWithEmailAndPassword(email, passw).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, "Authentication Successful.", Toast.LENGTH_SHORT).show();
                     Intent i=new Intent(SignUpActivity.this,TasksListsActivity.class);
                     startActivity(i);
                    finish();
                } else {
                    Toast.makeText(SignUpActivity.this, "Authentication failed." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });
    }







}







