package com.yazan.dahood.yazantasksmanger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail;
    private EditText etPassword;
    private Button btnSignup;
    private Button btnSignin;
    private Button btnHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail=(EditText)findViewById(R.id.etEmail);
        etPassword=(EditText)findViewById(R.id.etpassword);
        btnSignup=(Button)findViewById(R.id.btnSignUp);
        btnSignin=(Button)findViewById(R.id.btnSignIn);
        btnHelp=(Button)findViewById(R.id.btnhelp);

        eventHandler();

    }

    /**
     * getting data from the ui form (edittext, checkbox,radiobutton etc..)
     * checking data (the email text is ok, the password,
     * dealing with the data
     */
    private void dataHandler()
    {
        //1. getting data
        String stEmail=etEmail.getText().toString();
        String stPassword=etPassword.getText().toString();
        //2. checking
        if (stEmail.length()==0)
            etEmail.setError("Wrong Email");
        if (stPassword.length()==0)
            etPassword.setError("Wrong Password");


    }
    /**
     * putting event handler for (listeners)
     */
    private void  eventHandler()
    {
        btnSignin .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(LoginActivity.this,TasksListsActivity.class);
                startActivity(i);



            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(LoginActivity.this,SingUpActivity.class);
                startActivity(i);



            }
        });
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });



    }





}
