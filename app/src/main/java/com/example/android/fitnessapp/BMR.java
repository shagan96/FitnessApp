package com.example.android.fitnessapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BMR extends AppCompatActivity {

    EditText age, feet, weight, inches;
    RadioButton male, female;
    Button calcBMR;
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmr);

        age = (EditText) findViewById(R.id.ageTxt);
        feet = (EditText) findViewById(R.id.feetTxt);
        inches = (EditText) findViewById(R.id.inchesTxt);
        weight = (EditText) findViewById(R.id.weightTxt);
        male = (RadioButton) findViewById(R.id.maleBtn);
        female = (RadioButton) findViewById(R.id.femaleBtn);
        calcBMR = (Button) findViewById(R.id.caclBtn);
        display = (TextView) findViewById(R.id.displayTxt);

        calcBMR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double totalInches = (12*Integer.parseInt(feet.getText().toString())) + Integer.parseInt(inches.getText().toString());
                double menCalculatedBMI;
                double womenCalculatedBMI;
                menCalculatedBMI = (66.47 + (6.24*Integer.parseInt(weight.getText().toString()))+(12.7*totalInches) - (6.755*Integer.parseInt(age.getText().toString())));
                womenCalculatedBMI = (655.1 + (4.35*Integer.parseInt(weight.getText().toString()))+(4.7*totalInches) - (4.7*Integer.parseInt(age.getText().toString())));

                if (age.getText().toString().equals("") || feet.getText().toString().equals("") || inches.getText().toString().equals("") || weight.getText().toString().equals("")) {
                    Toast.makeText(BMR.this, "All the fields must be filled", Toast.LENGTH_SHORT).show();
                }

                if(male.isChecked()){
                    display.setText("Your BMR is: " + menCalculatedBMI + " Cal/day");
                }
                else if(female.isChecked()){
                    display.setText("Your BMR is:" + womenCalculatedBMI + " Cal/day");
                }

                else{
                    display.setText("Please select your Gender!");
                }
            }
        });

    }
}
