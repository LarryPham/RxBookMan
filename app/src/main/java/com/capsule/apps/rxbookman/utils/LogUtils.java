package com.capsule.apps.rxbookman.utils;

import android.util.Log;

import com.capsule.apps.rxbookman.BuildConfig;
import com.capsule.apps.rxbookman.Properties;

/**
 * @Author: larry
 * History: 12/15/15.rx
 */
public class LogUtils {
    private static final int LOG_PREFIX_LENGTH = Properties.PREFIX.length();
    private static final int MAX_LOG_TAG_LENGTH = 23;

    protected LogUtils() {
        throw new AssertionError("Cannot instantiate this class");
    }

    public static String makeLogTag(String str) {
        return Properties.PREFIX + str;
    }

    public static String makeLogTag(Class clazz) {
        return makeLogTag(clazz.getSimpleName());
    }

    public static void LOGD(final String tag, String message) {
        if (BuildConfig.DEBUG || Log.isLoggable(tag, Log.DEBUG)) {
            Log.d(tag, message);
        }
    }

    public static void LOGD(final String tag, String message, Throwable cause) {
        if (BuildConfig.DEBUG || Log.isLoggable(tag, Log.DEBUG)) {
            Log.d(tag, message, cause);
        }
    }

    public static void LOGV(final String tag, String message) {
        if (BuildConfig.DEBUG || Log.isLoggable(tag, Log.VERBOSE)) {
            Log.v(tag, message);
        }
    }

    public static void LOGV(final String tag, String message, Throwable cause) {
        if (BuildConfig.DEBUG || Log.isLoggable(tag, Log.VERBOSE)) {
            Log.v(tag, message, cause);
        }
    }

    public static void LOGI(final String tag, String message) {
        Log.i(tag, message);
    }

    public static void LOGI(final String tag, String message, Throwable cause) {
        Log.v(tag, message, cause);
    }

    public static void LOGW(final String tag, String message) {
        Log.w(tag, message);
    }

    public static void LOGW(final String tag, String message, Throwable cause) {
        Log.w(tag, message, cause);
    }

    public static void LOGE(final String tag, String message) {
        Log.e(tag, message);
    }

    public static void LOGE(final String tag, String message, Throwable cause) {
        Log.e(tag, message, cause);
    }
}