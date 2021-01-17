package com.example.android.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Workout extends AppCompatActivity {

    TextView chest, legs, arms, abs;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        chest = findViewById(R.id.chestWrkt);
        legs = findViewById(R.id.legsWrkt);
        arms = findViewById(R.id.armsWrkt);
        abs = findViewById(R.id.absWrkt);

        chest.setOnClickListener(v -> startActivity(new Intent(this,ChestWorkout.class)));
        legs.setOnClickListener(v -> startActivity(new Intent(this,LegsWorkout.class)));
        arms.setOnClickListener(v -> startActivity(new Intent(this,ArmsWorkout.class)));
        abs.setOnClickListener(v -> startActivity(new Intent(this,AbsWorkout.class)));
    }
}
