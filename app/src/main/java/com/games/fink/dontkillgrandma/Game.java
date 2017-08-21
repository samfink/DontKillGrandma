package com.games.fink.dontkillgrandma;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static android.graphics.Color.*;
import static java.util.Collections.shuffle;

public class Game extends AppCompatActivity {


    public int getRandomColor(){

        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

    }


    public int seconds = 5;
    public int i=0;
    public int j=0;
    public int k=0;
    public int l=0;
    public int m=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        SharedPreferences preferences = getSharedPreferences("file", Context.MODE_PRIVATE);
        String name = preferences.getString("points", "0");

        final TextView tv = (TextView) findViewById(R.id.textView4);
       tv.setText(name);

        final Button button1 = (Button) findViewById(R.id.button1);
        final Button button2 = (Button) findViewById(R.id.button2);
        final Button button3 = (Button) findViewById(R.id.button3);
        final Button button4 = (Button) findViewById(R.id.button4);
        final Button submit = (Button) findViewById(R.id.submit);

       final int color1 =getRandomColor();
       final int color2 =getRandomColor();
       final int color3 =getRandomColor();
       final int color4 =getRandomColor();


       final ArrayList<Integer> list = new ArrayList<>();
        list.add(color1);
        list.add(color2);
        list.add(color3);
        list.add(color4);


        final ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(color1);
        list2.add(color2);
        list2.add(color3);
        list2.add(color4);

        Collections.shuffle(list2);

        final Random rnd = new Random();

        button1.setBackgroundColor(color1);
        button2.setBackgroundColor(color2);
        button3.setBackgroundColor(color3);
        button4.setBackgroundColor(color4);

        final ArrayList<Integer> checker = new ArrayList<>();

        checker.add(color4);
        checker.add(color3);
        checker.add(color2);
        checker.add(color1);


        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                int newColor=list2.get(j);

                if(j==list2.size()-1){
                    j=0;
                }
                else{
                    j++;
                }
                button1.setBackgroundColor(newColor);
                    checker.remove(0);
                    checker.add(0, newColor);


            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                int newColor=list2.get(k);

                if(k==list2.size()-1){
                    k=0;
                }
                else{
                    k++;
                }
                button2.setBackgroundColor(newColor);
                checker.remove(1);
                checker.add(1, newColor);


            }
        });


        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int newColor=list2.get(l);

                if(l==list2.size()-1){
                    l=0;
                }
                else{
                    l++;
                }

               // int newColor=list.get(rnd.nextInt(list.size()));
                button3.setBackgroundColor(newColor);
                checker.remove(2);
                checker.add(2, newColor);


            }
        });


        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                int newColor=list2.get(m);

                if(m==list2.size()-1){
                    m=0;
                }
                else{
                    m++;
                }
                button4.setBackgroundColor(newColor);
                checker.remove(3);
                checker.add(3, newColor);

            }
        });



        i= Integer.parseInt(name);

        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

               if (checker.equals(list))
              {
                  i++;
                  TextView tv = (TextView) findViewById(R.id.textView4);
                  tv.setText(String.valueOf(i));


                  Intent myIntent = new Intent(Game.this, won.class);
                  //myIntent.putExtra("score", tv.getText().toString());

                  SharedPreferences preferences = getSharedPreferences("file", Context.MODE_PRIVATE);
                  SharedPreferences.Editor editor = preferences.edit();
                  editor.putString("points", String.valueOf(i));
                  editor.commit();

                  startActivity(myIntent);
                  finish();
                }
                else
               {
                   TextView tv2 = (TextView) findViewById(R.id.textView4);
                   tv2.setText(String.valueOf(i));

                   Intent myIntent = new Intent(Game.this, lost.class);

                   SharedPreferences preferences = getSharedPreferences("file", Context.MODE_PRIVATE);
                   SharedPreferences.Editor editor = preferences.edit();
                   editor.putString("points", String.valueOf(i));
                   editor.commit();

                //  myIntent.putExtra("score", tv2.getText().toString());
                  startActivity(myIntent);
                   finish();

               }


            }
        });

        final Timer t = new Timer();
        final Timer s = new Timer();
        //Set the schedule function and rate
        t.scheduleAtFixedRate(new TimerTask() {
           public void run() {
               runOnUiThread(new Runnable() {

                   @Override
                   public void run() {
                       TextView tv = (TextView) findViewById(R.id.time);
                        tv.setText(String.valueOf(seconds));
                       seconds -= 1;

                        if(seconds == -1)
                       {
                        button1.setBackgroundColor(Color.GRAY);
                        button2.setBackgroundColor(Color.GRAY);
                        button3.setBackgroundColor(Color.GRAY);
                        button4.setBackgroundColor(Color.GRAY);
                        //   seconds = 15;
                           t.cancel();
//                           s.scheduleAtFixedRate(new TimerTask() {
//                               public void run() {
//                                   runOnUiThread(new Runnable() {
//
//                                       @Override
//                                       public void run() {
//                                           TextView tv = (TextView) findViewById(R.id.time);
//                                           tv.setText(String.valueOf(seconds));
//                                           seconds -= 1;
//
//                                           if(seconds == -1)
//                                           {
//                                               s.cancel();
//                                               Intent intent = new Intent(Game.this, lost.class);
//                                               startActivity(intent);
//                                           }
//
//                                       }
//
//                                   });
//                               }
//
//                           }, 0, 1000);
                       }

                   }

                });
            }

       }, 0, 1000);
   }

   }





