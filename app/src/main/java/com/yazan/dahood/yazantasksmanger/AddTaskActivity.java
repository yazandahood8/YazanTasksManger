package com.yazan.dahood.yazantasksmanger;

import android.content.Intent;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;

public class AddTaskActivity extends AppCompatActivity {
    private EditText etText;
    private EditText etPhone;
    private EditText etLocation;
    private Button btnContact;
    private ImageButton btnLocation;
    private Button btnSave;
    private RatingBar rtBarPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        etText=(EditText)findViewById(R.id.etText);
        etPhone=(EditText)findViewById(R.id.etPhone);
        etLocation=(EditText)findViewById(R.id.etLocation);
        btnContact=(Button)findViewById(R.id.btnContact);
        btnLocation=(ImageButton)findViewById(R.id.btnLocation);
        btnSave=(Button)findViewById(R.id.btnSave);
        rtBarPriority=(RatingBar)findViewById(R.id.rtBarPriority);

        eventHandler();



    }

    private void dataHandler() {
        //1. getting data
        String stText = etText.getText().toString();
        String stPhone= etPhone.getText().toString();
        String stLocation= etLocation.getText().toString();


        //2. checking
        if (stText.length() == 0)
            etText.setError("Wrong Text");
        if (stPhone.length() == 0)
            etPhone.setError("Wrong Phone");
        if (stLocation.length() == 0)
            etLocation.setError("Wrong Location");



    }

        private void eventHandler()
        {
            btnContact .setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            btnLocation .setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            btnSave .setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i=new Intent(AddTaskActivity.this,TasksListsActivity.class);
                    startActivity(i);

                }
            });
        }
    }




