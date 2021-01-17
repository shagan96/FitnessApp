package com.example.android.fitnessapp;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    ImageView topImage, imageBmr, imageChat, imageWorkout;
    LinearLayout bmr, chat, work;
    TextView bmiText, chatText, workoutText;
    Animation top, bottom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bmiText = findViewById(R.id.bmiTxt);
        chatText = findViewById(R.id.chatTxt);
        workoutText = findViewById(R.id.workoutTxt);
        bmr = findViewById(R.id.bmrLayout);
        chat = findViewById(R.id.chatLayout);
        work = findViewById(R.id.workLayout);
        topImage = findViewById(R.id.topImg);

        top = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        topImage.setAnimation(top);
        bmr.setAnimation(bottom);
        chat.setAnimation(bottom);
        work.setAnimation(bottom);

        bmiText.setOnClickListener(v -> startActivity(new Intent(Home.this,BMR.class)));
        chatText.setOnClickListener(v -> startActivity(new Intent(Home.this,ChatApp.class)));
        workoutText.setOnClickListener(v -> startActivity(new Intent(Home.this,Workout.class)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

       switch (item.getItemId()){
           case R.id.nav_bmr:{
               startActivity(new Intent(this, BMR.class));
               break;
           }
           case R.id.nav_chat:{
               startActivity(new Intent(this, ChatApp.class));
               break;
           }
           case R.id.nav_logout:{
               logout();
               Toast.makeText(getApplicationContext(),"Log Out Successful", Toast.LENGTH_SHORT).show();
               break;
           }
           case R.id.nav_home:{
               startActivity(new Intent(this, Home.class));
                break;
           }

       }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {

        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
