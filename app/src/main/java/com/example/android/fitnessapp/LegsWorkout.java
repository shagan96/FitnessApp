package com.example.android.fitnessapp;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class LegsWorkout extends AppCompatActivity {

    Button startBtn, resetBtn;
    ImageView anchor;
    Animation rotating;
    Chronometer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legs);

        startBtn = findViewById(R.id.startButton);
        resetBtn = findViewById(R.id.resetButton);
        timer = findViewById(R.id.timer);
        anchor = (ImageView) findViewById(R.id.icanchor);

        rotating = AnimationUtils.loadAnimation(this, R.anim.rotate_clockwise);

        startBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                anchor.startAnimation(rotating);
                timer.setBase(SystemClock.elapsedRealtime());
                timer.start();
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                anchor.startAnimation(rotating);
                timer.setBase(SystemClock.elapsedRealtime());
                timer.start();
            }
        });




    }
}
