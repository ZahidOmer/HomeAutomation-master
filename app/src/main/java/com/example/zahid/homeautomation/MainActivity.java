package com.example.zahid.homeautomation;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zahid.homeautomation.Interface.ItemClickListener;
import com.example.zahid.homeautomation.Model.Account;
import com.example.zahid.homeautomation.Utill.Common;
import com.example.zahid.homeautomation.ViewHolder.AccountViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
//    FirebaseDatabase database = FirebaseDatabase.getInstance();
//    DatabaseReference myRef = database.getReference("message");

    //Firebase
    FirebaseDatabase database;
    DatabaseReference account;

    //firebasae ui adapter
    FirebaseRecyclerOptions<Account> options;
    FirebaseRecyclerAdapter<Account, AccountViewHolder> adapter;

    //view
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rv_account);

//        myRef.setValue("Hello, World!");

        showAccountData();

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayout);
        setAccount();
    }

    private void setAccount() {
        adapter.startListening();
//        recyclerView.setAdapter(adapter);
    }

    public void showAccountData() {
        database = FirebaseDatabase.getInstance();
        account = database.getReference(Common.STR_Account);
        options = new FirebaseRecyclerOptions.Builder<Account>()
                .setQuery(account, Account.class)
                .build();



        adapter = new FirebaseRecyclerAdapter<Account, AccountViewHolder>(options) {
            @Override
            public void startListening() {
                super.startListening();

            }

            @Override
            protected void onBindViewHolder(@NonNull AccountViewHolder holder, int position, @NonNull final Account model) {
                try {
                    holder.email.setText(String.valueOf(model.getEmail()));
                    holder.deviceName.setText(String.valueOf(model.getDevicemac()));
                    holder.live.setText(String.valueOf(model.getLive()));
                    Log.i("myvalue", model.getEmail());
                    Log.i("myvalue", model.getDevicemac());
                    Log.i("myvalue", String.valueOf(model.getLive()));
                } catch (Exception e) {
                    Log.i("myvalue", String.valueOf(e));
                }

                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Common.Account_ID = adapter.getRef(position).getKey();
                        Toast.makeText(MainActivity.this, "Email: " + model.getEmail(), Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public AccountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_account_item, parent, false);
                return new AccountViewHolder(itemView);

            }
        };

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void graphActivity(View view) {
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
    }
}
