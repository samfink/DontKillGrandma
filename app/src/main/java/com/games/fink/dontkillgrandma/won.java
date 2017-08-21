package com.games.fink.dontkillgrandma;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class won extends AppCompatActivity {

    public int seconds =4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won);

        int[] sounds={R.raw.thankyou};

       AdView adView = (AdView) findViewById(R.id.adView);
       AdRequest adRequest = new AdRequest.Builder().build();

       adView.loadAd(adRequest);

        SharedPreferences preferences = getSharedPreferences("file", Context.MODE_PRIVATE);
        String name = preferences.getString("points", "0");

        SharedPreferences preferences2 = getSharedPreferences("savedscore", Context.MODE_PRIVATE);
        String hs = preferences2.getString("highscore", "0");

        final TextView tv = (TextView) findViewById(R.id.textView6);
        tv.setText(name);
        if(Integer.valueOf(name)>Integer.valueOf(hs))
        {
            SharedPreferences.Editor editor = preferences2.edit();
            editor.putString("highscore", String.valueOf(name));
            editor.commit();
        }

        final Timer t = new Timer();

        Random r = new Random();
        int Low = 0;
        int High = 1;
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
                            Intent intent = new Intent(won.this, Game.class);
                           // intent.putExtra("score", tv.getText().toString());
                            startActivity(intent);
                            finish();
                        }

                    }

                });
            }

        }, 0, 1000);
    }
}
