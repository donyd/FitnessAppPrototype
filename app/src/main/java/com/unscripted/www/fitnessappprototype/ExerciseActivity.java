package com.unscripted.www.fitnessappprototype;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * References: https://www.sitepoint.com/using-the-youtube-api-to-embed-video-in-an-android-app/ **Karina
 */

public class ExerciseActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

        private static final int RECOVERY_REQUEST = 1;
        private YouTubePlayerView youTubeView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

            youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
            youTubeView.initialize(Config.YOUTUBE_API_KEY, this);
        }

        @Override
        public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
            if (!wasRestored) {
                player.cueVideo("m9wfzMsVxwo"); // Plays https://www.youtube.com/watch?v=m9wfzMsVxwo
            }
        }

        @Override
        public void onInitializationFailure(Provider provider, YouTubeInitializationResult errorReason) {
            if (errorReason.isUserRecoverableError()) {
                errorReason.getErrorDialog(this, RECOVERY_REQUEST).show();
            } else {
                String error = String.format(getString(R.string.player_error), errorReason.toString());
                Toast.makeText(this, error, Toast.LENGTH_LONG).show();
            }
        }

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




