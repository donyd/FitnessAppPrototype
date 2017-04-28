package com.unscripted.www.fitnessappprototype;

/**
 * References: https://www.sitepoint.com/using-the-youtube-api-to-embed-video-in-an-android-app/  **Karina
 */

public final class Config {

        public static final String TOPIC_GLOBAL = "global";
        // broadcast receiver intent filters
        public static final String REGISTRATION_COMPLETE = "registrationComplete";
        public static final String PUSH_NOTIFICATION = "pushNotification";

        // id to handle the notification in the notification tray
        public static final int NOTIFICATION_ID = 100;
        public static final int NOTIFICATION_ID_BIG_IMAGE = 101;
        public static final String SHARED_PREF = "ah_firebase";

    private Config() {
    }

    public static final String YOUTUBE_API_KEY = "AIzaSyD_V6GCDvchMvlf82kcl0b3LJvVu10NHZs";

}