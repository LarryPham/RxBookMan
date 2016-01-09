package com.capsule.apps.rxbookman.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import retrofit.http.Path;

/**
 * @Author: larry
 * History: 12/16/15.
 */
public class Status implements Serializable, Parcelable {

    private int mErrorCode;
    private float mTime;
    private long mTotal;
    private int mPage;

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

    public long getTotal() {
        return mTotal;
    }

    public void setTotal(long total) {
        mTotal = total;
    }

    public int getPage() {
        return mPage;
    }

    public void setPage(int page) {
        mPage = page;
    }

    public Status() {

    }

    protected Status(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mErrorCode);
        dest.writeFloat(mTime);
        dest.writeLong(mTotal);
        dest.writeInt(mPage);
    }

    public void readFromParcel(Parcel source) {
        this.mErrorCode = source.readInt();
        this.mTime = source.readFloat();
        this.mTotal = source.readLong();
        this.mPage = source.readInt();
    }

    public static final Creator<Status> CREATOR = new Creator<Status>() {
        @Override
        public Status createFromParcel(Parcel in) {
            return new Status(in);
        }

        @Override
        public Status[] newArray(int size) {
            return new Status[size];
        }
    };

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = mErrorCode != -1 ? prime * result + String.valueOf(mErrorCode).hashCode() : prime * result;
        result = mTime != 0 ? prime * result + String.valueOf(mTime).hashCode() : prime * result;
        result = mTotal != 0 ? prime * result + String.valueOf(mTotal).hashCode() : prime * result;
        result = mPage != 0 ? prime * result + String.valueOf(mPage).hashCode() : prime * result;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }

        final Status other = (Status) obj;
        return (this.mErrorCode == other.mErrorCode) && (this.mTotal == other.mTotal) && (this.mTime == other.mTime)
                && (this.mPage == other.mPage);
    }

    public boolean isSameStatus(Status other) {
        return (this.mErrorCode == other.mErrorCode) && (this.mTotal == other.mTotal) && (this.mTime == other.mTime)
                && (this.mPage == other.mPage);
    }
}
