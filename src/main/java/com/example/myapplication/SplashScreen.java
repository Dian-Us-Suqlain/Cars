package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4000;  // Splash Screen time to be displayed on the start of Application

    TextView tvSplash;
    ImageView ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        tvSplash = findViewById(R.id.tv_splash);
        ivLogo = findViewById(R.id.iv_car_logo);

        /*new Handler().postDelayed(new Runnable() {  //This handler class is used for communication between UI background with runnable()
            @Override
            public void run() {
                Intent sendIntent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(sendIntent);
            }
        },SPLASH_TIME_OUT);  //This will make the Splash Screen run for only 4s or 4000ms*/
    }
}
