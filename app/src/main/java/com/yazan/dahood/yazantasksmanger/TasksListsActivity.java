package com.yazan.dahood.yazantasksmanger;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yazan.dahood.yazantasksmanger.data.MyAdapterTask;
import com.yazan.dahood.yazantasksmanger.data.MyTask;

public class TasksListsActivity extends AppCompatActivity {
    private EditText etFastAdd;
    private Button btnFastAdd;
    private ListView listView;
    private MyAdapterTask adapterTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_lists);
        etFastAdd=(EditText)findViewById(R.id.etFastAdd);
        btnFastAdd=(Button)findViewById(R.id.btnFastAdd);
        listView=(ListView)findViewById(R.id.listView);
        adapterTask=new MyAdapterTask(this,R.layout.task);
        initListView();

        listView.setAdapter(adapterTask);


        eventHandler();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.yazan, menu);

        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnItmLogout:
                FirebaseAuth.getInstance().signOut();

                Intent i = new Intent(this, LoginActivity.class);
                startActivity(i);
                break;
        }
        return true;
    }



    private void dataHandler() {
        //1. getting data
        String stFastAdd = etFastAdd.getText().toString();
        //2. checking
        if (stFastAdd.length() == 0)
            etFastAdd.setError("Wrong Email");
    }

    private void eventHandler()
    {
        btnFastAdd .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(TasksListsActivity.this,AddTaskActivity.class);
                startActivity(i);

            }
        });
    }
    public void initListView(){
        String email=FirebaseAuth.getInstance().getCurrentUser().getEmail().replace(".","_");
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference(email);
        reference.child("MyTasks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapterTask.clear();
                for (DataSnapshot ds:dataSnapshot.getChildren())
                {
                    //4.
                    MyTask myTask=ds.getValue(MyTask.class);
                    myTask.setId(ds.getKey());
                    //4.
                    //Add myTask to Adatpter
                    adapterTask.add(myTask);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });
    }








}
