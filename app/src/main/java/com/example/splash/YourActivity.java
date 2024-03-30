package com.example.splash;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class YourActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // You can add the code here or in an event handler like a button click
        launchOtherActivity();
    }

    // Example method to launch the other activity
    private void launchOtherActivity() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setComponent(new ComponentName("com.example.trail3", "com.example.trail3.MainActivity"));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Start the activity
        startActivity(intent);
    }
}
