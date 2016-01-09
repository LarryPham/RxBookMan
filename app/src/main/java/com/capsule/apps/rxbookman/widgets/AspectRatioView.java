package com.capsule.apps.rxbookman.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.capsule.apps.rxbookman.R;

/**
 * Extension of FrameLayout that assumes a measured (none-zero) width and sets the height
 * according to the provided aspect ratio
 */
public class AspectRatioView extends FrameLayout {
    private float mAspectRatio = 0f;

    public AspectRatioView(Context context) {
        this (context, null, 0);
    }

    public AspectRatioView(Context context, AttributeSet attrs) {
        this (context, attrs, 0);
    }

    public AspectRatioView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.AspectRatioView, defStyleAttr, 0);
        mAspectRatio = array.getFloat(R.styleable.AspectRatioView_aspectRatio, 0);
        if (mAspectRatio == 0) {
            throw new IllegalArgumentException("You must specify an aspect ratio when suing the AspectRatioView");
        }
        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int width, height;
        if (mAspectRatio != 0) {
            width = widthSize;
            height = (int) (width / mAspectRatio);
            int exactWidthSize = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
            int exactHeightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
            super.onMeasure(exactWidthSize, exactHeightSpec);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
