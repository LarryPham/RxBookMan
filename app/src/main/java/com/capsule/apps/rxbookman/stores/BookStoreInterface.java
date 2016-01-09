package com.capsule.apps.rxbookman.stores;

import android.util.ArrayMap;

import com.capsule.apps.rxbookman.models.Book;
import com.capsule.apps.rxbookman.models.AppDataModel;
import com.capsule.apps.rxbookman.models.Books;
import com.capsule.apps.rxbookman.models.ShortBooks;
import com.capsule.apps.rxbookman.models.Status;

import java.util.ArrayList;

public interface BookStoreInterface {

    /**
     * Get a book from store which used to store into the database or {@link AppDataModel}'s instance.
     * After sending the request from client to server, the result will be parsed and stored into
     * <code>BookStore</code>'s instance
     * @param key The Book's hashed-key into array-map.
     * @param bookId The Book's id.
     * @return The Book's instance.
     */
    Book getBook(String key, long bookId);

    /**
     * Get the list of books which stored into the BookStore or {@link AppDataModel}'s instance.
     * @return The ArrayList of Book.
     */
    Books getListBook(String key);

    /**
     * Get an status after sending the request from client to server for getting the data
     * @param key The key of status which stored into the ArrayMap
     * @return The Status's instance.
     */
    Status geStatus(String key);

    /**
     * Get the list of statues after sending requests from client to server for getting the data
     * @return The ArrayList of <code>Status</code>
     */
    ArrayList<Status> getStatusList();

    ShortBooks getShortBooks(String key);
}
