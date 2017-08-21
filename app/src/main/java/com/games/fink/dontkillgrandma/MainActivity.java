package com.games.fink.dontkillgrandma;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-5368904009471412~7529177888");

      AdView adView = (AdView) findViewById(R.id.adView3);

       AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, Instructions.class);
        startActivity(intent);
        finish();
    }

    public void startGame(View view) {
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
        finish();
    }

    public void hs(View v) {
        Intent intent = new Intent(this, highscore.class);
        startActivity(intent);
        finish();

    }


    public void exit(View view) {
        finish();

    }

}
