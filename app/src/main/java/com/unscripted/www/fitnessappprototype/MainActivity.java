package com.unscripted.www.fitnessappprototype;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.unscripted.www.fitnessappprototype.sqlite.ExerciseContract;
import com.unscripted.www.fitnessappprototype.sqlite.ExerciseContract.ExerciseEntry;
import com.unscripted.www.fitnessappprototype.sqlite.ExerciseContract.LevelEntry;

import com.unscripted.www.fitnessappprototype.sqlite.DatabaseHelper;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    // Declare ToggleButtons instances
    ToggleButton beginner, novice, advanced;

    //Declare Home, activity_workout and Profile Buttons
    Button homeBtn;
    Button workoutBtn;
    Button profileBtn;

    // Intent for conveying exercise level and body part information to workout activity
    Intent levelTypeIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this line sets customized title in Action Bar
        setTitle("                          HOME");


        /* Initialize database
        @ reference https://app.pluralsight.com/library/courses/android-database-application-sqlite-building-your-first/  **Donal
        */
        /*DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();*/

      // Only to be called when intially populating exercises table
       //CreateExercises();

        // Initialize intent
        levelTypeIntent = new Intent(MainActivity.this, WorkoutActivity.class);


        // Initialize ToggleButtons
        beginner = (ToggleButton) findViewById(R.id.toggleBeginner);
        novice = (ToggleButton) findViewById(R.id.toggleNovice);
        advanced = (ToggleButton) findViewById(R.id.toggleAdvanced);

        //Initialize Home, workout and Profile Buttons
        homeBtn= (Button) findViewById(R.id.button1);
        workoutBtn= (Button) findViewById(R.id.button2);
        profileBtn= (Button) findViewById(R.id.button3);

        /*
         @ reference https://www.youtube.com/watch?v=mPGCLKRCG-8 putExtra  **Donal
         */

        //This tells Toggle buttons how to behave when user clicks on each of them
        beginner.setChecked(true);
        beginner.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    beginner.setTextOn("Beginner");

                    novice.setChecked(false);

                    advanced.setChecked(false);
                    levelTypeIntent.putExtra("Level", "beginner");

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
                    levelTypeIntent.putExtra("Level", "novice");
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
                    levelTypeIntent.putExtra("Level", "advanced");
                }
            }
        });


        //Open activity_workout Activity when Body part Selected
        ImageButton imgBtnAbs = (ImageButton) findViewById(R.id.imgBtnAbs);
        imgBtnAbs.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO:
                // Launch Activity Two
                // Hint: use Context's startActivity() method

                // Create an intent stating which Activity you would like to
                // start
                // Intent intent = new Intent(this, ActivityTwo.class);
                //Intent intent = new Intent(android.content.Intent, ActivityTwo);

                if (beginner.isChecked()) {
                    levelTypeIntent.putExtra("Type", "Abs");
                    levelTypeIntent.putExtra("Level", "beginner");
                } else {
                    levelTypeIntent.putExtra("Type", "Abs");
                }

                // Launch the Activity using the intent
                startActivity(levelTypeIntent);

            }
        });


        ImageButton imgBtnLegs = (ImageButton) findViewById(R.id.imgBtnLegs);
        imgBtnLegs.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(beginner.isChecked()) {
                    levelTypeIntent.putExtra("Type", "Legs");
                    levelTypeIntent.putExtra("Level", "beginner");
                } else {
                    levelTypeIntent.putExtra("Type", "Legs");
                }
                startActivity(levelTypeIntent);
            }
        });

        ImageButton imgBtnArms = (ImageButton) findViewById(R.id.imgBtnArms);
        imgBtnArms.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(beginner.isChecked()) {
                    levelTypeIntent.putExtra("Type", "Arms");
                    levelTypeIntent.putExtra("Level", "beginner");
                } else {
                    levelTypeIntent.putExtra("Type", "Arms");
                }
                startActivity(levelTypeIntent);
            }
        });

        ImageButton imgBtnBack = (ImageButton) findViewById(R.id.imgBtnBack);
        imgBtnBack.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(beginner.isChecked()) {
                    levelTypeIntent.putExtra("Type", "Back");
                    levelTypeIntent.putExtra("Level", "beginner");
                } else {
                    levelTypeIntent.putExtra("Type", "Back");
                }
                startActivity(levelTypeIntent);
            }
        });


        // MENU BUTTONS
        // Home button is selected when entering page
        homeBtn.setPressed(true);
        homeBtn.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                homeBtn.setPressed(true);
                workoutBtn.setPressed(false);
                profileBtn.setPressed(false);
                return true;
            }
        });

        //Pressing this button links to WORKOUT page
        Button btnWorkout = (Button) findViewById(R.id.button2);
        btnWorkout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Default workout configuration with just level extra information
               /* levelTypeIntent.putExtra("Level", "beginner");
                startActivity(levelTypeIntent);*/
               // to account for conditions where no explicit Level extra is available
               if (beginner.isChecked()) {
                   levelTypeIntent.putExtra("Level", "beginner");
               }

               // levelTypeIntent.putExtra("Type", "null");
                startActivity(levelTypeIntent);

                // Test comment to check git branch issue

            }
        });

        //Pressing this button links to PROFILE page
        Button btnProfile = (Button) findViewById(R.id.button3);
        btnProfile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO:
                // Launch Activity Two
                // Hint: use Context's startActivity() method

                // Create an intent stating which Activity you would like to
                // start
                Intent intent = new Intent(MainActivity.this, profileTest.class);


                // Launch the Activity using the intent
                startActivity(intent);

            }
        });


    }

    private void CreateExercises(){
        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        String query = "INSERT INTO exercises ("
                + ExerciseEntry.COLUMN_TYPE + ","
                + ExerciseEntry.COLUMN_NAME + ","
                + ExerciseEntry.COLUMN_DESC + ","
                + ExerciseEntry.COLUMN_URL + ")"
                + " VALUES (\"Arms\", \"Bicep Curl\", \"Start with arm extended and gripping weight. Bend arm and raise slowly till it reaches the top. Hold for one sec and release slowly to starting position.\", \"https://www.youtube.com/watch?v=m9wfzMsVxwo\")" +
                ",(\"Arms\", \"Tricep Extension\", \"Start with arm extended and gripping weight. Bend arm and raise slowly till it reaches the top. Hold for one sec and release slowly to starting position.\", \"https://www.youtube.com/watch?v=QbuaXgugLtg\")" +
                ",(\"Arms\", \"Hammer Curl\", \"Stand straight with palms facing towards each other. Grab weights and gently lift like a normal curl. Return to start position.\", \"https://www.youtube.com/watch?v=_Z0wVadvB8A\")" +
                ",(\"Legs\", \"Squat\", \"Stand straight with feet shoulder width apart. Slowly bend at the knees and lower your upper body till thighs are parallel to the lower. Hold for one sec and return to starting position.\", \"https://www.youtube.com/watch?v=GydjeiMarLo\")" +
                ",(\"Legs\", \"Calf Raise\", \"Stand straight with feet shoulder width apart. Lift body on front toes. Hold for one sec and return to starting position.\", \"https://www.youtube.com/watch?v=Kz7vRuIhQro\")" +
                ",(\"Legs\", \"Clock Lunge\", \"Stand straight with feet shoulder width apart. Step forward and lower onto left knee and return back to starting position. Step to the left and lower onto the knee and return to start position. Step backwards and lower onto knee and return to start. Repeat with right leg.\", \"https://www.youtube.com/watch?v=VVgTNlPupUU\")" +
                ",(\"Back\", \"Renegade Row\", \"This can be done with or without weights. Get into a push up position. Gently raise one arm up and rotate torso in the same direction. Elbow should bend and then return to start. Repeat with the other side.\", \"https://www.youtube.com/watch?v=PPYsPNPMnM4\")" +
                ",(\"Abs\", \"Crunch\", \"Lie on a flat surface. Place hands behind head. Gently lift shoulders only up off the ground and hold at the highest position and lower back to starting position.\", \"https://www.youtube.com/watch?v=zZGZxU2jsOw\")" +
                ",(\"Abs\", \"Reverse Crunch\", \"Lie on a flat surface with back flat and hands at sides. Keep knees together, bent and gently raise off the ground and towards the belly. Continue till thighs are perpendicular to the ground and return to starting position.\", \"https://www.youtube.com/watch?v=uq4vcnTNTR8\")" +
                ",(\"Abs\", \"Weighted Leg Drop\", \"Lie on a flat surface with dumbell in hand. Raise both feet and arms perpendicular to the floor. Lower gently. to resting position and return to start.\", \"https://www.youtube.com/watch?v=klFCxOfL3w0\")" +
                ",(\"Shoulders\", \"Lateral Raise\", \"Stand straight with feet shoulder width apart. Hold arms with elbows slightly bent. Raise arms towards the sides until slightly above shoulder level. Hold and return to start position.\", \"https://www.youtube.com/watch?v=c3FkUjXxWmM\");";



        String levelQuery = "INSERT INTO levels ("
                + LevelEntry.COLUMN_LEVEL + ","
                + LevelEntry.COLUMN_REPS + ")"
                + " VALUES (\"beginner\", \"8\")"
                + ",(\"novice\", \"14\")"
                + ",(\"advanced\", \"22\");";


        db.execSQL(query);
        db.execSQL(levelQuery);



        // Alternative method for inserting records into tables
        /*ContentValues values = new ContentValues();
        values.put(ExerciseEntry.COLUMN_TYPE, "Wings");
        values.put(ExerciseEntry.COLUMN_NAME, "Flapper");
        values.put(ExerciseEntry.COLUMN_DESC, "Make like a boid and sing");
        values.put(ExerciseEntry.COLUMN_URL, "www.youtube.com");
        long randomExercise = db.insert(ExerciseEntry.TABLE_NAME, null, values);*/

    }



}

