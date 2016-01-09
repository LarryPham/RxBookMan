package com.capsule.apps.rxbookman.models;

/**
 * @Author: larry
 * History: 12/16/15.
 */
public class AppDataModel {
    protected Books mBooks = new Books();

    protected ShortBooks mShortBooks = new ShortBooks();
    protected ShortBooks mHomeShortBooks = new ShortBooks();

    public AppDataModel() {

    }

    public Books getBooks() {
        return mBooks;
    }

    public void setBooks(Books books) {
        mBooks = books;
    }

    public ShortBooks getShortBooks() {
        return mShortBooks;
    }

    public void setShortBooks(ShortBooks shortBooks) {
        mShortBooks = shortBooks;
    }

    public ShortBooks getHomeShortBooks() {
        return mHomeShortBooks;
    }

    public void setHomeShortBooks(ShortBooks homeShortBooks) {
        mHomeShortBooks = homeShortBooks;
    }
}
