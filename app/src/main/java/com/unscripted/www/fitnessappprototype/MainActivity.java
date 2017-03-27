package com.unscripted.www.fitnessappprototype;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    // Declare ToggleButtons instances
    ToggleButton beginner, novice, advanced;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //********
        Button ok = (Button) findViewById(R.id.btnAlert);
        ok.setOnClickListener(new View.OnClickListener(){
            @Override
                public void onClick(View v){
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Warning");
                alert
                        .setIcon(R.mipmap.ic_launcher)//check
                        .setMessage("Message of Dialog")
                        .setCancelable(false)
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener(){


                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(),"Cancel option", Toast.LENGTH_SHORT).show();
                            }
                        })


                        .setPositiveButton("Ok", new DialogInterface.OnClickListener(){


                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(),"Ok option", Toast.LENGTH_SHORT).show();
                            }
                        });
                AlertDialog alertDialog = alert.create();
                alertDialog.show();


            }


        });
        //********



        // Initialize ToggleButtons
        beginner = (ToggleButton) findViewById(R.id.toggleBeginner);
        novice = (ToggleButton) findViewById(R.id.toggleNovice);
        advanced = (ToggleButton) findViewById(R.id.toggleAdvanced);

        beginner.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    beginner.setTextOn("Beginner");

                    novice.setChecked(false);

                    advanced.setChecked(false);
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
                }
            }
        });



        Button launchActivityTwoButton = (Button) findViewById(R.id.button);
        launchActivityTwoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO:
                // Launch Activity Two
                // Hint: use Context's startActivity() method

                // Create an intent stating which Activity you would like to
                // start
                // Intent intent = new Intent(this, ActivityTwo.class);
                //Intent intent = new Intent(android.content.Intent, ActivityTwo);
                Intent intent = new Intent(MainActivity.this, Workout.class);

                // Launch the Activity using the intent
                startActivity(intent);

            }
        });
    }
}
