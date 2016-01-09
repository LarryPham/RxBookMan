package com.capsule.apps.rxbookman.models.results;

import com.capsule.apps.rxbookman.models.ShortBooks;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @Author: larry
 * History: 12/16/15.
 */
public class BooksResult extends BaseResult {

    @Expose
    @SerializedName("Total")
    private long mTotal;

    @Expose
    @SerializedName("Page")
    private int mPage;

    @Expose
    @SerializedName("Books")
    private ShortBooks mBooks = new ShortBooks();

    private BooksResult() {

    }
    public ShortBooks getBooks() {
        return mBooks;
    }

    public void setBooks(ShortBooks books) {
        mBooks = books;
    }

    public int getPage() {
        return mPage;
    }

    public void setPage(int page) {
        mPage = page;
    }

    public long getTotal() {
        return mTotal;
    }

    public void setTotal(long total) {
        mTotal = total;
    }
}
