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

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail;
    private EditText etPassw;
    private Button btnSignUp,btnHelp,btnSignIn;
    private FirebaseAuth auth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail=(EditText)findViewById(R.id.etemail);
        etPassw=(EditText)findViewById(R.id.etpassword);
        btnSignIn=(Button) findViewById(R.id.btnSignIn);
        btnHelp=(Button)findViewById(R.id.btnHelp);
        btnSignUp=(Button)findViewById(R.id.btnSignUp);
        auth=FirebaseAuth.getInstance();


        evenHandler();


    }

    /**
     * 1.etting data from the ui from(edittext,checkbox,radiobutton...)
     * 2.checking data (the email text is ok ,the password...)
     * 3.dealing with the data
     * */
    private void dataHandler()
    {
        //1.getting data
        String stEmail=etEmail.getText().toString();
        String stPassw=etPassw.getText().toString();
        boolean isok=true;


        //2.checking
        if (stEmail.length()<3) {
            etEmail.setError("Worng Email");
            isok = false;
        }

        if (stPassw.length()<3) {
            etPassw.setError("Worng Password");
            isok = false;
        }
        if(isok==true) {
            signIn(stEmail, stPassw);
        }


    }

    /**
     * putting event handers(listeners)
     */
    private void evenHandler() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);

            }
        });
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataHandler();



            }
        });
    }


    private void signIn(String email, String passw) {
        auth.signInWithEmailAndPassword(email,passw).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(LoginActivity.this, "signIn Successful.", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(LoginActivity.this,TasksListsActivity.class);
                    startActivity(intent);
                    //  finish();
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "signIn failed."+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });
    }


}
