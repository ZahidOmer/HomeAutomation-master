package com.example.zahid.homeautomation.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.zahid.homeautomation.Interface.ItemClickListener;
import com.example.zahid.homeautomation.R;

public class MonthViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
    public String devicemac;
    public String momaxampere;
    public String momaxvoltage;
    public String motimestamp;
    public String mounits;

    ItemClickListener itemClickListener;


    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    public MonthViewHolder(View itemView) {
        super(itemView);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition());
    }
}

