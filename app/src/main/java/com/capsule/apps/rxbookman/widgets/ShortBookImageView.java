package com.capsule.apps.rxbookman.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @Author: larry
 * History: 12/22/15.
 */
public class ShortBookImageView extends ImageView {

    public ShortBookImageView(Context context) {
        super(context);
    }

    public ShortBookImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ShortBookImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int widthSize = Math.max(getMeasuredWidth(), getMeasuredHeight());
        final int heightSize = ((4 * widthSize)/3);
        setMeasuredDimension(widthSize, heightSize);
    }
}
