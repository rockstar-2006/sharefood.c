package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class singup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        TextView tvngo = findViewById(R.id.tvngo);
        tvngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singup.this.finish();
            }
        });
    }
}