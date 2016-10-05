package com.yazan.dahood.yazantasksmanger;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class TasksListsActivity extends AppCompatActivity {
    private EditText etFastAdd;
    private Button btnFastAdd;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_lists);
        etFastAdd=(EditText)findViewById(R.id.etFastAdd);
        btnFastAdd=(Button)findViewById(R.id.btnFastAdd);
        listView=(ListView)findViewById(R.id.listView);

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


}
