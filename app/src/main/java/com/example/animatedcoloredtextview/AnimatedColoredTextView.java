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

    private Context context;
    private TextView animatedColoredTextView;
    private Timer timerAnimation;

    private boolean downMove=true;
    private boolean firstActivationOfAnimation=false;

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
                timerAnimation.cancel();
            }
        });
    }

    public void  initContainer(ConstraintLayout container)
    {
        container.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(firstActivationOfAnimation)timerAnimation.cancel();

                setColorEnglishAndRussianLocales(Color.RED,Color.BLUE);

                startAnimation(container.getHeight());

                move(motionEvent.getX(),motionEvent.getY());
                return false;
            }
        });
    }
    public void move(float x, float y)
    {
        animatedColoredTextView.setX(x-animatedColoredTextView.getWidth()/2);
        animatedColoredTextView.setY(y-animatedColoredTextView.getHeight()/2);
    }

    public void setText(String text)
    {
        animatedColoredTextView.setText(text.toUpperCase());
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
        firstActivationOfAnimation=true;
        TranslateAnimation animationPause=new TranslateAnimation(
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, 0);
        animationPause.setDuration(5000);

        animationPause.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {


            }

            @Override
            public void onAnimationEnd(Animation animation) {

                timerAnimation=new Timer(true);
                timerAnimation.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {

                        if(animatedColoredTextView.getY()+animatedColoredTextView.getHeight()<sizeOfTheMovingArea&&downMove)
                        {animatedColoredTextView.setY(animatedColoredTextView.getY()+10);}
                        else{downMove=false;}

                        if(animatedColoredTextView.getY()>=0&&downMove==false) {
                            animatedColoredTextView.setY(animatedColoredTextView.getY() - 10);}
                        else{downMove=true;}
                    }
                },0,50);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animatedColoredTextView.startAnimation(animationPause);

    }



}

