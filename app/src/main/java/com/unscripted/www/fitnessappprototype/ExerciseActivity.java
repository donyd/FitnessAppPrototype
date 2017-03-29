package com.unscripted.www.fitnessappprototype;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ExerciseActivity extends AppCompatActivity {
    // Reference to views
    ImageView exerciseImageHolder;
    Button exerciseButton;

   /* static int mImages[] = new int[]{
            R.drawable.lungestart,
            R.drawable.lungeend
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        exerciseImageHolder = (ImageView) findViewById(R.id.exerciseImage);
        exerciseButton = (Button) findViewById(R.id.btnExercise);

        exerciseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ((TransitionDrawable) exerciseImageHolder.getDrawable()).startTransition(1000);
            }
        });


        /**
         * Animation trial
         * Revision 1
         * 29/03/2017
         *
         * @reference http://stackoverflow.com/questions/26060182/animated-transition-between-two-imageview
         *
         */



       /* TransitionDrawable exerImage = new TransitionDrawable (new Drawable[]{
           getResources().getDrawables(mImages[0]),
                getResources().getDrawables(mImages[1])
        });*/

    }


}
