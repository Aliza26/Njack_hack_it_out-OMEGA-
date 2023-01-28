package com.arcreations.pdfpencil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {



    private TextView textView;
    private Handler handler;
    private long startTime, currentTime, finishedTime = 0L;
    private int duration = 22000 / 4;// 1 character is equal to 1 second. if want to
    // reduce. can use as divide
    // by 2,4,8
    private int endTime = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        this.getSupportActionBar().hide();

        textView = findViewById(R.id.splashText);
        textView.setText("       PDF Pencil       ");// length of string is 22
        String splashText = textView.getText().toString();


        handler = new Handler();
        startTime = System.currentTimeMillis();
        currentTime = startTime;

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                currentTime = System.currentTimeMillis();
                finishedTime = currentTime - startTime;

                if (finishedTime >= duration + 30) {

                    Intent i = new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(i);
                } else {
                    endTime = (int) (finishedTime / 350);// divide this by
                    // 1000,500,250,125
                    Spannable spannableString = new SpannableString(splashText);
                    spannableString.setSpan(new ForegroundColorSpan(Color.parseColor( "#964B00")), 0, endTime, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                    textView.setText(spannableString);
                    handler.postDelayed(this, 10);
                }
            }
        }, 10);



    }
}