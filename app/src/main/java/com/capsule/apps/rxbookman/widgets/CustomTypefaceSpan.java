package com.capsule.apps.rxbookman.widgets;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Parcel;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;

/**
 * @Author: larry
 * History: 12/21/15.
 */
public class CustomTypefaceSpan extends TypefaceSpan {

    private final Typeface mNewType;

    public CustomTypefaceSpan(String family, Typeface newType) {
        super(family);
        mNewType = newType;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        applyCustomTypeface(ds, mNewType);
    }

    @Override
    public void updateMeasureState(TextPaint paint) {
        applyCustomTypeface(paint, mNewType);
    }

    private static void applyCustomTypeface(Paint paint, Typeface typeface) {
        int oldStyle;
        Typeface oldTypeface = paint.getTypeface();
        if (oldTypeface == null) {
            oldStyle = 0;
        } else {
            oldStyle = oldTypeface.getStyle();
        }

        int fakeStyle = oldStyle &~typeface.getStyle();
        if ((fakeStyle & Typeface.BOLD) != 0) {
            paint.setFakeBoldText(true);
        }

        if ((fakeStyle & Typeface.ITALIC) != 0) {
            paint.setTextSkewX(-0.25f);
        }
        paint.setTypeface(typeface);
    }
}
