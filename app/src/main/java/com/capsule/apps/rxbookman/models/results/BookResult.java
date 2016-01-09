package com.capsule.apps.rxbookman.models.results;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @Author: larry
 * History: 12/16/15.
 */
public class BookResult extends BaseResult {

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
    @SerializedName("Author")
    private String mAuthor;

    @Expose
    @SerializedName("ISBN")
    private long mISBN;

    @Expose
    @SerializedName("Year")
    private int mYear;

    @Expose
    @SerializedName("Page")
    private int mPage;

    @Expose
    @SerializedName("Publisher")
    private String mPublisher;

    @Expose
    @SerializedName("Image")
    private String mImageUrl;

    @Expose
    @SerializedName("Download")
    private String mDownload;

    public BookResult() {

    }

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

    public String getDownload() {
        return mDownload;
    }

    public void setDownload(String download) {
        mDownload = download;
    }
}
