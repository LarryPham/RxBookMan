package com.capsule.apps.rxbookman.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.w3c.dom.Text;

import java.io.Serializable;

/**
 * @Author: larry
 * History: 12/16/15.
 */
public class ShortBook implements Serializable, Parcelable, Comparable<ShortBook> {

    private static final long INVALID_ID = -1;
    @Expose
    @SerializedName("ID")
    private long mBookId;

    @Expose
    @SerializedName("Title")
    private String mTitle;

    @Expose
    @SerializedName("SubTitle")
    private String mSubTitle;

    @Expose
    @SerializedName("Description")
    private String mDescription;

    @Expose
    @SerializedName("Image")
    private String mImageUrl;

    @Expose
    @SerializedName("isbn")
    private long mISBN;

    public long getBookId() {
        return mBookId;
    }

    public void setBookId(long bookId) {
        mBookId = bookId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getSubTitle() {
        return mSubTitle;
    }

    public void setSubTitle(String subTitle) {
        mSubTitle = subTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public long getISBN() {
        return mISBN;
    }

    public void setISBN(long ISBN) {
        mISBN = ISBN;
    }

    protected ShortBook() {

    }

    public ShortBook(ShortBook other) {
        this.mBookId = other.getBookId();
        this.mTitle = other.getTitle();
        this.mSubTitle = other.getSubTitle();
        this.mDescription = other.getDescription();
        this.mImageUrl = other.getImageUrl();
        this.mISBN = other.getISBN();
    }

    protected ShortBook(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.mBookId);
        dest.writeString(this.mTitle);
        dest.writeString(this.mSubTitle);
        dest.writeString(this.mDescription);
        dest.writeString(this.mImageUrl);
        dest.writeLong(this.mISBN);
    }

    public void readFromParcel(Parcel source) {
        this.mBookId = source.readLong();
        this.mTitle = source.readString();
        this.mSubTitle = source.readString();
        this.mDescription = source.readString();
        this.mImageUrl = source.readString();
        this.mISBN = source.readLong();
    }

    public static final Creator<ShortBook> CREATOR = new Creator<ShortBook>() {
        @Override
        public ShortBook createFromParcel(Parcel in) {
            return new ShortBook(in);
        }

        @Override
        public ShortBook[] newArray(int size) {
            return new ShortBook[size];
        }
    };

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = mBookId != INVALID_ID ? prime * result + String.valueOf(mBookId).hashCode(): prime * result;
        result = !TextUtils.isEmpty(mTitle) ? prime * result + mTitle.hashCode() : prime * result;
        result = !TextUtils.isEmpty(mSubTitle) ? prime * result + mSubTitle.hashCode() : prime * result;
        result = !TextUtils.isEmpty(mDescription) ? prime * result + mDescription.hashCode() : prime * result;
        result = !TextUtils.isEmpty(mImageUrl) ? prime * result + mImageUrl.hashCode() : prime * result;

        result = mISBN != 0 ? prime * result + String.valueOf(mISBN).hashCode() : prime * result;
        return result;
    }


    @Override
    public int compareTo(@NonNull  ShortBook another) {
        return this.mTitle.compareToIgnoreCase(another.mTitle);
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

        final ShortBook other = (ShortBook) obj;
        return (this.mBookId == other.mBookId) && (TextUtils.equals(this.mTitle, other.mTitle)) && (TextUtils.equals(this.mSubTitle, other.mSubTitle))
                && (TextUtils.equals(this.mDescription, other.mDescription)) && (this.mISBN == other.mISBN);
    }

    public boolean isSameBook(ShortBook other) {
        return (this.mBookId == other.mBookId) && (TextUtils.equals(this.mTitle, other.mTitle)) && (TextUtils.equals(this.mSubTitle, other.mSubTitle))
                && (TextUtils.equals(this.mDescription, other.mDescription)) && (this.mISBN == other.mISBN);
    }
}
