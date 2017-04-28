package com.unscripted.www.fitnessappprototype;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Arrays;
import java.util.List;

/**
 * References: https://www.sitepoint.com/using-the-youtube-api-to-embed-video-in-an-android-app/ **Karina
 */

public class ExerciseActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {


    // arrays to retrieve intent information
    public String[] ExerciseArr = new String[4];
    public String[] LevelArr = new String[4];
    public String[] UrlArr = new String[4];
    public int counter = 0;
    public String vidId = "";



    public String showExercise(String[] arr){
        vidId = extractVidId(arr[counter]);
        counter++;
        return vidId;
    }

    /* @reference http://stackoverflow.com/questions/14316487/java-getting-a-substring-from-a-string-starting-after-a-particular-character */
    public String extractVidId(String url){
        return url.substring(url.lastIndexOf("=") + 1);
    }

        //TO PLAY YOUTUBE VIDEOS
        private static final int RECOVERY_REQUEST = 1;
        private YouTubePlayerView youTubeView;

        //TO PLACE ADDS IN OUR APP
        private static final String TAG = "MainActivity";
        private AdView mAdView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

            // Retrieve exercise info from WorkoutActivity
            ExerciseArr = getIntent().getStringArrayExtra("exerciseName");
            LevelArr = getIntent().getStringArrayExtra("exerciseLevel");
            UrlArr = getIntent().getStringArrayExtra("exerciseUrl");

            // Log messages to check intents
//            Log.d("Exercise", Arrays.toString(ExerciseArr));
//            Log.d("Level", Arrays.toString(LevelArr));
//            Log.d("Url", Arrays.toString(UrlArr));

            // simple button onClick causes activity to crash, unable to ascertain
//            Button btnNext = (Button) findViewById(R.id.btnStart);
//            btnNext.setOnClickListener(new View.OnClickListener(){
//
//                @Override
//                public void onClick(View view) {
//                    onInitializationSuccess(null, null, true);
//
//                }
//            });

            TextView txtVwRepCount = (TextView) findViewById(R.id.txtVwRepCount);
            txtVwRepCount.setText(LevelArr[0]);


//


            //TO PLACE ADDS IN OUR APP
            mAdView = (AdView) findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);


            //INITIALIZE YOUTUBE VIDEO LINK
            youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
            youTubeView.initialize(Config.YOUTUBE_API_KEY, this);
        }

        //PLAY THE FOLLOWING VIDEO
        @Override
        public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
            if (!wasRestored) {
                //player.cueVideo("m9wfzMsVxwo"); // Plays https://www.youtube.com/watch?v=m9wfzMsVxwo
                player.cueVideo(showExercise(UrlArr));
            }
        }

        //IF THERE IS AN ERROR WITH THE VIDEO...
        @Override
        public void onInitializationFailure(Provider provider, YouTubeInitializationResult errorReason) {
            if (errorReason.isUserRecoverableError()) {
                errorReason.getErrorDialog(this, RECOVERY_REQUEST).show();
            } else {
                String error = String.format(getString(R.string.player_error), errorReason.toString());
                Toast.makeText(this, error, Toast.LENGTH_LONG).show();
            }
        }

        //RECOVERY REQUEST FOR YOUTUBE VIDEO
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == RECOVERY_REQUEST) {
                // Retry initialization if user performed a recovery action
                getYouTubePlayerProvider().initialize(Config.YOUTUBE_API_KEY, this);
            }
        }

        protected Provider getYouTubePlayerProvider() {
            return youTubeView;
        }
    }




