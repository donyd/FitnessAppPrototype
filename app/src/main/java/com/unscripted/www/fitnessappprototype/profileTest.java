package com.unscripted.www.fitnessappprototype;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.joooonho.SelectableRoundedImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;

/*
  * Reference @http://stackoverflow.com/questions/2416844/how-to-set-custom-title-bar-textview-value-dynamically-in-android **Karina
  * @http://viralpatel.net/blogs/pick-image-from-galary-android-app/ **Karina
  * @http://stackoverflow.com/questions/5309190/android-pick-images-from-gallery **Karina
  * @https://github.com/jaisonfdo/ImageAttachment **Karina
  * @http://droidmentor.com/pick-image-from-gallery-or-camera/  **Karina
  * @http://android-steps.blogspot.ie/2015/08/profile-page-with-first-time-login-check.html **Karina
  */
public class profileTest extends AppCompatActivity implements Imageutils.ImageAttachmentListener{

    //For Image Attachment
    ImageView iv_attachment;
    private Bitmap bitmap;
    private String file_name;
    Imageutils imageutils;

    //TO SAVE THE TEXT ENTERED BY USER
    EditText ed1,ed2,ed3;
    Button b1;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    public static final String Dob = "DobKey";
    public static final String Email = "emailKey";
    SharedPreferences sharedpreferences;

    //Declare Home, Workout and Profile Buttons
    Button homeBtn;
    Button workoutBtn;
    Button profileBtn;

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

        //TO SAVE TEXT ENTERED BY USER
        //NOT WORKING AS DESIRED
        ed1=(EditText)findViewById(R.id.editText);
        ed2=(EditText)findViewById(R.id.editText2);
        ed3=(EditText)findViewById(R.id.editText3);

        b1=(Button)findViewById(R.id.buttonOK);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n  = ed1.getText().toString();
                String ph  = ed2.getText().toString();
                String e  = ed3.getText().toString();

                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(Name, n);
                editor.putString(Dob, ph);
                editor.putString(Email, e);
                editor.commit();
                Toast.makeText(profileTest.this,"Thanks",Toast.LENGTH_LONG).show();

            }

        });

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

        //To upload Image
        imageutils =new Imageutils(this);

        iv_attachment=(ImageView)findViewById(R.id.imgView);

        iv_attachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageutils.imagepicker(1);
            }
        });
    }

    //This code grabs the image chosen by the user and shows it in the screen
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        imageutils.onActivityResult(requestCode, resultCode, data);

    }
        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            imageutils.request_permission_result(requestCode, permissions, grantResults);
        }

        public void image_attachment(int from, String filename, Bitmap file, Uri uri) {
            this.bitmap=file;
            this.file_name=filename;
            iv_attachment.setImageBitmap(file);

            String path =  Environment.getExternalStorageDirectory() + File.separator + "ImageAttach" + File.separator;
            imageutils.createImage(file,filename,path,false);

        }
}
