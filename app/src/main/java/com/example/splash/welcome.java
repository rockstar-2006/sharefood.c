package com.example.splash;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class welcome extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Find the sign-in button
        Button signInButton = findViewById(R.id.signInButton);

        // Set OnClickListener for the sign-in button
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to start LoginActivity
                Intent intent = new Intent(welcome.this, login.class);
                startActivity(intent);
            }
        });
        Button signUpButton = findViewById(R.id.signUpButton);

        // Set OnClickListener for the sign-up button
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to start RegisterActivity
                Intent intent = new Intent(welcome.this, register.class);
                startActivity(intent);
            }
        });

        // ... Other initialization code for your welcome activity
    }
}





