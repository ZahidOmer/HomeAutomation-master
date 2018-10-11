package com.example.zahid.homeautomation.ViewHolder;

import android.app.Activity;
import android.widget.ArrayAdapter;

import com.example.zahid.homeautomation.Model.Month;
import com.example.zahid.homeautomation.R;

import java.util.List;

public class MonthViewHolder extends ArrayAdapter<Month> {

    private Activity context;
    private List<Month> momoList;

    public MonthViewHolder(Activity context, List<Month> moList){
        super(context,R.layout.activity_graph,moList);
        this.context = context;
        this.momoList = moList;
    }




}

