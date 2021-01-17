package com.example.android.fitnessapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android.fitnessapp.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    EditText fullName, username, emailId, password;
    Button registerBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    FirebaseDatabase data;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fAuth = FirebaseAuth.getInstance();
        data = FirebaseDatabase.getInstance();

        fullName = findViewById(R.id.firstTxt);
        username = findViewById(R.id.userText);
        emailId = findViewById(R.id.emailTxt);
        password = findViewById(R.id.passTxt);
        registerBtn = findViewById(R.id.registerBtn);

        progressDialog = new ProgressDialog(Register.this);
        progressDialog.setTitle("Creating user account");


        registerBtn.setOnClickListener(v -> {

            String email = emailId.getText().toString().trim();
            String pass = password.getText().toString().trim();
            String fName = fullName.getText().toString().trim();
            String uName = username.getText().toString().trim();

            if (email.equals("") || pass.equals("") || fName.equals("") || uName.equals("")) {
                Toast.makeText(Register.this, "All fields are required", Toast.LENGTH_SHORT).show();
            } else if (pass.length() < 6) {
                Toast.makeText(Register.this, "Password Must be >= 6 Characters", Toast.LENGTH_SHORT).show();

            } else {
                progressDialog.show();
                createUser(uName, email, pass);
            }

        });
    }

    private void createUser(String userName, String email, String pass) {

        fAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    User user = new User(userName, email, pass);
                    String id = task.getResult().getUser().getUid();

                    data.getReference().child("Users").child(id).setValue(user);

                    Toast.makeText(Register.this, "User Created Successfully.",
                            Toast.LENGTH_SHORT).show();

                    emailId.setText("");
                    fullName.setText("");
                    password.setText("");
                    username.setText("");
                    // updateUI(user);
                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(Register.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }

                // ...
            }
        });
    }
}