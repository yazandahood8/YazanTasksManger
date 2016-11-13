package com.yazan.dahood.yazantasksmanger.data;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.yazan.dahood.yazantasksmanger.R;
import com.yazan.dahood.yazantasksmanger.data.MyTask;

/**
 * Created by user on 10/30/2016.
 */
public class MyAdapterTask extends ArrayAdapter<MyTask>  {
    private DatabaseReference reference;

    public MyAdapterTask(Context context, int resource) {
        super(context, resource);
        String email= FirebaseAuth.getInstance().getCurrentUser().getEmail().replace(".","_");
        reference= FirebaseDatabase.getInstance().getReference(email).child("MyTasks");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(getContext()).inflate(R.layout.task,parent,false);
        TextView tvTitle=(TextView)convertView.findViewById(R.id.tvItmTitle);
        TextView tvText=(TextView)convertView.findViewById(R.id.tvItmText);
        TextView tvPhone=(TextView)convertView.findViewById(R.id.tvItmPhone);
        RatingBar rate=(RatingBar)convertView.findViewById(R.id.rtbItmPriority);
        ImageButton btnCall=(ImageButton)convertView.findViewById(R.id.btnItmCall);
        // del 01
        ImageButton btnDel=(ImageButton)convertView.findViewById(R.id.btnDel);
        final MyTask myTask=getItem(position);
        tvTitle.setText(myTask.getTitle());
        tvPhone.setText(myTask.getPhone());
        tvText.setText(myTask.getAddress());
        rate.setRating(myTask.getPrioroty());
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //make call
            }
        });
        //del 02
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());



                // set title
                // alertDialogBuilder.setTitle("Your Title");

                // set dialog message
                alertDialogBuilder.setMessage("Do you want to delete this task?").setCancelable(false).setPositiveButton("Yes",new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog,int id) {
                        //delete from the firebase server
                        reference.child(myTask.getId()).removeValue(new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                if (databaseError==null)//deleted
                                {
                                    Toast.makeText(getContext(),"Deleted",Toast.LENGTH_LONG).show();
                                    //delete from this adapter
                                    remove(myTask);
                                    //to update the listview
                                    setNotifyOnChange(true);
                                }

                            }
                        });



                    }
                })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();


            }
        });
        return convertView;
    }
}
