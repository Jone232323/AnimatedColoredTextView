package com.example.animatedcoloredtextview;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Timer;
import java.util.TimerTask;

public class AnimatedColoredTextView {

    private final long TIME_DELAY=5000;
    private final int MOVE_STEP=10;
    private final int PERIOD_FOR_TIMER_ANIMATION=50;

    private Context context;
    private TextView animatedColoredTextView;
    private Timer timerAnimation;

    private boolean downMove=true;
    private boolean firstActivationOfTimerAnimation=false;



    AnimatedColoredTextView(Context context)
    {
        this.context=context;
    }
    public void  initHelloTextView(TextView textView)
    {
        animatedColoredTextView=textView;
        animatedColoredTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(firstActivationOfTimerAnimation)timerAnimation.cancel();
            }
        });
    }

    public void  initContainer(ConstraintLayout container)
    {
        container.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(firstActivationOfTimerAnimation)timerAnimation.cancel();

                setColorEnglishAndRussianLocales(Color.RED,Color.BLUE);

                startAnimation(container.getHeight());

                move(motionEvent.getX(),motionEvent.getY());
                return false;
            }
        });
    }
    private void setColorEnglishAndRussianLocales(int colorEnglishLocale, int colorRussianLocale)
    {
        if(animatedColoredTextView.getCurrentTextColor()==Color.WHITE) {

            animatedColoredTextView.setTextColor(colorRussianLocale);

            String localesEnglish;
            localesEnglish = (String) animatedColoredTextView.getText();

            int numberOfEnglishletters = localesEnglish.replaceAll("[А-Я\\d]", "").length();

            Spannable spannable = new SpannableString(localesEnglish);
            spannable.setSpan(new ForegroundColorSpan(colorEnglishLocale), 0, numberOfEnglishletters, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            animatedColoredTextView.setText(spannable);
        }
    }
    private void startAnimation(float sizeOfTheMovingArea)
    {
        TranslateAnimation animationPause=new TranslateAnimation(
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, 0);
        animationPause.setDuration(TIME_DELAY);
        animationPause.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {


            }
            @Override
            public void onAnimationEnd(Animation animation) {

                startAnimationMoveDownOrMoveUp(sizeOfTheMovingArea);

            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        animatedColoredTextView.startAnimation(animationPause);
    }
    private void startAnimationMoveDownOrMoveUp(float sizeOfTheMovingArea)
    {
        firstActivationOfTimerAnimation=true;

        timerAnimation=new Timer(true);
        timerAnimation.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(animatedColoredTextView.getY()+animatedColoredTextView.getHeight()<=sizeOfTheMovingArea && downMove){
                    animatedColoredTextView.setY(animatedColoredTextView.getY()+MOVE_STEP);}
                else{downMove=false;}

                if(animatedColoredTextView.getY()>=0 && downMove==false) {
                    animatedColoredTextView.setY(animatedColoredTextView.getY() - MOVE_STEP);}
                else{downMove=true;}
            }
        },0,PERIOD_FOR_TIMER_ANIMATION);
    }

    private void move(float x, float y)
    {
        animatedColoredTextView.setX(x-animatedColoredTextView.getWidth()/2);
        animatedColoredTextView.setY(y-animatedColoredTextView.getHeight()/2);
    }
    public void setText(String text)
    {
        if(text.length()!=0){
            animatedColoredTextView.setText(text.toUpperCase());}
        else{
            animatedColoredTextView.setText("HELLO ПРИВЕТ");}
    }







}

