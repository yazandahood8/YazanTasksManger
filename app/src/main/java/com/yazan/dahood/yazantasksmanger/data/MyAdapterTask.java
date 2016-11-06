package com.yazan.dahood.yazantasksmanger.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.yazan.dahood.yazantasksmanger.R;
import com.yazan.dahood.yazantasksmanger.data.MyTask;

/**
 * Created by user on 10/30/2016.
 */
public class MyAdapterTask extends ArrayAdapter<MyTask>  {

    public MyAdapterTask(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(getContext()).inflate(R.layout.task,parent,false);
        TextView tvTitle=(TextView)convertView.findViewById(R.id.tvItmTitle);
        TextView tvText=(TextView)convertView.findViewById(R.id.tvItmText);
        TextView tvPhone=(TextView)convertView.findViewById(R.id.tvItmPhone);
        RatingBar rate=(RatingBar)convertView.findViewById(R.id.rtbItmPriority);
        ImageButton btnCall=(ImageButton)convertView.findViewById(R.id.btnItmCall);
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
        return convertView;
    }
}
