package com.capsule.apps.rxbookman.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.capsule.apps.rxbookman.models.results.BookResult;

import java.io.Serializable;

/**
 * @Author: larry
 * History: 12/16/15.
 */
public class Book implements Serializable, Parcelable, Comparable<Book> {

    private static final long INVALID_ID = -1;
    private long mBookId;
    private String mTitle;
    private String mSubTitle;
    private String mDescription;
    private String mAuthor;
    private long mISBN;
    private int mYear;
    private int mPage;

    private String mPublisher;
    private String mImageUrl;
    private String mDownloadUrl;

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

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public long getISBN() {
        return mISBN;
    }

    public void setISBN(long ISBN) {
        mISBN = ISBN;
    }

    public int getYear() {
        return mYear;
    }

    public void setYear(int year) {
        mYear = year;
    }

    public int getPage() {
        return mPage;
    }

    public void setPage(int page) {
        mPage = page;
    }

    public String getPublisher() {
        return mPublisher;
    }

    public void setPublisher(String publisher) {
        mPublisher = publisher;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public String getDownloadUrl() {
        return mDownloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        mDownloadUrl = downloadUrl;
    }

    public Book(BookResult result) {
        this.mBookId = result.getBookId();
        this.mTitle = result.getTitle();
        this.mSubTitle = result.getSubTitle();
        this.mDescription = result.getDescription();
        this.mAuthor = result.getAuthor();
        this.mISBN = result.getISBN();
        this.mYear = result.getYear();
        this.mPublisher = result.getPublisher();
        this.mImageUrl = result.getImageUrl();
        this.mDownloadUrl = result.getDownload();
    }

    public Book(long bookId, String title, String subTitle, String description, String author, long ISBN,
                int year, int page, String publisher, String imageUrl, String downloadUrl) {
        mBookId = bookId;
        mTitle = title;
        mSubTitle = subTitle;
        mDescription = description;
        mAuthor = author;
        mISBN = ISBN;
        mYear = year;
        mPage = page;
        mPublisher = publisher;
        mImageUrl = imageUrl;
        mDownloadUrl = downloadUrl;
    }

    protected Book(Parcel in) {
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
        dest.writeString(this.mAuthor);
        dest.writeString(this.mAuthor);
        dest.writeLong(this.mISBN);
        dest.writeString(this.mPublisher);
        dest.writeString(this.mImageUrl);
        dest.writeString(this.mDownloadUrl);
    }

    public void readFromParcel(Parcel source) {
        this.mBookId = source.readLong();
        this.mTitle = source.readString();
        this.mSubTitle = source.readString();
        this.mDescription = source.readString();
        this.mAuthor = source.readString();
        this.mISBN = source.readLong();
        this.mYear = source.readInt();
        this.mPage = source.readInt();
        this.mPublisher = source.readString();
        this.mImageUrl = source.readString();
        this.mDownloadUrl = source.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };


    @Override
    public int compareTo(@NonNull Book another) {
        return this.mTitle.compareToIgnoreCase(another.mTitle);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = mBookId != INVALID_ID ? prime * result + String.valueOf(mBookId).hashCode() : prime * result;
        result = !TextUtils.isEmpty(mTitle) ? prime * result + mTitle.hashCode() : prime * result;
        result = !TextUtils.isEmpty(mSubTitle) ? prime * result + mSubTitle.hashCode() : prime * result;
        result = !TextUtils.isEmpty(mDescription) ? prime * result + mDescription.hashCode() : prime * result;
        result = !TextUtils.isEmpty(mAuthor) ? prime * result + mImageUrl.hashCode() : prime * result;
        result = !TextUtils.isEmpty(mPublisher) ? prime * result + mImageUrl.hashCode() : prime * result;
        result = !TextUtils.isEmpty(mImageUrl) ? prime * result + mImageUrl.hashCode() : prime * result;
        result = !TextUtils.isEmpty(mDownloadUrl) ? prime * result + mImageUrl.hashCode() : prime * result;

        result = mISBN != 0 ? prime * result + String.valueOf(mISBN).hashCode() : prime * result;
        return super.hashCode();
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

        final Book other = (Book) obj;
        return (this.mBookId == other.mBookId) && (TextUtils.equals(this.mTitle, other.mTitle)) && (TextUtils.equals(this.mSubTitle, other.mSubTitle))
                && (TextUtils.equals(this.mDescription, other.mDescription)) && (this.mISBN == other.mISBN);
    }

    public boolean isSameBook(Book other) {
        return (this.mBookId == other.mBookId) && (TextUtils.equals(this.mTitle, other.mTitle)) && (TextUtils.equals(this.mSubTitle, other.mSubTitle))
                && (TextUtils.equals(this.mDescription, other.mDescription)) && (this.mISBN == other.mISBN);
    }
}
