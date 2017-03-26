package com.unscripted.www.fitnessappprototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class ProfileActivity extends AppCompatActivity {
    // Declare buttons
    Button homeBtn, workoutBtn, profileBtn;


    // Creates a back button to go BACKWARDS
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //this line creates back button to go BACKWARDS
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Initialize Home, workout and Profile Buttons
        homeBtn= (Button) findViewById(R.id.button1);
        workoutBtn= (Button) findViewById(R.id.button2);
        profileBtn= (Button) findViewById(R.id.button3);

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
                Intent intent = new Intent(ProfileActivity.this, WorkoutActivity.class);


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
                Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);


                // Launch the Activity using the intent
                startActivity(intent);

            }
        });

    }




}
