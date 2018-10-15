package com.example.zahid.homeautomation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

    }


    public void accountList(View view) {
        Intent intent = new Intent(this, AccountList.class);
        startActivity(intent);
    }

    public void graphActivity(View view) {
        Intent intent = new Intent(this, GraphActivity.class);
        startActivity(intent);
    }
}
