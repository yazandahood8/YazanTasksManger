package com.yazan.dahood.yazantasksmanger;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.renderscript.Sampler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.yazan.dahood.yazantasksmanger.data.MyTask;

import java.util.Calendar;
import java.util.Date;

public class AddTaskActivity extends AppCompatActivity {
    private EditText etText, etPhone, etLocation;
    private Button btnContacts, btnSave;
    private ImageButton imageButton;
    private RatingBar ratingBar;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        etText = (EditText) findViewById(R.id.etText);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etLocation = (EditText) findViewById(R.id.etLocation);
        btnContacts = (Button) findViewById(R.id.btnContacts);
        btnSave = (Button) findViewById(R.id.btnSave);
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);


        evenHandler();
    }


    private void dataHandler() {
        String stText = etText.getText().toString();
        String stPhone=etPhone.getText().toString();
        String stLocation=etLocation.getText().toString();
        float rate=ratingBar.getRating();
        boolean isok=true;

        if (stText.length() < 3) {
            etText.setError("Worng Text");
            isok = false;
        }
        if (stPhone.length() < 3) {
            etPhone.setError("Worng Phone");
            isok = false;
        }

        if (stLocation.length() < 3) {
            etLocation.setError("Worng Location");
            isok = false;
        }
        if (isok==true){
            Intent i = new Intent(AddTaskActivity.this, TasksListsActivity.class);
            startActivity(i);
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
            Date date = Calendar.getInstance().getTime();
            MyTask t = new MyTask();
            t.setPhone(stPhone);
            t.setAddress(stLocation);
            t.setTitle(stText);
            t.setWhen(date);
            t.setPrioroty(rate);

            String email= FirebaseAuth.getInstance().getCurrentUser().getEmail();
            email=email.replace(".","_");
            reference.child(email).child("MyTasks").push().setValue(t, new DatabaseReference.CompletionListener() {
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                    if (databaseError == null) {

                        Toast.makeText(getBaseContext(), "save ok", Toast.LENGTH_LONG).show();


                    } else {

                        Toast.makeText(getBaseContext(), "save Err" + databaseError.getMessage(), Toast.LENGTH_LONG).show();

                        databaseError.toException().printStackTrace();
                    }

                }

            });

        }



    }


    private void evenHandler() {
        btnContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataHandler();




            }

        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddTaskActivity.this, MapsActivity.class);
                startActivity(i);


            }

        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataHandler();

            }



        });
    }

    }






