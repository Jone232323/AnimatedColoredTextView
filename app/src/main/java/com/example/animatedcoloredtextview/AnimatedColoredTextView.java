package com.example.animatedcoloredtextview;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import java.util.Locale;

public class AnimatedColoredTextView {

    private Context context;

    private TextView animatedColoredTextView;

    AnimatedColoredTextView(Context context, TextView textView)
    {
        this.context=context;

        animatedColoredTextView=textView;
    }

    public void move(float x, float y)
    {
        animatedColoredTextView.setX(x);
        animatedColoredTextView.setY(y);
    }

    public void setText(String text)
    {
        animatedColoredTextView.setText(text);
    }

    public void setColorEnglishAndRussianLocales(int colorEnglishLocale, int colorRussianLocale)
    {
        animatedColoredTextView.getText();
        animatedColoredTextView.setTextColor(colorRussianLocale);

        String localesEnglish;
        localesEnglish=(String)animatedColoredTextView.getText();
        localesEnglish=localesEnglish.toUpperCase();

        int numberOfEnglishletters=localesEnglish.replaceAll("[А-Я\\d]","").length();

        Spannable spannable= new SpannableString(localesEnglish);
        spannable.setSpan(new ForegroundColorSpan(colorEnglishLocale),0,numberOfEnglishletters,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        animatedColoredTextView.setText(spannable);
    }



}
