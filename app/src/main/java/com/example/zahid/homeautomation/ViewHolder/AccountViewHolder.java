package com.example.zahid.homeautomation.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.TextView;

import com.example.zahid.homeautomation.Interface.ItemClickListener;
import com.example.zahid.homeautomation.R;

import java.util.ArrayList;
import java.util.List;

public class AccountViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView email;
    public TextView deviceName;
    public TextView live;

    ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    public AccountViewHolder(View itemView) {
        super(itemView);
        email = (TextView) itemView.findViewById(R.id.tv_email);
        deviceName = (TextView) itemView.findViewById(R.id.tv_device);
        live = (TextView) itemView.findViewById(R.id.tv_live);

        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition());
    }
}
