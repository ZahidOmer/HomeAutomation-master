package com.example.zahid.homeautomation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zahid.homeautomation.Model.Account;
import com.example.zahid.homeautomation.ViewHolder.AccountViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main2Activity extends AppCompatActivity {

    //Firebase
    FirebaseDatabase database;
    DatabaseReference account;

    //firebasae ui adapter
    FirebaseRecyclerOptions<Account> options;
    FirebaseRecyclerAdapter<Account, AccountViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
}
