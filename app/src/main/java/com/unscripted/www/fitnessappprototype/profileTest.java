package com.unscripted.www.fitnessappprototype;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;
import com.joooonho.SelectableRoundedImageView;

/*
  * Reference @http://stackoverflow.com/questions/2416844/how-to-set-custom-title-bar-textview-value-dynamically-in-android **Karina
  * @http://viralpatel.net/blogs/pick-image-from-galary-android-app/ **Karina
  * @http://stackoverflow.com/questions/5309190/android-pick-images-from-gallery **Karina
  * @http://androidarena.co.in/how-to-pick-image-from-gallery-example/ **Karina
  */
public class profileTest extends AppCompatActivity {

    //Declare Home, Workout and Profile Buttons & RESULT_LOAD_IMAGE constant
    Button homeBtn;
    Button workoutBtn;
    Button profileBtn;
    private static final int RESULT_LOAD_IMAGE = 1;

    // Creates a back button to go BACKWARDS
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_test);

        //this line sets customized title in Action Bar
        setTitle("              PROFILE");

        //this line creates back button to go BACKWARDS
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Initialize Home, workout and Profile Buttons
        homeBtn= (Button) findViewById(R.id.button1);
        workoutBtn= (Button) findViewById(R.id.button2);
        profileBtn= (Button) findViewById(R.id.button3);

            // *****MENU BUTTONS******
            // PROFILE button is selected when entering page
            profileBtn.setPressed(true);
            profileBtn.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    homeBtn.setPressed(false);
                    workoutBtn.setPressed(false);
                    profileBtn.setPressed(true);
                    return true;
                }
            });

            //Pressing this button links to WORKOUT page
            Button btnWorkout = (Button) findViewById(R.id.button2);
            btnWorkout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO:
                    // Launch Activity Two
                    // Hint: use Context's startActivity() method

                    // Create an intent stating which Activity you would like to
                    // start
                    // Intent intent = new Intent(this, ActivityTwo.class);
                    //Intent intent = new Intent(android.content.Intent, ActivityTwo);
                    Intent intent = new Intent(profileTest.this, WorkoutActivity.class);

                    // Launch the Activity using the intent
                    startActivity(intent);

                }
            });

            //Pressing this button links to HOME page
            Button btnHome = (Button) findViewById(R.id.button1);
            btnHome.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO:
                    // Launch Activity Two
                    // Hint: use Context's startActivity() method

                    // Create an intent stating which Activity you would like to
                    // start
                    Intent intent = new Intent(profileTest.this, MainActivity.class);


                    // Launch the Activity using the intent
                    startActivity(intent);

                }
            });

            //Pressing this button lets user select a picture from gallery to insert in their profile
            Button btnLoadImage = (Button) findViewById(R.id.buttonLoadPicture);
            btnLoadImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO:
                    // Launch Activity Two
                    //
                    startActivityForResult(
                            new Intent(
                                    Intent.ACTION_PICK,
                                    android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI
                            ),
                            RESULT_LOAD_IMAGE
                    );
                }
            });
    }

    //This code grabs the image chosen by the user and shows it in the screen
    //>>>>STILL NOT WORKING AS DESIRED
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String img_Decodable_Str = cursor.getString(columnIndex);
                cursor.close();
                SelectableRoundedImageView imgView = (SelectableRoundedImageView) findViewById(R.id.imgView);
                // Set the Image in ImageView after decoding the String
                imgView.setImageBitmap(BitmapFactory
                        .decodeFile(img_Decodable_Str));

            } else {
                Toast.makeText(this, "Hey pick your image first",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went embrassing", Toast.LENGTH_LONG)
                    .show();
        }

    }

/** >>>>>>THIS IS AN ALTERNATIVE CODE TO THE ONE ABOVE THAT IS ALSO NOT WORKING AS DESIRED YET
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Detects request codes
        if(requestCode==RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
    **/

}
