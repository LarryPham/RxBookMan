package com.capsule.apps.rxbookman.widgets;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.capsule.apps.rxbookman.utils.LogUtils;

public class OpenViewPager extends ViewPager {

    private static final String TAG = LogUtils.makeLogTag(OpenViewPager.class);

    public OpenViewPager(Context context) {
        super(context);
    }

    public OpenViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        try {
            return super.onInterceptTouchEvent(event);
        } catch (IllegalArgumentException ex) {
            LogUtils.LOGD(TAG, ex.toString());
            return false;
        }
    }

    /**
     * This method has been added blocks of code for resolving the issues "Pointer Index Out Of Range" bug in the compatibility lib
     **/
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            return super.onTouchEvent(event);
        } catch (IllegalArgumentException ex) {
            LogUtils.LOGD(TAG, ex.toString());
            return false;
        }
    }
}
