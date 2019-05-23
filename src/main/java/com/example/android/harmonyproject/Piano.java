package com.example.android.harmonyproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Piano extends AppCompatActivity {

    JSONObject jsonObject;

    Spinner chooseakey;

    ArrayList<String> list1;
    int pos1;

    Button deletebutton;
    Button clearbutton;


    ArrayList<Integer> wavfiles = new ArrayList<>();

        int r;
        ArrayList<Integer> melodyarray = new ArrayList<>();
        ArrayList<Integer> spacesinscalearray = new ArrayList<>();
        int[][] spacesinchordarray = new int[7][3];
        ArrayList<Button> arrayforcoloringkeys= new ArrayList<>();



        final int lowg = 0;
        final int lowgsharpaflat = 1;
        final int lowa = 2;
        final int lowasharpbflat = 3;
        final int lowb = 4;
        final int c = 5;
        final int csharpdflat = 6;
        final int d = 7;
        final int dsharpeflat = 8;
        final int e = 9;
        final int f = 10;
        final int fsharpgflat = 11;
        final int g = 12;
        final int gsharpaflat = 13;
        final int a = 14;
        final int asharpbflat = 15;
        final int b = 16;
        final int highc = 17;
        final int highcsharpdflat = 18;
        final int highd = 19;
        final int highdsharpeflat = 20;
        final int highe = 21;
        final int highf = 22;
        final int highfsharpgflat = 23;
        final int highg = 24;
        final int highgsharpaflat = 25;
        final int higha=26;
        final int highasharpbflat=27;
        final int highb=28;
        final int highhighc=29;
        final int highhighcsharpdflat=30;
        final int highhighd=31;
        final int highhighdsharpeflat=32;
        final int highhighe=33;
        final int highhighf=34;
        final int highhighfsharpgflat=35;
        final int highhighg=36;
        final int highhighgsharpaflat=37;




    String test = "";
        Handler handler;

        TextView melodysofar;

        Button cbutton;
        Button csharpdflatbutton;
        Button dbutton;
        Button dsharpeflatbutton;
        Button ebutton;
        Button fbutton;
        Button fsharpgflatbutton;
        Button gbutton;
        Button gsharpaflatbutton;
        Button abutton;
        Button asharpbflatbutton;
        Button bbutton;
        Button highcbutton;
        Button highcsharpdflatbutton;
        Button highdbutton;
        Button highdsharpeflatbutton;
        Button highebutton;
        Button highfbutton;
        Button highfsharpgflatbutton;
        Button highgbutton;
        Button highgsharpaflatbutton;

        Button startbutton;


        MediaPlayer sopranoMediaPlayer;
        MediaPlayer altoMediaPlayer;
        MediaPlayer tenorMediaPlayer;
        MediaPlayer bassMediaPlayer;

        MediaPlayer melodyplayer;


        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            setContentView(R.layout.activity_piano);
            Log.d("filetesting1","In create");



            handler = new Handler();

            clearbutton=findViewById(R.id.id_clear);
            deletebutton=findViewById(R.id.id_delete);

            chooseakey=findViewById(R.id.id_chooseakey);
            list1 = new ArrayList<>();


            list1.add("C MAJOR");
            list1.add("C# MAJOR");
            list1.add("D MAJOR");
            list1.add("Eb MAJOR");
            list1.add("E MAJOR");
            list1.add("F MAJOR");
            list1.add("F# MAJOR");
            list1.add("G MAJOR");
            list1.add("Ab MAJOR");
            list1.add("A MAJOR");
            list1.add("Bb MAJOR");
            list1.add("B MAJOR");



            ArrayAdapter<String> spinneradapter= new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,list1);
            chooseakey.setAdapter(spinneradapter);




            melodysofar = findViewById(R.id.id_melodysofar);
            cbutton = findViewById(R.id.id_cbutton);
            csharpdflatbutton = findViewById(R.id.id_csharpdflat);
            dbutton = findViewById(R.id.id_dbutton);
            dsharpeflatbutton = findViewById(R.id.id_dsharpeflat);
            ebutton = findViewById(R.id.id_ebutton);
            fbutton = findViewById(R.id.id_fbutton);
            fsharpgflatbutton = findViewById(R.id.id_fsharpgflat);
            gbutton = findViewById(R.id.id_gbutton);
            gsharpaflatbutton = findViewById(R.id.id_gsharpaflat);
            abutton = findViewById(R.id.id_abutton);
            asharpbflatbutton = findViewById(R.id.id_asharpbflat);
            bbutton = findViewById(R.id.id_bbutton);
            highcbutton = findViewById(R.id.id_highcbutton);
            highcsharpdflatbutton = findViewById(R.id.id_highcsharpdflat);
            highdbutton = findViewById(R.id.id_highdbutton);
            highdsharpeflatbutton = findViewById(R.id.id_highdsharpeflat);
            highebutton = findViewById(R.id.id_highebutton);
            highfbutton = findViewById(R.id.id_highfbutton);
            highfsharpgflatbutton = findViewById(R.id.id_highfsharpgflat);
            highgbutton = findViewById(R.id.id_highgbutton);
            highgsharpaflatbutton = findViewById(R.id.id_highgsharpaflat);

            startbutton = findViewById(R.id.id_startbutton);


            arrayforcoloringkeys.add(cbutton);
            arrayforcoloringkeys.add(csharpdflatbutton);
            arrayforcoloringkeys.add(dbutton);
            arrayforcoloringkeys.add(dsharpeflatbutton);
            arrayforcoloringkeys.add(ebutton);
            arrayforcoloringkeys.add(fbutton);
            arrayforcoloringkeys.add(fsharpgflatbutton);
            arrayforcoloringkeys.add(gbutton);
            arrayforcoloringkeys.add(gsharpaflatbutton);
            arrayforcoloringkeys.add(abutton);
            arrayforcoloringkeys.add(asharpbflatbutton);
            arrayforcoloringkeys.add(bbutton);
            arrayforcoloringkeys.add(highcbutton);
            arrayforcoloringkeys.add(highcsharpdflatbutton);
            arrayforcoloringkeys.add(highdbutton);
            arrayforcoloringkeys.add(highdsharpeflatbutton);
            arrayforcoloringkeys.add(highebutton);
            arrayforcoloringkeys.add(highfbutton);
            arrayforcoloringkeys.add(highfsharpgflatbutton);
            arrayforcoloringkeys.add(highgbutton);
            arrayforcoloringkeys.add(highgsharpaflatbutton);




            wavfiles.add(0,R.raw.lowgnote);
            wavfiles.add(1,R.raw.lowgsharpaflat);
            wavfiles.add(2,lowa);
            wavfiles.add(3,lowasharpbflat);
            wavfiles.add(4,R.raw.lowbnote);
            wavfiles.add(5,R.raw.cnote);
            wavfiles.add(6,R.raw.csharpdflat);
            wavfiles.add(7,R.raw.dnote);
            wavfiles.add(8,R.raw.dsharpeflat);
            wavfiles.add(9,R.raw.enote);
            wavfiles.add(10,R.raw.fnote);
            wavfiles.add(11,R.raw.fsharpgflat);
            wavfiles.add(12,R.raw.gnote);
            wavfiles.add(13,R.raw.gsharpaflat);
            wavfiles.add(14,R.raw.anote);
            wavfiles.add(15,R.raw.asharpbflat);
            wavfiles.add(16,R.raw.bnote);
            wavfiles.add(17,R.raw.highcnote);
            wavfiles.add(18,R.raw.highcsharpdflat);
            wavfiles.add(19,R.raw.highdnote);
            wavfiles.add(20,R.raw.highdsharpeflat);
            wavfiles.add(21,R.raw.highenote);
            wavfiles.add(22,R.raw.highfnote);
            wavfiles.add(23,R.raw.highfsharpgflat);
            wavfiles.add(24,R.raw.highgnote);
            wavfiles.add(25,R.raw.highgsharpaflat);
            wavfiles.add(26,R.raw.highanote);
            wavfiles.add(27,R.raw.highasharpbflat);
            wavfiles.add(28,R.raw.highbnote);
            wavfiles.add(29,R.raw.highhighc);
            wavfiles.add(30,R.raw.highhighcsharpdflat);
            wavfiles.add(31,R.raw.highhighd);
            wavfiles.add(32,R.raw.highhighdsharpeflat);
            wavfiles.add(33,R.raw.highhighenote);
            wavfiles.add(34,R.raw.highhighfnote);
            wavfiles.add(35,R.raw.highhighfsharpgflat);
            wavfiles.add(36,R.raw.highhighgnote);
            wavfiles.add(37,R.raw.highhighgsharpaflat);

            spacesinscalearray.add(2);
            spacesinscalearray.add(2);
            spacesinscalearray.add(1);
            spacesinscalearray.add(2);
            spacesinscalearray.add(2);
            spacesinscalearray.add(2);
            spacesinscalearray.add(1);

            spacesinchordarray[0][0] = 4;
            spacesinchordarray[0][1] = 3;
            spacesinchordarray[0][2] = 5;

            spacesinchordarray[1][0] = 3;
            spacesinchordarray[1][1] = 4;
            spacesinchordarray[1][2] = 5;

            spacesinchordarray[2][0] = 3;
            spacesinchordarray[2][1] = 4;
            spacesinchordarray[2][2] = 5;

            spacesinchordarray[3][0] = 4;
            spacesinchordarray[3][1] = 3;
            spacesinchordarray[3][2] = 5;

            spacesinchordarray[4][0] = 4;
            spacesinchordarray[4][1] = 3;
            spacesinchordarray[4][2] = 5;

            spacesinchordarray[5][0] = 3;
            spacesinchordarray[5][1] = 4;
            spacesinchordarray[5][2] = 5;

            spacesinchordarray[6][0] = 4;
            spacesinchordarray[6][1] = 3;
            spacesinchordarray[6][2] = 5;


            final PlaySopranoNote playSopranoNote = new PlaySopranoNote();
            final PlayAltoNote playAltoNote = new PlayAltoNote();
            final PlayTenorNote playTenorNote = new PlayTenorNote();
            final PlayBassNote playBassNote = new PlayBassNote();

            chooseakey.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    pos1=position;
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


            deletebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (melodyarray.size()>0)
                        melodyarray.remove(melodyarray.size()-1);
                    if (melodysofar.getText().toString().length()>0)
                        melodysofar.setText((melodysofar.getText().toString()).substring(0,(melodysofar.getText().toString()).length()-3));
                }
            });


            clearbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    melodysofar.setText("");
                    melodyarray.clear();

                }
            });


            startbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //test = melodysofar.getText().toString();


                    startbutton.setEnabled(false);
                    startbutton.setClickable(false);

                    clearbutton.setEnabled(false);
                    clearbutton.setClickable(false);

                    deletebutton.setEnabled(false);
                    deletebutton.setClickable(false);
                    Log.d("DEBUG", "in onclick for start");


                    Log.d("DEBUG", "about to start key if statements");
                    if (list1.get(pos1).toUpperCase().equals("C MAJOR")) {
                        r = 5;
                        Log.d("DEBUG", "for c major key");
                    } else if (list1.get(pos1).toUpperCase().equals("C# MAJOR")) {
                        r = 6;
                    } else if (list1.get(pos1).toUpperCase().equals("D MAJOR")) {
                        r = 7;
                    } else if (list1.get(pos1).toUpperCase().equals("EB MAJOR")) {
                        r = 8;
                    } else if (list1.get(pos1).toUpperCase().equals("E MAJOR")) {
                        r = 9;
                    } else if (list1.get(pos1).toUpperCase().equals("F MAJOR")) {
                        r = 10;
                    } else if (list1.get(pos1).toUpperCase().equals("F# MAJOR")) {
                        r = 11;
                        Log.d("DEBUG", "for f# major key");
                    } else if (list1.get(pos1).toUpperCase().equals("G MAJOR")) {
                        r = 12;
                    } else if (list1.get(pos1).toUpperCase().equals("AB MAJOR")) {
                        r = 13;
                    } else if (list1.get(pos1).toUpperCase().equals("A MAJOR")) {
                        r = 14;
                    } else if (list1.get(pos1).toUpperCase().equals("BB MAJOR")) {
                        r = 15;
                    } else if (list1.get(pos1).toUpperCase().equals("B MAJOR")) {
                        r = 16;
                    } else
                        Log.d("DEBUG", "went into else");






                    class PlayTheNotes extends AsyncTask<Void,Void,Void> {

                        @Override
                        protected Void doInBackground(Void... voids) {
                            android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
                            int whichnoteinmelody = 0;
                            int u=0;
                            Log.d("DEBUG", "about to go into outer for loop");
                            for (int x = 0; x < melodyarray.size(); x++) {
                                int rother = r;
                                Log.d("DEBUG", "in outer for loop " + x);
                                //for (int x = 0; x < input.length(); x += 3) {
                                for (int i = 0; i < 7; i++) {
                                    Log.d("DEBUG", "in inner for loop " + i);
                                    if (melodyarray.get(whichnoteinmelody)<r)
                                    {
                                        u=melodyarray.get(whichnoteinmelody);
                                        playBassNote.runMelody(u);
                                        playTenorNote.runMelody(u + spacesinchordarray[1][0]);
                                        playAltoNote.runMelody(u + spacesinchordarray[1][0] + spacesinchordarray[1][1]);
                                        playSopranoNote.runMelody(u + spacesinchordarray[1][0] + spacesinchordarray[1][1] + spacesinchordarray[1][2]);
                                        try {
                                            Thread.sleep(2000);
                                        } catch (InterruptedException e1) {
                                            e1.printStackTrace();
                                        }
                                        break;
                                    }
                                    else if (rother == melodyarray.get(whichnoteinmelody)) {
                                        Log.d("DEBUG", "in if statement in loop");
                                        playBassNote.runMelody(rother);
                                        Log.d("DEBUG", "playbass called");
                                        playTenorNote.runMelody(rother + spacesinchordarray[i][0]);
                                        Log.d("DEBUG", "playtenor called");
                                        playAltoNote.runMelody(rother + spacesinchordarray[i][0] + spacesinchordarray[i][1]);
                                        Log.d("DEBUG", "playalto called");
                                        playSopranoNote.runMelody(rother + spacesinchordarray[i][0] + spacesinchordarray[i][1] + spacesinchordarray[i][2]);
                                        Log.d("DEBUG", "playsoprano called");

                                        try {
                                            Thread.sleep(1300);
                                        } catch (InterruptedException e1) {
                                            e1.printStackTrace();
                                        }
                                        break;
                                    }
                                    rother = rother + spacesinscalearray.get(i);

                                }
                                whichnoteinmelody++;
                            }
                            Piano.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    startbutton.setEnabled(true);
                                    startbutton.setClickable(true);

                                    clearbutton.setEnabled(true);
                                    clearbutton.setClickable(true);

                                    deletebutton.setEnabled(true);
                                    deletebutton.setClickable(true);
                                }
                            });

                            return null;
                        }
                    }

                    PlayTheNotes playTheNotes = new PlayTheNotes();
                    playTheNotes.execute();
                    Log.d("DEBUG", "done playing all");
                    String fileName = "melodyline.json";
                    try {
                        @SuppressLint("WorldWriteableFiles") OutputStreamWriter writer = new OutputStreamWriter(openFileOutput(fileName, Context.MODE_PRIVATE));
                        jsonObject= new JSONObject();
                        //list1.get(pos1).

                        jsonObject.put("savedkey",pos1);
                        jsonObject.put("savedmelodytext",melodysofar.getText());
                        JSONArray jsArray = new JSONArray();
                        for (int i = 0; i < melodyarray.size();i++){
                            jsArray.put(melodyarray.get(i));
                        }
                        jsonObject.put("savedmelodyarray",jsArray);

                        writer.write(jsonObject.toString());
                        writer.close();
                        Log.d("file",Piano.this.getFilesDir().getAbsolutePath());

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }


                    //startbutton.setOnClickListener(!null);


                }


            });


            cbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (list1.get(pos1).toUpperCase().equals("D MAJOR") || list1.get(pos1).toUpperCase().equals("A MAJOR") || list1.get(pos1).toUpperCase().equals("E MAJOR") || list1.get(pos1).toUpperCase().equals("B MAJOR")|| list1.get(pos1).toUpperCase().equals("F# MAJOR")|| list1.get(pos1).toUpperCase().equals("C# MAJOR"))
                        Toast.makeText(Piano.this, "This note is not in your key!!", Toast.LENGTH_SHORT).show();
                    else {
                        melodysofar.setText(melodysofar.getText() + "C  ");
                        melodyarray.add(5);
                    }


                    playNote(R.raw.cnote);
                    Log.d("MP", "c pressed");
                    cbutton.setBackgroundColor(Color.rgb(1,70,170));
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            cbutton.setBackgroundColor(Color.WHITE);
                        }
                    }, 1000);

                }
            });
            csharpdflatbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list1.get(pos1).toUpperCase().equals("D MAJOR") || list1.get(pos1).toUpperCase().equals("A MAJOR") || list1.get(pos1).toUpperCase().equals("E MAJOR") || list1.get(pos1).toUpperCase().equals("B MAJOR") || list1.get(pos1).toUpperCase().equals("F# MAJOR") || list1.get(pos1).toUpperCase().equals("C# MAJOR") || list1.get(pos1).toUpperCase().equals("B MINOR") || list1.get(pos1).toUpperCase().equals("F# MINOR") || list1.get(pos1).toUpperCase().equals("C# MINOR") || list1.get(pos1).toUpperCase().equals("G# MINOR") || list1.get(pos1).toUpperCase().equals("D# MINOR") || list1.get(pos1).toUpperCase().equals("A# MINOR")) {
                        melodysofar.setText(melodysofar.getText() + "C#  ");
                        melodyarray.add(6);

                    } else if (list1.get(pos1).toUpperCase().equals("AB MAJOR") || list1.get(pos1).toUpperCase().equals("DB MAJOR") || list1.get(pos1).toUpperCase().equals("GB MAJOR") || list1.get(pos1).toUpperCase().equals("CB MAJOR") || list1.get(pos1).toUpperCase().equals("F MINOR") || list1.get(pos1).toUpperCase().equals("BB MINOR") || list1.get(pos1).toUpperCase().equals("EB MINOR") || list1.get(pos1).toUpperCase().equals("AB MINOR")) {
                        melodyarray.add(6);
                        melodysofar.setText(melodysofar.getText() + "Db  ");
                    } else {
                        Toast.makeText(Piano.this, "This note is not in your key!!", Toast.LENGTH_SHORT).show();
                    }
                    playNote(R.raw.csharpdflat);

                    csharpdflatbutton.setBackgroundColor(Color.rgb(1,70,170));
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            csharpdflatbutton.setBackgroundColor(Color.BLACK);
                        }
                    }, 1000);
                }
            });
            dbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list1.get(pos1).toUpperCase().equals("E MAJOR")|| list1.get(pos1).toUpperCase().equals("B MAJOR")|| list1.get(pos1).toUpperCase().equals("F# MAJOR")|| list1.get(pos1).toUpperCase().equals("C# MAJOR")|| list1.get(pos1).toUpperCase().equals("AB MAJOR"))
                        Toast.makeText(Piano.this, "This note is not in your key!!", Toast.LENGTH_SHORT).show();

                    else {
                        melodyarray.add(7);
                        melodysofar.setText(melodysofar.getText() + "D  ");
                    }
                    playNote(R.raw.dnote);
                    dbutton.setBackgroundColor(Color.rgb(1,70,170));
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dbutton.setBackgroundColor(Color.WHITE);
                        }
                    }, 1000);

                }
            });
            dsharpeflatbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list1.get(pos1).toUpperCase().equals("E MAJOR") || list1.get(pos1).toUpperCase().equals("B MAJOR") || list1.get(pos1).toUpperCase().equals("F# MAJOR") || list1.get(pos1).toUpperCase().equals("C# MAJOR") || list1.get(pos1).toUpperCase().equals("C# MINOR") || list1.get(pos1).toUpperCase().equals("G# MINOR") || list1.get(pos1).toUpperCase().equals("D# MINOR") || list1.get(pos1).toUpperCase().equals("A# MINOR")) {
                        melodysofar.setText(melodysofar.getText() + "D#  ");
                        melodyarray.add(8);
                    }
                    else if (list1.get(pos1).toUpperCase().equals("BB MAJOR") || list1.get(pos1).toUpperCase().equals("EB MAJOR") || list1.get(pos1).toUpperCase().equals("AB MAJOR") || list1.get(pos1).toUpperCase().equals("DB MAJOR") || list1.get(pos1).toUpperCase().equals("GB MAJOR") || list1.get(pos1).toUpperCase().equals("CB MAJOR") || list1.get(pos1).toUpperCase().equals("G MINOR") || list1.get(pos1).toUpperCase().equals("C MINOR") || list1.get(pos1).toUpperCase().equals("F MINOR") || list1.get(pos1).toUpperCase().equals("BB MINOR") || list1.get(pos1).toUpperCase().equals("EB MINOR") || list1.get(pos1).toUpperCase().equals("AB MINOR"))
                    {
                        melodysofar.setText(melodysofar.getText() + "Eb  ");
                        melodyarray.add(8);

                    }
                    else {
                        Toast.makeText(Piano.this, "This note is not in your key!!", Toast.LENGTH_SHORT).show();
                    }
                    playNote(R.raw.dsharpeflat);

                    dsharpeflatbutton.setBackgroundColor(Color.rgb(1,70,170));
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dsharpeflatbutton.setBackgroundColor(Color.BLACK);
                        }
                    }, 1000);
                }
            });
            ebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list1.get(pos1).toUpperCase().equals("BB MAJOR") || list1.get(pos1).toUpperCase().equals("EB MAJOR") || list1.get(pos1).toUpperCase().equals("AB MAJOR") || list1.get(pos1).toUpperCase().equals("F# MAJOR") || list1.get(pos1).toUpperCase().equals("C# MAJOR"))
                        Toast.makeText(Piano.this, "This note is not in your key!!", Toast.LENGTH_SHORT).show();
                    else {
                        melodysofar.setText(melodysofar.getText() + "E  ");
                        melodyarray.add(9);

                    }

                    playNote(R.raw.enote);

                    ebutton.setBackgroundColor(Color.rgb(1,70,170));
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ebutton.setBackgroundColor(Color.WHITE);
                        }
                    }, 1000);
                }
            });
            fbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list1.get(pos1).toUpperCase().equals("G MAJOR") || list1.get(pos1).toUpperCase().equals("D MAJOR") || list1.get(pos1).toUpperCase().equals("A MAJOR") || list1.get(pos1).toUpperCase().equals("E MAJOR") || list1.get(pos1).toUpperCase().equals("B MAJOR") || list1.get(pos1).toUpperCase().equals("F# MAJOR") || list1.get(pos1).toUpperCase().equals("C# MAJOR"))
                        Toast.makeText(Piano.this, "This note is not in your key!!", Toast.LENGTH_SHORT).show();

                    else {
                        melodyarray.add(10);
                        melodysofar.setText(melodysofar.getText() + "F  ");
                    }
                    playNote(R.raw.fnote);

                    fbutton.setBackgroundColor(Color.rgb(1,70,170));
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            fbutton.setBackgroundColor(Color.WHITE);
                        }
                    }, 1000);
                }
            });
            fsharpgflatbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list1.get(pos1).toUpperCase().equals("G MAJOR") || list1.get(pos1).toUpperCase().equals("D MAJOR") || list1.get(pos1).toUpperCase().equals("A MAJOR") || list1.get(pos1).toUpperCase().equals("E MAJOR") || list1.get(pos1).toUpperCase().equals("B MAJOR") || list1.get(pos1).toUpperCase().equals("F# MAJOR") || list1.get(pos1).toUpperCase().equals("C# MAJOR") || list1.get(pos1).toUpperCase().equals("E MINOR") || list1.get(pos1).toUpperCase().equals("B MINOR") || list1.get(pos1).toUpperCase().equals("F# MINOR") || list1.get(pos1).toUpperCase().equals("C# MINOR") || list1.get(pos1).toUpperCase().equals("G# MINOR") || list1.get(pos1).toUpperCase().equals("D# MINOR") || list1.get(pos1).toUpperCase().equals("A# MINOR")) {
                        melodysofar.setText(melodysofar.getText() + "F#  ");
                        melodyarray.add(11);

                    } else if (list1.get(pos1).toUpperCase().equals("DB MAJOR") || list1.get(pos1).toUpperCase().equals("GB MAJOR") || list1.get(pos1).toUpperCase().equals("CB MAJOR") || list1.get(pos1).toUpperCase().equals("BB MINOR") || list1.get(pos1).toUpperCase().equals("EB MINOR") || list1.get(pos1).toUpperCase().equals("AB MINOR")) {
                        melodysofar.setText(melodysofar.getText() + "Gb  ");
                        melodyarray.add(11);
                    } else {
                        Toast.makeText(Piano.this, "This note is not in your key!!", Toast.LENGTH_SHORT).show();
                    }
                    playNote(R.raw.fsharpgflat);

                    fsharpgflatbutton.setBackgroundColor(Color.rgb(1,70,170));
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            fsharpgflatbutton.setBackgroundColor(Color.BLACK);
                        }
                    }, 1000);
                }
            });
            gbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list1.get(pos1).toUpperCase().equals("A MAJOR") || list1.get(pos1).toUpperCase().equals("E MAJOR") || list1.get(pos1).toUpperCase().equals("B MAJOR") || list1.get(pos1).toUpperCase().equals("F# MAJOR") || list1.get(pos1).toUpperCase().equals("C# MAJOR"))
                        Toast.makeText(Piano.this, "This note is not in your key!!", Toast.LENGTH_SHORT).show();
                    else {
                        melodyarray.add(12);
                        melodysofar.setText(melodysofar.getText() + "G  ");
                    }

                    playNote(R.raw.gnote);

                    gbutton.setBackgroundColor(Color.rgb(1,70,170));
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            gbutton.setBackgroundColor(Color.WHITE);
                        }
                    }, 1000);
                }
            });
            gsharpaflatbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list1.get(pos1).toUpperCase().equals("A MAJOR") || list1.get(pos1).toUpperCase().equals("E MAJOR") || list1.get(pos1).toUpperCase().equals("B MAJOR") || list1.get(pos1).toUpperCase().equals("F# MAJOR") || list1.get(pos1).toUpperCase().equals("C# MAJOR") || list1.get(pos1).toUpperCase().equals("F# MINOR") || list1.get(pos1).toUpperCase().equals("C# MINOR") || list1.get(pos1).toUpperCase().equals("G# MINOR") || list1.get(pos1).toUpperCase().equals("D# MINOR") || list1.get(pos1).toUpperCase().equals("A# MINOR")) {
                        melodysofar.setText(melodysofar.getText() + "G#  ");
                        melodyarray.add(13);
                    }

                    else if (list1.get(pos1).toUpperCase().equals("EB MAJOR") || list1.get(pos1).toUpperCase().equals("AB MAJOR") || list1.get(pos1).toUpperCase().equals("DB MAJOR") || list1.get(pos1).toUpperCase().equals("GB MAJOR") || list1.get(pos1).toUpperCase().equals("CB MAJOR") || list1.get(pos1).toUpperCase().equals("C MINOR") || list1.get(pos1).toUpperCase().equals("F MINOR") || list1.get(pos1).toUpperCase().equals("BB MINOR") || list1.get(pos1).toUpperCase().equals("EB MINOR") || list1.get(pos1).toUpperCase().equals("AB MINOR")) {
                        melodysofar.setText(melodysofar.getText() + "Ab  ");
                        melodyarray.add(13);
                    }

                    else {
                        Toast.makeText(Piano.this, "This note is not in your key!!", Toast.LENGTH_SHORT).show();
                    }
                    playNote(R.raw.gsharpaflat);

                    gsharpaflatbutton.setBackgroundColor(Color.rgb(1,70,170));
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            gsharpaflatbutton.setBackgroundColor(Color.BLACK);
                        }
                    }, 1000);
                }
            });
            abutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list1.get(pos1).toUpperCase().equals("B MAJOR") || list1.get(pos1).toUpperCase().equals("F# MAJOR") || list1.get(pos1).toUpperCase().equals("C# MAJOR") || list1.get(pos1).toUpperCase().equals("EB MAJOR") || list1.get(pos1).toUpperCase().equals("AB MAJOR"))
                        Toast.makeText(Piano.this, "This note is not in your key!!", Toast.LENGTH_SHORT).show();

                    else {
                        melodysofar.setText(melodysofar.getText() + "A  ");
                        melodyarray.add(14);

                    }
                    playNote(R.raw.anote);

                    abutton.setBackgroundColor(Color.rgb(1,70,170));
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            abutton.setBackgroundColor(Color.WHITE);
                        }
                    }, 1000);
                }
            });
            asharpbflatbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list1.get(pos1).toUpperCase().equals("B MAJOR") || list1.get(pos1).toUpperCase().equals("F# MAJOR") || list1.get(pos1).toUpperCase().equals("C# MAJOR") || list1.get(pos1).toUpperCase().equals("G# MINOR") || list1.get(pos1).toUpperCase().equals("D# MINOR") || list1.get(pos1).toUpperCase().equals("A# MINOR")) {
                        melodysofar.setText(melodysofar.getText() + "A#  ");
                        melodyarray.add(15);

                    }
                    else if (list1.get(pos1).toUpperCase().equals("F MAJOR") || list1.get(pos1).toUpperCase().equals("BB MAJOR") || list1.get(pos1).toUpperCase().equals("EB MAJOR") || list1.get(pos1).toUpperCase().equals("AB MAJOR") || list1.get(pos1).toUpperCase().equals("DB MAJOR") || list1.get(pos1).toUpperCase().equals("GB MAJOR") || list1.get(pos1).toUpperCase().equals("CB MAJOR") || list1.get(pos1).toUpperCase().equals("D MINOR") || list1.get(pos1).toUpperCase().equals("G MINOR") || list1.get(pos1).toUpperCase().equals("C MINOR") || list1.get(pos1).toUpperCase().equals("F MINOR") || list1.get(pos1).toUpperCase().equals("BB MINOR") || list1.get(pos1).toUpperCase().equals("EB MINOR") || list1.get(pos1).toUpperCase().equals("AB MINOR")) {
                        melodysofar.setText(melodysofar.getText() + "Bb  ");
                        melodyarray.add(15);

                    }
                    else {
                        Toast.makeText(Piano.this, "This note is not in your key!!", Toast.LENGTH_SHORT).show();
                    }
                    playNote(R.raw.asharpbflat);

                    asharpbflatbutton.setBackgroundColor(Color.rgb(1,70,170));
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            asharpbflatbutton.setBackgroundColor(Color.BLACK);
                        }
                    }, 1000);
                }
            });
            bbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list1.get(pos1).toUpperCase().equals("C# MAJOR") || list1.get(pos1).toUpperCase().equals("F MAJOR") ||list1.get(pos1).toUpperCase().equals("BB MAJOR") || list1.get(pos1).toUpperCase().equals("EB MAJOR") || list1.get(pos1).toUpperCase().equals("AB MAJOR"))
                        Toast.makeText(Piano.this, "This note is not in your key!!", Toast.LENGTH_SHORT).show();
                    else {
                        melodyarray.add(16);
                        melodysofar.setText(melodysofar.getText() + "B  ");
                    }

                    playNote(R.raw.bnote);

                    bbutton.setBackgroundColor(Color.rgb(1,70,170));
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bbutton.setBackgroundColor(Color.WHITE);
                        }
                    }, 1000);
                }
            });
            highcbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list1.get(pos1).toUpperCase().equals("D MAJOR") || list1.get(pos1).toUpperCase().equals("A MAJOR") || list1.get(pos1).toUpperCase().equals("E MAJOR") || list1.get(pos1).toUpperCase().equals("B MAJOR")|| list1.get(pos1).toUpperCase().equals("F# MAJOR")|| list1.get(pos1).toUpperCase().equals("C# MAJOR"))
                        Toast.makeText(Piano.this, "This note is not in your key!!", Toast.LENGTH_SHORT).show();
                    else {
                        melodysofar.setText(melodysofar.getText() + "C  ");
                        melodyarray.add(17);

                    }

                    playNote(R.raw.highcnote);

                    highcbutton.setBackgroundColor(Color.rgb(1,70,170));
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            highcbutton.setBackgroundColor(Color.WHITE);
                        }
                    }, 1000);
                }
            });
            highcsharpdflatbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list1.get(pos1).toUpperCase().equals("D MAJOR") || list1.get(pos1).toUpperCase().equals("A MAJOR") || list1.get(pos1).toUpperCase().equals("E MAJOR") || list1.get(pos1).toUpperCase().equals("B MAJOR") || list1.get(pos1).toUpperCase().equals("F# MAJOR") || list1.get(pos1).toUpperCase().equals("C# MAJOR") || list1.get(pos1).toUpperCase().equals("B MINOR") || list1.get(pos1).toUpperCase().equals("F# MINOR") || list1.get(pos1).toUpperCase().equals("C# MINOR") || list1.get(pos1).toUpperCase().equals("G# MINOR") || list1.get(pos1).toUpperCase().equals("D# MINOR") || list1.get(pos1).toUpperCase().equals("A# MINOR")) {
                        melodysofar.setText(melodysofar.getText() + "C#  ");
                        melodyarray.add(6);

                    } else if (list1.get(pos1).toUpperCase().equals("AB MAJOR") || list1.get(pos1).toUpperCase().equals("DB MAJOR") || list1.get(pos1).toUpperCase().equals("GB MAJOR") || list1.get(pos1).toUpperCase().equals("CB MAJOR") || list1.get(pos1).toUpperCase().equals("F MINOR") || list1.get(pos1).toUpperCase().equals("BB MINOR") || list1.get(pos1).toUpperCase().equals("EB MINOR") || list1.get(pos1).toUpperCase().equals("AB MINOR")) {
                        melodyarray.add(6);
                        melodysofar.setText(melodysofar.getText() + "Db  ");
                    } else {
                        Toast.makeText(Piano.this, "This note is not in your key!!", Toast.LENGTH_SHORT).show();
                    }
                    playNote(R.raw.highcsharpdflat);

                    highcsharpdflatbutton.setBackgroundColor(Color.rgb(1,70,170));
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            highcsharpdflatbutton.setBackgroundColor(Color.BLACK);
                        }
                    }, 1000);
                }
            });
            highdbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list1.get(pos1).toUpperCase().equals("E MAJOR")|| list1.get(pos1).toUpperCase().equals("B MAJOR")|| list1.get(pos1).toUpperCase().equals("F# MAJOR")|| list1.get(pos1).toUpperCase().equals("C# MAJOR")|| list1.get(pos1).toUpperCase().equals("AB MAJOR"))
                        Toast.makeText(Piano.this, "This note is not in your key!!", Toast.LENGTH_SHORT).show();

                    else {
                        melodyarray.add(7);
                        melodysofar.setText(melodysofar.getText() + "D  ");
                    }

                    playNote(R.raw.highdnote);
                    highdbutton.setBackgroundColor(Color.rgb(1,70,170));
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            highdbutton.setBackgroundColor(Color.WHITE);
                        }
                    }, 1000);
                }
            });
            highdsharpeflatbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list1.get(pos1).toUpperCase().equals("E MAJOR") || list1.get(pos1).toUpperCase().equals("B MAJOR") || list1.get(pos1).toUpperCase().equals("F# MAJOR") || list1.get(pos1).toUpperCase().equals("C# MAJOR") || list1.get(pos1).toUpperCase().equals("C# MINOR") || list1.get(pos1).toUpperCase().equals("G# MINOR") || list1.get(pos1).toUpperCase().equals("D# MINOR") || list1.get(pos1).toUpperCase().equals("A# MINOR")) {
                        melodysofar.setText(melodysofar.getText() + "D#  ");
                        melodyarray.add(8);
                    }
                    else if (list1.get(pos1).toUpperCase().equals("BB MAJOR") || list1.get(pos1).toUpperCase().equals("EB MAJOR") || list1.get(pos1).toUpperCase().equals("AB MAJOR") || list1.get(pos1).toUpperCase().equals("DB MAJOR") || list1.get(pos1).toUpperCase().equals("GB MAJOR") || list1.get(pos1).toUpperCase().equals("CB MAJOR") || list1.get(pos1).toUpperCase().equals("G MINOR") || list1.get(pos1).toUpperCase().equals("C MINOR") || list1.get(pos1).toUpperCase().equals("F MINOR") || list1.get(pos1).toUpperCase().equals("BB MINOR") || list1.get(pos1).toUpperCase().equals("EB MINOR") || list1.get(pos1).toUpperCase().equals("AB MINOR"))
                    {
                        melodysofar.setText(melodysofar.getText() + "Eb  ");
                        melodyarray.add(8);

                    }
                    else {
                        Toast.makeText(Piano.this, "This note is not in your key!!", Toast.LENGTH_SHORT).show();
                    }
                    playNote(R.raw.highdsharpeflat);

                    highdsharpeflatbutton.setBackgroundColor(Color.rgb(1,70,170));
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            highdsharpeflatbutton.setBackgroundColor(Color.BLACK);
                        }
                    }, 1000);
                }
            });
            highebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list1.get(pos1).toUpperCase().equals("BB MAJOR") || list1.get(pos1).toUpperCase().equals("EB MAJOR") || list1.get(pos1).toUpperCase().equals("AB MAJOR") || list1.get(pos1).toUpperCase().equals("F# MAJOR") || list1.get(pos1).toUpperCase().equals("C# MAJOR"))
                        Toast.makeText(Piano.this, "This note is not in your key!!", Toast.LENGTH_SHORT).show();
                    else {
                        melodysofar.setText(melodysofar.getText() + "E  ");
                        melodyarray.add(9);

                    }
                    playNote(R.raw.highenote);

                    highebutton.setBackgroundColor(Color.rgb(1,70,170));
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            highebutton.setBackgroundColor(Color.WHITE);
                        }
                    }, 1000);
                }
            });
            highfbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list1.get(pos1).toUpperCase().equals("G MAJOR") || list1.get(pos1).toUpperCase().equals("D MAJOR") || list1.get(pos1).toUpperCase().equals("A MAJOR") || list1.get(pos1).toUpperCase().equals("E MAJOR") || list1.get(pos1).toUpperCase().equals("B MAJOR") || list1.get(pos1).toUpperCase().equals("F# MAJOR") || list1.get(pos1).toUpperCase().equals("C# MAJOR"))
                        Toast.makeText(Piano.this, "This note is not in your key!!", Toast.LENGTH_SHORT).show();

                    else {
                        melodyarray.add(10);
                        melodysofar.setText(melodysofar.getText() + "F  ");
                    }
                    playNote(R.raw.highfnote);

                    highfbutton.setBackgroundColor(Color.rgb(1,70,170));
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            highfbutton.setBackgroundColor(Color.WHITE);
                        }
                    }, 1000);
                }
            });
            highfsharpgflatbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list1.get(pos1).toUpperCase().equals("G MAJOR") || list1.get(pos1).toUpperCase().equals("D MAJOR") || list1.get(pos1).toUpperCase().equals("A MAJOR") || list1.get(pos1).toUpperCase().equals("E MAJOR") || list1.get(pos1).toUpperCase().equals("B MAJOR") || list1.get(pos1).toUpperCase().equals("F# MAJOR") || list1.get(pos1).toUpperCase().equals("C# MAJOR") || list1.get(pos1).toUpperCase().equals("E MINOR") || list1.get(pos1).toUpperCase().equals("B MINOR") || list1.get(pos1).toUpperCase().equals("F# MINOR") || list1.get(pos1).toUpperCase().equals("C# MINOR") || list1.get(pos1).toUpperCase().equals("G# MINOR") || list1.get(pos1).toUpperCase().equals("D# MINOR") || list1.get(pos1).toUpperCase().equals("A# MINOR")) {
                        melodysofar.setText(melodysofar.getText() + "F#  ");
                        melodyarray.add(11);

                    } else if (list1.get(pos1).toUpperCase().equals("DB MAJOR") || list1.get(pos1).toUpperCase().equals("GB MAJOR") || list1.get(pos1).toUpperCase().equals("CB MAJOR") || list1.get(pos1).toUpperCase().equals("BB MINOR") || list1.get(pos1).toUpperCase().equals("EB MINOR") || list1.get(pos1).toUpperCase().equals("AB MINOR")) {
                        melodysofar.setText(melodysofar.getText() + "Gb  ");
                        melodyarray.add(11);
                    } else {
                        Toast.makeText(Piano.this, "This note is not in your key!!", Toast.LENGTH_SHORT).show();
                    }
                    playNote(R.raw.highfsharpgflat);

                    highfsharpgflatbutton.setBackgroundColor(Color.rgb(1,70,170));
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            highfsharpgflatbutton.setBackgroundColor(Color.BLACK);
                        }
                    }, 1000);
                }
            });
            highgbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list1.get(pos1).toUpperCase().equals("A MAJOR") || list1.get(pos1).toUpperCase().equals("E MAJOR") || list1.get(pos1).toUpperCase().equals("B MAJOR") || list1.get(pos1).toUpperCase().equals("F# MAJOR") || list1.get(pos1).toUpperCase().equals("C# MAJOR"))
                        Toast.makeText(Piano.this, "This note is not in your key!!", Toast.LENGTH_SHORT).show();
                    else {
                        melodyarray.add(12);
                        melodysofar.setText(melodysofar.getText() + "G  ");
                    }
                    playNote(R.raw.highgnote);

                    highgbutton.setBackgroundColor(Color.rgb(1,70,170));
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            highgbutton.setBackgroundColor(Color.WHITE);
                        }
                    }, 1000);
                }
            });
            highgsharpaflatbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list1.get(pos1).toUpperCase().equals("A MAJOR") || list1.get(pos1).toUpperCase().equals("E MAJOR") || list1.get(pos1).toUpperCase().equals("B MAJOR") || list1.get(pos1).toUpperCase().equals("F# MAJOR") || list1.get(pos1).toUpperCase().equals("C# MAJOR") || list1.get(pos1).toUpperCase().equals("F# MINOR") || list1.get(pos1).toUpperCase().equals("C# MINOR") || list1.get(pos1).toUpperCase().equals("G# MINOR") || list1.get(pos1).toUpperCase().equals("D# MINOR") || list1.get(pos1).toUpperCase().equals("A# MINOR")) {
                        melodysofar.setText(melodysofar.getText() + "G#  ");
                        melodyarray.add(13);
                    }

                    else if (list1.get(pos1).toUpperCase().equals("EB MAJOR") || list1.get(pos1).toUpperCase().equals("AB MAJOR") || list1.get(pos1).toUpperCase().equals("DB MAJOR") || list1.get(pos1).toUpperCase().equals("GB MAJOR") || list1.get(pos1).toUpperCase().equals("CB MAJOR") || list1.get(pos1).toUpperCase().equals("C MINOR") || list1.get(pos1).toUpperCase().equals("F MINOR") || list1.get(pos1).toUpperCase().equals("BB MINOR") || list1.get(pos1).toUpperCase().equals("EB MINOR") || list1.get(pos1).toUpperCase().equals("AB MINOR")) {
                        melodysofar.setText(melodysofar.getText() + "Ab  ");
                        melodyarray.add(13);
                    }

                    else {
                        Toast.makeText(Piano.this, "This note is not in your key!!", Toast.LENGTH_SHORT).show();
                    }
                    playNote(R.raw.highgsharpaflat);

                    highgsharpaflatbutton.setBackgroundColor(Color.rgb(1,70,170));
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            highgsharpaflatbutton.setBackgroundColor(Color.BLACK);
                        }
                    }, 1000);
                }
            });
            String input = "";
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput("melodyline.json")));

                input = reader.readLine();
                Log.d("filetesting1",input);
                JSONObject jsonobject1 = new JSONObject(input);
                melodysofar.setText( jsonobject1.get("savedmelodytext").toString());
                JSONArray jMelodyArray = jsonobject1.getJSONArray("savedmelodyarray");
                for (int i=0;i<jMelodyArray.length();i++){
                    melodyarray.add(jMelodyArray.getInt(i));
                }
                chooseakey.setSelection((Integer) jsonobject1.get("savedkey"));
                reader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }
        public void playNote( int resid)
        {
            MediaPlayer melodyplayer1 = MediaPlayer.create(Piano.this, resid);
            melodyplayer1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                    }
                    if (mediaPlayer != null) {
                        mediaPlayer.release();
                    }

                }
            });
            melodyplayer1.setVolume(1,1);
            melodyplayer1.start();
        }

        public class PlaySopranoNote {

            public void runMelody(final int resid) {
                Piano.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if ((resid-5)>=0 && (resid-5)<21) {
                            colorSelectedKey(arrayforcoloringkeys.get(resid - 5));
                        }
                    }
                });
                sopranoMediaPlayer = MediaPlayer.create(Piano.this, wavfiles.get(resid));
                sopranoMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.stop();
                        }
                        if (mediaPlayer != null) {
                            mediaPlayer.release();
                        }

                    }
                });
                sopranoMediaPlayer.setVolume(1,1);
                sopranoMediaPlayer.start();
                Log.d("DEBUG","SOPRANO NOTE IS: "+resid);
            }
        }
        public class PlayAltoNote  {
            public void runMelody(final int resid) {
                Piano.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if ((resid-5)>=0 && (resid-5)<=21) {
                            Log.d("Weird",resid-5+"");

                            colorSelectedKey(arrayforcoloringkeys.get(resid - 5));
                        }
                    }
                });
                altoMediaPlayer = MediaPlayer.create(Piano.this, wavfiles.get(resid));
                altoMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.stop();
                        }
                        if (mediaPlayer != null) {
                            mediaPlayer.release();
                        }

                    }
                });
                altoMediaPlayer.setVolume(1,1);
                altoMediaPlayer.start();
                Log.d("DEBUG","ALTO NOTE IS: "+resid);


            }


        }
        public class PlayTenorNote {
            public void runMelody(final int resid) {
                Piano.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if ((resid-5)>=0 && (resid-5)<21) {
                            colorSelectedKey(arrayforcoloringkeys.get(resid - 5));
                            Log.d("Weird",resid-5+"");

                        }
                    }
                });
                tenorMediaPlayer = MediaPlayer.create(Piano.this, wavfiles.get(resid));
                tenorMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.stop();
                        }
                        if (mediaPlayer != null) {
                            mediaPlayer.release();
                        }

                    }
                });
                tenorMediaPlayer.setVolume(1,1);
                tenorMediaPlayer.start();
                Log.d("DEBUG","TENOR NOTE IS: "+resid);

            }


        }
        public class PlayBassNote {

            public void runMelody(final int resid) {
                Piano.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if ((resid-5)>=0 && (resid-5)<=21) {
                            Log.d("Weird",resid-5+"");
                            colorSelectedKey(arrayforcoloringkeys.get(resid - 5));
                        }
                    }
                });

                bassMediaPlayer = MediaPlayer.create(Piano.this, wavfiles.get(resid));
                bassMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.stop();
                        }
                        if (mediaPlayer != null) {
                            mediaPlayer.release();
                        }

                    }
                });
                bassMediaPlayer.setVolume(1,1);
                bassMediaPlayer.start();
                Log.d("DEBUG","BASS NOTE IS: "+resid);



            }

        }
        public void colorSelectedKey(final Button button)
        {
            Log.d("Weird",button+"");
            button.setBackgroundColor(Color.rgb(1,70,170));
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (button==csharpdflatbutton || button==dsharpeflatbutton || button==fsharpgflatbutton || button==gsharpaflatbutton || button==asharpbflatbutton || button==highcsharpdflatbutton || button==highdsharpeflatbutton  || button==highfsharpgflatbutton  || button==highgsharpaflatbutton)
                        button.setBackgroundColor(Color.BLACK);
                    else
                        button.setBackgroundColor(Color.WHITE);

                }
            }, 500);
        }


    }


