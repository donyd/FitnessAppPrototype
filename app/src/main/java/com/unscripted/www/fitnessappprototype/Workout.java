package com.unscripted.www.fitnessappprototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Workout extends AppCompatActivity {

    private String[] arrWorkout = {"Bicep Curl", "Lunges", "Crunches", "Chest Press"};
    ListView mListView;

    //Declare workout button
    Button workoutBtn;

    //This creates a back button to go BACKWARDS
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout);
        //this line creates back button to go BACKWARDS
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        /** START OF ListView setup and details
         * Recycler list view to present a receptacle for exercises
         * which will be displayed based on yet to be finalised logic
         * and also on the basis of whether user has selected a bias i.e
         * Arms, Legs, etcetera
         */

        mListView = (ListView) findViewById(R.id.workout_container);



        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, arrWorkout);

        mListView.setAdapter(mAdapter);

        /**
         * END OF ListView setup and details
         */

        //Initalize workout
        workoutBtn = (Button) findViewById(R.id.button2);

        //workout button is selected and cannot be pressed
        workoutBtn.setPressed(true);
        workoutBtn.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                workoutBtn.setPressed(true);
                return true;
            }
        });

        //When clicked links to HOME page
        Button launchActivityOneButton = (Button) findViewById(R.id.button1);
        launchActivityOneButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO:
                // Launch Activity Two
                // Hint: use Context's startActivity() method

                // Create an intent stating which Activity you would like to
                // start
                // Intent intent = new Intent(this, ActivityTwo.class);
                //Intent intent = new Intent(android.content.Intent, ActivityTwo);
                Intent intent = new Intent(Workout.this, MainActivity.class);

                // Launch the Activity using the intent
                startActivity(intent);

            }
        });

        //WHen clicked links to PROFILE page
        Button launchActivityThreeButton = (Button) findViewById(R.id.button3);
        launchActivityThreeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO:
                // Launch Activity Two
                // Hint: use Context's startActivity() method

                // Create an intent stating which Activity you would like to
                // start
                // Intent intent = new Intent(this, ActivityTwo.class);
                //Intent intent = new Intent(android.content.Intent, ActivityTwo);
                //Intent intent = new Intent(workout.this, Profile.class);

                // Launch the Activity using the intent
                //startActivity(intent);

            }
        });

    }


}


