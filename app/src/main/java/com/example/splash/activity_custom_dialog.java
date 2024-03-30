package com.example.splash;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.splash.R;
import com.example.splash.track;

public class activity_custom_dialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);

        // Adding onClickListener to the "Okay" Button
        Button okayButton = findViewById(R.id.btn_okay);

        if (okayButton != null) {
            okayButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Handle the "Okay" button click
                    onOkayButtonClick();
                }
            });
        }
    }

    // Error might be around this line
    private void onOkayButtonClick() {
        // Create an explicit intent for the 'track' activity
        Intent trackIntent = new Intent();
        trackIntent.setClassName("com.example.splash", "com.example.splash.track");

        // Start the 'track' activity
        startActivity(trackIntent);

        // Finish the current activity if you want
        finish();
    }

}
