package com.unscripted.www.fitnessappprototype.sqlite;

import android.provider.BaseColumns;

/**
 * Created by dONY on 11/04/2017.
 *
 *  @ reference https://app.pluralsight.com/library/courses/android-database-application-sqlite-building-your-first/table-of-contents       *
 */


public final class ExerciseContract {

    public static final class ExerciseEntry implements BaseColumns {
        // Table name
        public static final String TABLE_NAME = "exercises";

        // Column names
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESC = "description";
        public static final String COLUMN_URL = "url";
    }

    public static final class LevelEntry implements BaseColumns {
        // Table name
        public static final String TABLE_NAME = "levels";

        // Column names
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_LEVEL = "level";
        public static final String COLUMN_REPS = "reps";
    }

    public static final class ExerciseTrackerEntry implements BaseColumns {
        // Table name
        public static final String TABLE_NAME = "tracker";

        // Column names
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_TOTALREPS = "totalReps";
    }

    public static final class WorkoutEntry implements BaseColumns {
        // Table name
        public static final String TABLE_NAME = "workout";

        // Column names
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESC = "description";
        public static final String COLUMN_REPS = "reps";
        public static final String COLUMN_URL = "url";

    }
}
