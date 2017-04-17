package com.unscripted.www.fitnessappprototype.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.unscripted.www.fitnessappprototype.sqlite.ExerciseContract.ExerciseEntry;
import com.unscripted.www.fitnessappprototype.sqlite.ExerciseContract.ExerciseTrackerEntry;
import com.unscripted.www.fitnessappprototype.sqlite.ExerciseContract.LevelEntry;
import com.unscripted.www.fitnessappprototype.sqlite.ExerciseContract.WorkoutEntry;

/**
 * Created by dONY on 12/04/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ufit.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_EXERCISE_CREATE =
            "CREATE TABLE " + ExerciseEntry.TABLE_NAME + " (" +
                    ExerciseEntry._ID + " INTEGER PRIMARY KEY, " +
                    ExerciseEntry.COLUMN_TYPE + " TEXT, " +
                    ExerciseEntry.COLUMN_NAME + " TEXT, " +
                    ExerciseEntry.COLUMN_DESC + " TEXT, " +
                    ExerciseEntry.COLUMN_URL + " TEXT " + ")";

    private static final String TABLE_LEVEL_CREATE =
            "CREATE TABLE " + LevelEntry.TABLE_NAME + " (" +
                    LevelEntry._ID + " INTEGER PRIMARY KEY, " +
                    LevelEntry.COLUMN_LEVEL + " TEXT, " +
                    LevelEntry.COLUMN_REPS + " INTEGER " + ")";


    private static final String TABLE_WORKOUT_CREATE =
            "CREATE TABLE " + WorkoutEntry.TABLE_NAME + " (" +
                    WorkoutEntry._ID  + " INTEGER PRIMARY KEY, " +
                    WorkoutEntry.COLUMN_TYPE + " TEXT, " +
                    WorkoutEntry.COLUMN_NAME + " TEXT, " +
                    WorkoutEntry.COLUMN_DESC + " TEXT, " +
                    WorkoutEntry.COLUMN_REPS + " INT, " +
                    WorkoutEntry.COLUMN_URL + " TEXT " + ")";

    private static final String TABLE_EXERCISETRACKER_CREATE =
            // Previous Table create query which caused app to crash
          /*  "CREATE TABLE " + ExerciseTrackerEntry.TABLE_NAME + " (" +
                    ExerciseTrackerEntry._ID + " INTEGER PRIMARY KEY, " +
                    " FOREIGN KEY (" + ExerciseTrackerEntry._ID + ") REFERENCES " +
                    ExerciseEntry.TABLE_NAME + "(" + ExerciseEntry._ID + "), " +
                    ExerciseTrackerEntry.COLUMN_TOTALREPS + " INTEGER " + ")";*/

          "CREATE TABLE " + ExerciseTrackerEntry.TABLE_NAME +  " (" +
                  ExerciseTrackerEntry._ID + " INTEGER PRIMARY KEY, " +
                  ExerciseTrackerEntry.COLUMN_TOTALREPS + " INTEGER, " +
                  " FOREIGN KEY(" + ExerciseTrackerEntry._ID + ") REFERENCES " +
                  ExerciseEntry.TABLE_NAME + "(" + ExerciseEntry._ID + ") " + ")";




    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_EXERCISE_CREATE);
        db.execSQL(TABLE_LEVEL_CREATE);
        db.execSQL(TABLE_WORKOUT_CREATE);
        db.execSQL(TABLE_EXERCISETRACKER_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ExerciseEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + LevelEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + WorkoutEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ExerciseTrackerEntry.TABLE_NAME);
        onCreate(db);

    }
}
