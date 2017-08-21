package com.games.fink.dontkillgrandma;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static com.games.fink.dontkillgrandma.R.id.button6;

public class highscore extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        SharedPreferences preferences2 = getSharedPreferences("savedscore", Context.MODE_PRIVATE);
        String hs = preferences2.getString("highscore", "0");

        final TextView tv = (TextView) findViewById(R.id.textView11);
        tv.setText(hs);

    }

    public void back(View view) {
        Intent intent = new Intent(this, MainActivity.class);
       startActivity(intent);
       finish();

   }

}
