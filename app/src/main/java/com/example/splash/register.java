package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        }catch (Exception e){

        }

        setContentView(R.layout.activity_register);
        TextView tvvoluteer = findViewById(R.id.tvvoluteer);
        tvvoluteer.setOnClickListener(onClickLvolunteer());
    }
    private View.OnClickListener onClickLvolunteer(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register.this,singup.class);
                register.this.startActivity(intent);

            }
        };
    }
}