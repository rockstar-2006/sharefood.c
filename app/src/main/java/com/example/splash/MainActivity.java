package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView name, slogan;
    private ImageView logo;
    private View topview1, topview2, topview3;
    private View bottomView1, bottomView2, bottomView3;

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE);

        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        slogan = findViewById(R.id.slogan);
        logo = findViewById(R.id.logo);

        topview1 = findViewById(R.id.topView1);
        topview2 = findViewById(R.id.topView2);
        topview3 = findViewById(R.id.topView3);

        bottomView1 = findViewById(R.id.bottomView1);
        bottomView2 = findViewById(R.id.bottomView2);
        bottomView3 = findViewById(R.id.bottomView3);

        Animation logoAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoom_animation);
        Animation nameAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoom_animation);

        Animation topview1Animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.top_views_animation);
        Animation topview2Animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.top_views_animation);
        Animation topview3Animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.top_views_animation);

        Animation bottomView1Animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bottom_views_animation);
        Animation bottomView2Animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bottom_views_animation);
        Animation bottomView3Animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bottom_views_animation);


        topview1.startAnimation(topview1Animation);
        bottomView1.startAnimation(bottomView1Animation);

        topview1Animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                topview2.setVisibility(View.VISIBLE);
                bottomView2.setVisibility(View.VISIBLE);

                topview2.startAnimation(topview2Animation);
                bottomView2.startAnimation(bottomView2Animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        topview2Animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                topview3.setVisibility(View.VISIBLE);
                bottomView3.setVisibility(View.VISIBLE);

                topview3.startAnimation(topview3Animation);
                bottomView3.startAnimation(bottomView3Animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        topview3Animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                logo.setVisibility(View.VISIBLE);
                logo.setAnimation(logoAnimation);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Schedule the redirection to WelcomeActivity after 5 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Start WelcomeActivity
                        Intent intent = new Intent(MainActivity.this, welcome.class);
                        startActivity(intent);

                        // Finish MainActivity to prevent coming back to it when pressing back
                        finish();
                    }
                }, 5000); // 5000 milliseconds (5 seconds)
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        logoAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                name.setVisibility(View.VISIBLE);
                name.startAnimation(nameAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        nameAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public int hashCode() {
                return super.hashCode();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                slogan.setVisibility(View.VISIBLE);
                final String animateTxt = slogan.getText().toString();
                slogan.setText("");
                count = 0;

                new CountDownTimer(animateTxt.length() * 100, 100) {

                    @Override
                    public void onTick(long millisUntilFinished) {
                        slogan.setText(slogan.getText().toString() + animateTxt.charAt(count));
                        count++;
                    }

                    @Override
                    public void onFinish() {

                    }
                }.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}