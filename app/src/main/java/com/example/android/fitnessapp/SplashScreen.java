package com.example.android.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    ImageView welcomeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        welcomeImage = findViewById(R.id.welcomeImage);

        Animation aniRotate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.splash_animation);
        welcomeImage.startAnimation(aniRotate);

        Thread thread = new Thread() {

            public void run() {

                try{
                    sleep(5000);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };thread.start();
    }
}