package com.example.splash;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class login extends AppCompatActivity {

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Find the Button by its ID
        Button loginUpButton = findViewById(R.id.loginUpButton);

        // Set an OnClickListener on the Button
        loginUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When the Button is clicked, create an Intent to start the DashboardActivity
                Intent intent = new Intent(login.this, Dashboard.class);

                // Add any necessary data to the intent (e.g., user information, etc.)
                // intent.putExtra("key", "value");

                // Start the DashboardActivity
                startActivity(intent);

                // Finish the LoginActivity (optional, depends on your use case)
                // finish();
            }
        });
    }
}
