package com.example.arroundme;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class dashboard extends AppCompatActivity {

    private TextView profileNameSettings, numberSettings;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

//        Bundle extras = getIntent().getExtras();
//
//        profileNameSettings = findViewById(R.id.profileNameSettings);
//        numberSettings = findViewById(R.id.numberSettings);
//
//        profileNameSettings.setText(extras.getString("fullname"));
//        numberSettings.setText(extras.getString("phone"));
    }
}