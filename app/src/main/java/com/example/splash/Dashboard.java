package com.example.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Find the QuickDono CardView
        CardView quickDonoCardView = findViewById(R.id.btn_qk);

        // Set an OnClickListener
        quickDonoCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event, for example, start a new activity
                Intent intent = new Intent(Dashboard.this, quickdono.class);
                startActivity(intent);
            }
        });

        // Find the ReminderHarbor CardView
        CardView reminderHarborCardView = findViewById(R.id.cardViewReminderHarbor);

        // Set an OnClickListener for ReminderHarbor
        reminderHarborCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event, start the desired activity (activity_main_2.xml)
                Intent intent = new Intent(Dashboard.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        CardView trackCardView = findViewById(R.id.track);

        // Set an OnClickListener for ReminderHarbor
        trackCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event, start the TrackActivity
                Intent intent = new Intent(Dashboard.this, track.class);
                startActivity(intent);
            }
        });
        CardView wastewisetipsCardView = findViewById(R.id.tips);

// Set an OnClickListener for wastewisetipsCardView
        wastewisetipsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event, start the waste activity
                try {
                    Intent intent = new Intent(Dashboard.this, waste.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }}