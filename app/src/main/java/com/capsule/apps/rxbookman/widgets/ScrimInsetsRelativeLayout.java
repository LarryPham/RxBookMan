package com.capsule.apps.rxbookman.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.capsule.apps.rxbookman.R;
import com.capsule.apps.rxbookman.utils.LogUtils;

public class ScrimInsetsRelativeLayout extends RelativeLayout {

    private static final String TAG = LogUtils.makeLogTag(ScrimInsetsRelativeLayout.class);

    private Drawable mInsetForeground;
    private Rect mInsets;
    private Rect mTempRect = new Rect();
    protected OnInsetsCallback mOnInsetsCallback;

    public ScrimInsetsRelativeLayout(Context context) {
        super(context);
        init(context, null, 0);
    }

    public ScrimInsetsRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public ScrimInsetsRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ScrimInsetsView, defStyle, 0);
        if (array == null) {
            return;
        }

        mInsetForeground = array.getDrawable(R.styleable.ScrimInsetsView_InsetForeground);
        array.recycle();
        setWillNotDraw(true);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        int width = getWidth();
        int height = getHeight();

        if (mInsets != null && mInsetForeground != null) {
            int sc = canvas.save();
            canvas.translate(getScrollX(), getScrollY());

            LogUtils.LOGD(TAG, "Drawing the top of Insets rectangle with new translated canvas");
            // Top
            mTempRect.set(0, 0, width, mInsets.top);
            mInsetForeground.setBounds(mTempRect);
            mInsetForeground.draw(canvas);

            LogUtils.LOGD(TAG, "Drawing the bottom of Insets rectangle with new translated canvas");

            // bottom
            mTempRect.set(0, height - mInsets.bottom, width, height);
            mInsetForeground.setBounds(mTempRect);
            mInsetForeground.draw(canvas);

            LogUtils.LOGD(TAG, "Drawing the left side of Insets rectangle with new translated canvas");
            // left
            mTempRect.set(0, mInsets.top, mInsets.left, height - mInsets.bottom);
            mInsetForeground.setBounds(mTempRect);
            mInsetForeground.draw(canvas);

            LogUtils.LOGD(TAG, "Drawing the right side of Insets rectangle with new translated canvas");
            // Right
            mTempRect.set(width - mInsets.right, mInsets.top, width, height - mInsets.bottom);
            mInsetForeground.setBounds(mTempRect);
            mInsetForeground.draw(canvas);

            canvas.restoreToCount(sc);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mInsetForeground != null) {
            mInsetForeground.setCallback(this);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mInsetForeground != null) {
            mInsetForeground.setCallback(null);
        }
    }

    /**
     * Allows the calling container to specify a callback for custom processing when insets change
     * (i.e when {@link #fitSystemWindows(android.graphics.Rect)} is called. This is useful for setting padding on UI
     * elements based on UI chrome insets(e.g a Google Map or a ListView). When using with ListView or GridView, remember to set
     * clipToPadding to false.
     */
    public void setOnInsetsCallback(OnInsetsCallback callback) {
        this.mOnInsetsCallback = callback;
    }

    public interface OnInsetsCallback {
        public void onInsetsChanged(Rect insets);
    }
}
