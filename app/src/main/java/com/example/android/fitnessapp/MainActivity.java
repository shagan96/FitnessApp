package com.example.android.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText userName, password;
    TextView cb;
    Button signIn;
    FirebaseAuth firebaseAuth;
    Animation top, bottom;
    ImageView image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cb = findViewById(R.id.checkBox);
        getSupportActionBar().hide();
        cb.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Register.class)));

        firebaseAuth = FirebaseAuth.getInstance();
        userName = findViewById(R.id.usernameTxt);
        password = findViewById(R.id.passwordTxt);
        signIn = findViewById(R.id.LoginBtn);
        image = findViewById(R.id.imageView);

        //Animations
        top = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        image.setAnimation(top);
        userName.setAnimation(bottom);
        password.setAnimation(bottom);
        signIn.setAnimation(bottom);

        signIn.setOnClickListener(v -> {

            String emailId = userName.getText().toString().trim();
            String pass = password.getText().toString();

            if (emailId.equals("") || pass.equals("")) {
                Toast.makeText(MainActivity.this, "Email ID or Password cannot be empty", Toast.LENGTH_SHORT).show();
            }
            else{
                loginMethod(emailId,pass);
            }
        });
    }

    private void loginMethod(String userId, String password){

        firebaseAuth.signInWithEmailAndPassword(userId, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {

            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(MainActivity.this,"Logged In Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, Home.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void onClick(View v) {
//        startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
//    }



}
