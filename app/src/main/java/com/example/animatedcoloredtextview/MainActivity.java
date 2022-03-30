package com.example.animatedcoloredtextview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnimatedColoredTextView animatedColoredTextView=new AnimatedColoredTextView(this,findViewById(R.id.helloTextView));
        animatedColoredTextView.setText("hello привет");
        animatedColoredTextView.setColorEnglishAndRussianLocales(Color.RED,Color.BLUE);


        ConstraintLayout container=(ConstraintLayout)findViewById(R.id.container);

        

    }
}