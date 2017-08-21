package com.games.fink.dontkillgrandma;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class lost extends AppCompatActivity {

    public int seconds =4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost);

       AdView adView = (AdView) findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
       adView.loadAd(adRequest);



        int[] sounds={R.raw.candy, R.raw.dreams, R.raw.grandson,R.raw.insurance,R.raw.killed,R.raw.legs,R.raw.lights, R.raw.present, R.raw.tictac, R.raw.browser, R.raw.family};

        final SharedPreferences preferences = getSharedPreferences("file", Context.MODE_PRIVATE);
        String name = preferences.getString("points", "0");

        final TextView tv = (TextView) findViewById(R.id.textView8);
        tv.setText(name);

        final Timer t = new Timer();

        Random r = new Random();
        int Low = 0;
        int High = 11;
        int rndm = r.nextInt(High-Low) + Low;
        MediaPlayer mp1 = MediaPlayer.create(getApplicationContext(), sounds[rndm]);
        mp1.start();

        t.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        seconds -= 1;

                        if(seconds == -1)
                        {
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.commit();
                            Intent intent = new Intent(lost.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    }

                });
            }

        }, 0, 1000);
    }
}
