package com.example.android.harmonyproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
//import static maes.tech.intentanim.CustomIntent.customType;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button nextpagebutton;

    TextView apptitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        nextpagebutton=findViewById(R.id.id_button);
        apptitle=findViewById(R.id.id_title);

        apptitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.blink_anim);
                apptitle.startAnimation(animation);
            }
        });

        nextpagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation1 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.rotate);
                nextpagebutton.startAnimation(animation1);

                Animation animation2 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fadeout);
                apptitle.startAnimation(animation2);

                Intent intentToLoad = new Intent(MainActivity.this, Piano.class);
                startActivity(intentToLoad);
                //CustomIntent.customType(this, "fadein-to-fadeout");
            }
        });


    }

}
