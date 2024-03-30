package com.example.splash;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class waste extends AppCompatActivity {

    private static final String TAG = "VideoPlayback";
    private VideoView videoView1;
    private VideoView videoView2;

    @Override
    protected void onResume() {
        super.onResume();
        videoView1.start();
        videoView2.start(); // Start the second video as well
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoView1.pause();
        videoView2.pause(); // Pause the second video as well
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waste);

        // Hide the action bar for full-screen experience
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        // Make the activity full-screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        videoView1 = findViewById(R.id.videoView1); // Initialize the first VideoView
        videoView2 = findViewById(R.id.videoView2); // Initialize the second VideoView

        // Constructing the URIs for the videos
        String videoPath1 = "android.resource://" + getApplicationContext().getPackageName() + "/raw/" + R.raw.waste;
        String videoPath2 = "android.resource://" + getApplicationContext().getPackageName() + "/raw/" + R.raw.top;

        Uri uri1 = Uri.parse(videoPath1);
        Uri uri2 = Uri.parse(videoPath2);

        // Setting up the URIs for the videos
        videoView1.setVideoURI(uri1);
        videoView2.setVideoURI(uri2);

        // Adding media controllers for play/pause, seekbar, etc.
        MediaController mediaController1 = new MediaController(this);
        MediaController mediaController2 = new MediaController(this);

        videoView1.setMediaController(mediaController1);
        videoView2.setMediaController(mediaController2);

        // Start playing the videos automatically when prepared
        videoView1.setOnPreparedListener(mp -> {
            Log.d(TAG, "Video 1 is prepared. Starting playback.");
            mp.start();
        });

        videoView2.setOnPreparedListener(mp -> {
            Log.d(TAG, "Video 2 is prepared. Starting playback.");
            mp.start();
        });

        // Optional: Add error listeners to log any errors
        videoView1.setOnErrorListener((mp, what, extra) -> {
            Log.e(TAG, "Error during playback - Video 1: what: " + what + ", extra: " + extra);
            return false;
        });

        videoView2.setOnErrorListener((mp, what, extra) -> {
            Log.e(TAG, "Error during playback - Video 2: what: " + what + ", extra: " + extra);
            return false;
        });

        // Optional: Add completion listeners to restart videos when playback completes
        videoView1.setOnCompletionListener(mp -> {
            Log.d(TAG, "Video 1 playback completed. Restarting.");
            videoView1.start();
        });

        videoView2.setOnCompletionListener(mp -> {
            Log.d(TAG, "Video 2 playback completed. Restarting.");
            videoView2.start();
        });
    }
}
