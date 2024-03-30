package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PostActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        // Retrieve data from Intent
        String foodDetails = getIntent().getStringExtra("FOOD_DETAILS");
        String foodQuantity = getIntent().getStringExtra("FOOD_QUANTITY");
        // Retrieve more data as needed

        // Now you can use these values in your PostActivity
    }
}