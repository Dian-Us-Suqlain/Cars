package com.example.carassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    ImageView ivLogo;

    private static int SPLASH_TIME_OUT = 6000;  // Splash Screen time to be displayed for 6 seconds on the start of Application

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ivLogo = findViewById(R.id.iv_car_logo);

        Animation myAnimation = AnimationUtils.loadAnimation(this, R.anim.mytransition);  //Animation class loads the animations you have made for your
                                                                                                  // for your components
        ivLogo.startAnimation(myAnimation);

        new Handler().postDelayed(new Runnable() {  //This handler class is used for communication between UI background with runnable() method
            @Override
            public void run() {                 // Handler class communicates with the UI's with runnable() method
                Intent sendIntent = new Intent(SplashScreen.this, HomePage.class);
                startActivity(sendIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}

