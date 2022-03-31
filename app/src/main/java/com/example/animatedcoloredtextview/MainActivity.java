package com.example.animatedcoloredtextview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnimatedColoredTextView animatedColoredTextView=new AnimatedColoredTextView(this);
        animatedColoredTextView.initHelloTextView(findViewById(R.id.helloTextView));
        animatedColoredTextView.initContainer(findViewById(R.id.container));
        animatedColoredTextView.setText("hello привет");








    }

}