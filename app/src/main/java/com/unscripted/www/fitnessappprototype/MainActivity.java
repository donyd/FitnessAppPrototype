package com.unscripted.www.fitnessappprototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    // Declare ToggleButtons instances
    ToggleButton beginner, novice, advanced;

    //Declare Home, workout and Profile Buttons
    Button homeBtn;
    Button workoutBtn;
    Button profileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);

        // Initialize ToggleButtons
        beginner = (ToggleButton) findViewById(R.id.toggleBeginner);
        novice = (ToggleButton) findViewById(R.id.toggleNovice);
        advanced = (ToggleButton) findViewById(R.id.toggleAdvanced);

        //Initialize Home, workout and Profile Buttons
        homeBtn= (Button) findViewById(R.id.button1);
        workoutBtn= (Button) findViewById(R.id.button2);
        profileBtn= (Button) findViewById(R.id.button3);

        beginner.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    beginner.setTextOn("Beginner");

                    novice.setChecked(false);

                    advanced.setChecked(false);
                }
            }
        });

        novice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    beginner.setChecked(false);

                    novice.setTextOn("Novice");

                    advanced.setChecked(false);
                }
            }
        });

        advanced.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    beginner.setChecked(false);

                    novice.setChecked(false);

                    advanced.setTextOn("Advanced");
                }
            }
        });


        //Open workout Activity when Body part Selected
        ImageButton launchActivityButton = (ImageButton) findViewById(R.id.imageButton);
        launchActivityButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO:
                // Launch Activity Two
                // Hint: use Context's startActivity() method

                // Create an intent stating which Activity you would like to
                // start
                // Intent intent = new Intent(this, ActivityTwo.class);
                //Intent intent = new Intent(android.content.Intent, ActivityTwo);
                Intent intent = new Intent(MainActivity.this, WorkoutActivity.class);

                // Launch the Activity using the intent
                startActivity(intent);

            }
        });
        //MENU BUTTONS
        //Home button is selected and cannot be pressed
        homeBtn.setPressed(true);
        homeBtn.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                homeBtn.setPressed(true);
                workoutBtn.setPressed(false);
                profileBtn.setPressed(false);
                return true;
            }
        });


        //WHen clicked links to WORKOUT page
        Button btnWorkout = (Button) findViewById(R.id.button2);
        btnWorkout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO:
                // Launch Activity Two
                // Hint: use Context's startActivity() method

                // Create an intent stating which Activity you would like to
                // start
                // Intent intent = new Intent(this, ActivityTwo.class);
                //Intent intent = new Intent(android.content.Intent, ActivityTwo);
               Intent intent = new Intent(MainActivity.this, WorkoutActivity.class);


                // Launch the Activity using the intent
                startActivity(intent);

            }
        });

        //WHen clicked links to PROFILE page
        Button btnProfile = (Button) findViewById(R.id.button3);
        btnProfile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO:
                // Launch Activity Two
                // Hint: use Context's startActivity() method

                // Create an intent stating which Activity you would like to
                // start
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);


                // Launch the Activity using the intent
                startActivity(intent);

            }
        });


    }
}
