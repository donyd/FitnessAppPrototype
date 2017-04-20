package com.unscripted.www.fitnessappprototype;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.unscripted.www.fitnessappprototype.sqlite.DatabaseHelper;
import com.unscripted.www.fitnessappprototype.sqlite.ExerciseContract;
import com.unscripted.www.fitnessappprototype.sqlite.ExerciseContract.ExerciseEntry;

import static android.app.PendingIntent.getActivity;

public class WorkoutActivity extends AppCompatActivity {



    private String[] arrWorkout = {"Bicep Curl", "Lunges", "Crunches", "Chest Press"};
    ListView mListView;

    /*
  @ reference https://app.pluralsight.com/player?course=android-database-application-sqlite-building-your-first&author=simone-alessandria&name=android-database-application-sqlite-building-your-first-m3&clip=6&mode=live
   */
    private void readData(){
        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] projection = {ExerciseEntry.COLUMN_TYPE,
                ExerciseEntry.COLUMN_NAME,
                ExerciseEntry.COLUMN_URL};

        String selection = ExerciseEntry.COLUMN_TYPE + " = ? ";
        String[] selectionArgs = {"Abs"};

        Cursor c = db.query(ExerciseEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

        // verifying query return
        int i = c.getCount();
        Log.d("Record Count", String.valueOf(i));

        String rowContent = "";
        while(c.moveToNext()){
            for(i = 0; i<=2; i++){
                rowContent += c.getString(i) + " - ";
            }
            Log.i("Row " + String.valueOf(c.getPosition()), rowContent);
            rowContent = "";
        }
        c.close();
    }



    //Declare activity_workout button
    Button workoutBtn;

    // Creates a back button to go BACKWARDS
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        //this line creates back button to go BACKWARDS
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        /** START OF ListView setup and details
         * List view to present a receptacle for exercises
         * which will be displayed based on yet to be finalised logic
         * and also on the basis of whether user has selected a bias i.e
         * Arms, Legs, etcetera
         *
         *  @ reference https://developer.android.com/guide/topics/ui/declaring-layout.html#AdapterViews         *
         */


        readData();



        mListView = (ListView) findViewById(R.id.workout_container);

        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, arrWorkout);

        mListView.setAdapter(mAdapter);

        // END OF ListView setup and details

        // Get references to ListView items

       mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Intent intent = new Intent(WorkoutActivity.this, ExerciseActivity.class);
               startActivity(intent);

           }
       });


    /*   mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
               *//* AlertDialog.Builder builder = new AlertDialog.Builder(WorkoutActivity.this);
                String listName = mListView.getItemAtPosition(i).toString();
                builder.setMessage(listName).setTitle("Exercise one");
                AlertDialog dialog = builder.create();
                dialog.show(); *//* // Dialog created to test out ListView item click event.

                Intent intent = new Intent(WorkoutActivity.this, ExerciseActivity.class);
                startActivity(intent);

                return true;
            }
        });*/

        // Initialize activity_workout
        workoutBtn = (Button) findViewById(R.id.button2);

        //activity_workout button is selected and cannot be pressed
        workoutBtn.setPressed(true);
        workoutBtn.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                workoutBtn.setPressed(true);

                return true;
            }
        });

        // Links to HOME page
        Button btnHome = (Button) findViewById(R.id.button1);
        btnHome.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO:
                // Launch Activity Two
                // Hint: use Context's startActivity() method

                // Create an intent stating which Activity you would like to
                // start
                // Intent intent = new Intent(this, ActivityTwo.class);
                //Intent intent = new Intent(android.content.Intent, ActivityTwo);
                Intent intent = new Intent(WorkoutActivity.this, MainActivity.class);

                // Launch the Activity using the intent
                startActivity(intent);

            }
        });

        // Links to PROFILE page
        Button btnProfile = (Button) findViewById(R.id.button3);
        btnProfile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO:
                // Launch Activity Two
                // Hint: use Context's startActivity() method

                // Create an intent stating which Activity you would like to
                // start
                Intent intent = new Intent(WorkoutActivity.this, ProfileActivity.class);

                // Launch the Activity using the intent
                startActivity(intent);

            }
        });

    }



}


