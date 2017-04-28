package com.unscripted.www.fitnessappprototype;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.unscripted.www.fitnessappprototype.sqlite.DatabaseHelper;
import com.unscripted.www.fitnessappprototype.sqlite.ExerciseContract.ExerciseEntry;
import com.unscripted.www.fitnessappprototype.sqlite.ExerciseContract.LevelEntry;

import java.util.ArrayList;
import java.util.Arrays;

import static android.app.PendingIntent.getActivity;

public class WorkoutActivity extends AppCompatActivity {






    public ArrayList<String> ExerciseLst = new ArrayList();
    String[] ExerciseArr = new String[4];
    public String[] UrlArr = new String[4];
    public String[] LevelArr = new String[4];
    public ArrayList<String> urlLst = new ArrayList();
    public ArrayList<String> levelLst = new ArrayList();

    Cursor exerciseSet;

    ListView mListView;

    /*
  @ reference https://app.pluralsight.com/player?course=android-database-application-sqlite-building-your-first&author=simone-alessandria&name=android-database-application-sqlite-building-your-first-m3&clip=6&mode=live
   */
    private ArrayList<String> readData(String query){
        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
//        String[] projection = {
//                ExerciseEntry.COLUMN_TYPE,
//                ExerciseEntry.COLUMN_NAME,
//                ExerciseEntry.COLUMN_URL,
//        };
//
//        String selection = ExerciseEntry.COLUMN_TYPE + " = ? ";
//        String[] selectionArgs = {"Abs"};
//        Cursor c = db.query(ExerciseEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);


        Log.e("exerciseQuery", query);

        Cursor exerciseSet = db.rawQuery(query, null);

//        urlLst = getUrl(exerciseSet);
//        Log.d("Urls", Arrays.toString(urlLst));


        // verifying query return
        int i = exerciseSet.getCount();
        Log.d("Record Count", String.valueOf(i));


//        String rowContent = "";
//        while(c.moveToNext()){
//            for(i = 0; i<=2; i++){
//                rowContent += c.getString(i) + " - ";
//            }
//            Log.i("Row " + String.valueOf(c.getPosition()), rowContent);
//            rowContent = "";
//        }

        while(exerciseSet.moveToNext()){
            ExerciseLst.add(exerciseSet.getString(0));
            urlLst.add(exerciseSet.getString(1));
            levelLst.add(exerciseSet.getString(2));
                     // Log.d("Urls", );
        }

        exerciseSet.close();
        return ExerciseLst;


    }

//    private String[] getUrl(Cursor cursor) {
//        String[] UrlArr = new String[1];
//        for(int i = 0; i < 4; i++) {
//            while (cursor.moveToNext()) {
//                UrlArr[i] = cursor.getString(1);
//
//            }
//        }
//        return UrlArr;
//    }




    //Declare activity_workout button
    Button workoutBtn;
    Button startBtn;

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
        setTitle("             WORKOUT");
        //this line creates back button to go BACKWARDS
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        /*
         @ reference https://www.youtube.com/watch?v=mPGCLKRCG-8 getExtra
         */

        String selectExercises;
        String exerciseType = getIntent().getStringExtra("Type");
        String exerciseLevel = getIntent().getStringExtra("Level");

        //Log.d("Intent Type", exerciseType);
        Log.d("Intent Level", exerciseLevel);

        /*
        @ http://www.androidhive.info/2013/09/android-sqlite-database-with-multiple-tables/
         */
        if(exerciseType == null) {
            selectExercises = "SELECT " + "ex." + ExerciseEntry.COLUMN_NAME + ", ex." + ExerciseEntry.COLUMN_URL + ", lvl."
                    + LevelEntry.COLUMN_REPS + " FROM " + ExerciseEntry.TABLE_NAME + " AS ex, " + LevelEntry.TABLE_NAME + " AS lvl"
                    + " WHERE " + LevelEntry.COLUMN_LEVEL + " = " + "\"" + exerciseLevel + "\""
                    + " ORDER BY RANDOM() LIMIT 0, 4";
        } else
        {
            selectExercises = "SELECT " + "ex." + ExerciseEntry.COLUMN_NAME + ", ex." + ExerciseEntry.COLUMN_URL + ", lvl."
                    + LevelEntry.COLUMN_REPS + " FROM " + ExerciseEntry.TABLE_NAME + " AS ex, " + LevelEntry.TABLE_NAME + " AS lvl"
                    + " WHERE " + LevelEntry.COLUMN_LEVEL + " = " + "\"" + exerciseLevel + "\""
                    + " AND " + ExerciseEntry.COLUMN_TYPE + " = " + "\"" + exerciseType + "\""
                    + " ORDER BY RANDOM() LIMIT 0, 4";

        }




        /** START OF ListView setup and details
         * List view to present a receptacle for exercises
         * which will be displayed based on yet to be finalised logic
         * and also on the basis of whether user has selected a bias i.e
         * Arms, Legs, etcetera
         *
         *  @ reference https://developer.android.com/guide/topics/ui/declaring-layout.html#AdapterViews         *
         */
        mListView = (ListView) findViewById(R.id.workout_container);



        ExerciseLst = readData(selectExercises);

        /* @reference http://stackoverflow.com/questions/5374311/convert-arrayliststring-to-string-array */
        UrlArr = urlLst.toArray(UrlArr);
        Log.d("Urls", Arrays.toString(UrlArr));
        LevelArr = levelLst.toArray(LevelArr);
        Log.d("Levels", Arrays.toString(LevelArr));


        ExerciseArr = ExerciseLst.toArray(ExerciseArr);
        Log.d("exercise", Arrays.toString(ExerciseArr));

        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, ExerciseLst);

        mListView.setAdapter(mAdapter);
        // END OF ListView setup and details


        // Get references to ListView items and set onclick listeners for early prototype
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//
//
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(WorkoutActivity.this, ExerciseActivity.class);
//                startActivity(intent);
//
//            }
//        });


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



        startBtn = (Button) findViewById(R.id.btnStart);
        startBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent exerciseDetails = new Intent(WorkoutActivity.this, ExerciseActivity.class);
                exerciseDetails.putExtra("exerciseName", ExerciseArr);
                exerciseDetails.putExtra("exerciseLevel", LevelArr);
                exerciseDetails.putExtra("exerciseUrl", UrlArr);

                startActivity(exerciseDetails);
            }
        });


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
                Intent intent = new Intent(WorkoutActivity.this, profileTest.class);

                // Launch the Activity using the intent
                startActivity(intent);

            }
        });

    }



}