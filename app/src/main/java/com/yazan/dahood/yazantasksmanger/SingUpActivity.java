package com.yazan.dahood.yazantasksmanger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SingUpActivity extends AppCompatActivity {
    private EditText etEmail;
    private EditText etPassw1;
    private EditText etPassw2;
    private EditText etFullname;
    private Button btnSignup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassw1 = (EditText) findViewById(R.id.etPassw1);
        etPassw2 = (EditText) findViewById(R.id.etPassw2);
        etFullname=(EditText)findViewById(R.id.etFullName);
        btnSignup=(Button)findViewById(R.id.btnSignUp);

        eventHandler();

    }
    private void dataHandler() {
        //1. getting data
        String stEmail = etEmail.getText().toString();
        String stPassw1= etPassw1.getText().toString();
        String stPassw2=etPassw2.getText().toString();
        String stFullname=etFullname.getText().toString();
        //2. checking
        if (stEmail.length() == 0)
            etEmail.setError("Wrong Email");
        if (stPassw1.length() == 0)
            etPassw1.setError("Wrong Password");
        if (stPassw2.length() == 0)
            etPassw2.setError("Wrong Password");
        if (stFullname.length() == 0)
            etFullname.setError("Wrong name");
    }
    private void eventHandler()
    {
        btnSignup .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SingUpActivity.this,TasksListsActivity.class);
                startActivity(i);


            }
        });
    }

}
