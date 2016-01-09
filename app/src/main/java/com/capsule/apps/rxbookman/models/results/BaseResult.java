package com.capsule.apps.rxbookman.models.results;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @Author: larry
 * History: 12/16/15.
 */
public class BaseResult implements Serializable {

    @Expose
    @SerializedName("Error")
    private int mErrorCode;

    @Expose
    @SerializedName("Time")
    private float mTime;

    public BaseResult() {
    }

    public int getErrorCode() {
        return mErrorCode;
    }

    public void setErrorCode(int errorCode) {
        mErrorCode = errorCode;
    }

    public float getTime() {
        return mTime;
    }

    public void setTime(float time) {
        mTime = time;
    }
}
