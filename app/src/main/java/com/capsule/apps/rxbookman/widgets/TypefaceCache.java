package com.capsule.apps.rxbookman.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.capsule.apps.rxbookman.R;
import com.capsule.apps.rxbookman.utils.CollectionsUtil;

import java.util.Hashtable;

/**
 * @Author: larry
 * History: 12/15/15.
 *
 * The TypefaceCache used to store the recently font used into the TextView's instance. For rendering the TextView's instance with customized Typeface font.
 * Using instance of <code>TypefaceCache</code> to store the customized cached typeface for TextView or customise buttons when we want to visualize some
 * components which are descendants from TextView into current app.
 */
public class TypefaceCache {

    public static final int VARIATION_NORMAL = 0;
    public static final int VARIATION_LIGHT = 1;
    public static final int VARIATION_BOOK = 2;
    public static final int VARIATION_MEDIUM = 3;
    public static final int VARIATION_SEMI = 4;

    public static final int VARIATION_THIN = 5;
    public static final int VARIATION_ULTRA = 6;

    private static final Hashtable<String, Typeface> mTypefaceCache = CollectionsUtil.newHashTable();

    public static Typeface getTypeface(Context context) {
        return getTypeface(context, Typeface.NORMAL, VARIATION_NORMAL);
    }

    public static Typeface getTypeface(Context context, int fontStyle, int variation) {
        if (context == null) {
            return null;
        }

        String typefaceName = "FiraSans-Regular.ttf";
        if (variation == VARIATION_LIGHT) {
            switch (fontStyle) {
                case Typeface.ITALIC: {
                    typefaceName = "FiraSans-LightItalic.ttf";
                    break;
                }
                default: {
                    typefaceName = "FiraSans-Light.ttf";
                    break;
                }
            }
        } else if (variation == VARIATION_BOOK) {
            switch (fontStyle) {
                case Typeface.ITALIC: {
                    typefaceName = "FiraSans-BookItalic.ttf";
                    break;
                }
                default: {
                    typefaceName = "FiraSans-Book.ttf";
                    break;
                }
            }
        } else if (variation == VARIATION_MEDIUM) {
            switch (fontStyle) {
                case Typeface.ITALIC: {
                    typefaceName = "FiraSans-MediumItalic.ttf";
                    break;
                }
                default: {
                    typefaceName = "FiraSans-Medium.ttf";
                    break;
                }
            }
        } else if (variation == VARIATION_SEMI) {
            switch (fontStyle) {
                case Typeface.BOLD_ITALIC: {
                    typefaceName = "FiraSans-SemiBoldItalic.ttf";
                    break;
                }
                default: {
                    typefaceName = "FiraSans-SemiBold.ttf";
                    break;
                }
            }
        } else if (variation == VARIATION_THIN) {
            typefaceName = "FiraSans-Thin.ttf";
        } else if (variation == VARIATION_ULTRA) {
            typefaceName = "FiraSans-Ultra.ttf";
        } else {
            switch (fontStyle) {
                case Typeface.BOLD: {
                    typefaceName = "FiraSans-Bold.ttf";
                    break;
                }
                case Typeface.BOLD_ITALIC: {
                    typefaceName = "FiraSans-BoldItalic.ttf";
                    break;
                }
                case Typeface.ITALIC: {
                    typefaceName = "FiraSans-Italic.ttf";
                    break;
                }
                default: {
                    typefaceName = "FiraSans-Regular.ttf";
                    break;
                }
            }
        }

        return getTypefaceForTypefaceName(context, typefaceName);
    }

    private static Typeface getTypefaceForTypefaceName(Context context, String typefaceName) {
        if (!mTypefaceCache.containsKey(typefaceName)) {
            Typeface typeface = Typeface.createFromAsset(context.getApplicationContext().getAssets(), "Fonts/" + typefaceName);
            if (typeface != null) {
                mTypefaceCache.put(typefaceName, typeface);
            }
        }
        return mTypefaceCache.get(typefaceName);
    }

    /**
     * Sets the typeface for TextView (or TextView descendant such as EditText or Button) based on the passed attributes, defaults to normal typeface
     * @param context The Application Context.
     * @param textView The TextView's instance.
     * @param attrs The passed TextView's attributes.
     */
    public static void setCustomTypeface(Context context, TextView textView, AttributeSet attrs) {
        if (context == null || textView == null) {
            return;
        }

        if (textView.isInEditMode()) {
            return;
        }

        int variation = TypefaceCache.VARIATION_NORMAL;
        if (attrs != null) {
            TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.OpenTextView, 0, 0);
            if (array != null) {
                try {
                    variation = array.getInteger(R.styleable.OpenTextView_fontVariation, TypefaceCache.VARIATION_NORMAL);
                } finally {
                    array.recycle();
                }
            }
        }

        final int fontStyle;
        if (textView.getTypeface() != null) {
            boolean isBold = textView.getTypeface().isBold();
            boolean isItalic = textView.getTypeface().isItalic();

            if (isBold && isItalic) {
                fontStyle = Typeface.BOLD_ITALIC;
            } else if (isBold) {
                fontStyle = Typeface.BOLD;
            } else if (isItalic) {
                fontStyle = Typeface.ITALIC;
            } else {
                fontStyle = Typeface.NORMAL;
            }
        } else {
            fontStyle = Typeface.NORMAL;
        }

        Typeface typeface = getTypeface(context, fontStyle, variation);
        if (typeface != null) {
            textView.setTypeface(typeface);
        }
    }
}
