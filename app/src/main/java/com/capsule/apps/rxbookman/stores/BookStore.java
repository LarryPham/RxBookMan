package com.capsule.apps.rxbookman.stores;

import android.util.ArrayMap;
import android.util.Log;

import com.capsule.apps.rxbookman.actions.BookAppActions;
import com.capsule.apps.rxbookman.actions.Keys;
import com.capsule.apps.rxbookman.models.Book;
import com.capsule.apps.rxbookman.models.Books;
import com.capsule.apps.rxbookman.models.ShortBook;
import com.capsule.apps.rxbookman.models.ShortBooks;
import com.capsule.apps.rxbookman.models.Status;
import com.capsule.apps.rxbookman.models.results.BookResult;
import com.capsule.apps.rxbookman.models.results.BooksResult;
import com.capsule.apps.rxbookman.utils.CollectionsUtil;
import com.capsule.apps.rxbookman.utils.LogUtils;
import com.techiedb.apps.rxflux.action.RxAction;
import com.techiedb.apps.rxflux.dispatcher.Dispatcher;
import com.techiedb.apps.rxflux.store.RxStore;

import java.util.ArrayList;

public class BookStore extends RxStore implements BookStoreInterface {

    private static final String TAG = LogUtils.makeLogTag(BookStore.class);
    public static final String ID = "BookStore";

    private static BookStore sBookStore;

    private ArrayMap<String, ShortBooks> mShortBooks;
    private ArrayMap<String, Books> mBooks;
    private ArrayMap<String, Status> mStatuses;

    public static synchronized BookStore get(Dispatcher dispatcher) {
        if (sBookStore == null) {
            sBookStore = new BookStore(dispatcher);
        }
        return sBookStore;
    }

    public BookStore(Dispatcher dispatcher) {
        super(dispatcher);
        mShortBooks = CollectionsUtil.newArrayMap();
        mBooks = CollectionsUtil.newArrayMap();
        mStatuses = CollectionsUtil.newArrayMap();
    }

    @Override
    public void onRxAction(RxAction action) {
        final String type = action.getType();
        switch (type) {
            case BookAppActions.SEARCH_BOOK_BY_KEYWORD: {
                LogUtils.LOGD(TAG, "{HandleAction} - SEARCH_BOOK_BY_KEYWORD");
                final BooksResult result = (BooksResult) action.getData().get(Keys.SHORT_BOOKS);
                final Status status = new Status();

                if (result.getBooks() != null && result.getBooks().size() > 0) {
                    LogUtils.LOGD(TAG, String.format("[SearchBookByKeyword]: %s", result.getBooks().toString()));
                    mShortBooks.put("search_book_by_keyword", result.getBooks());
                    mStatuses.put("search_book_by_keyword_status", status);
                } else {
                    LogUtils.LOGD(TAG, String.format("[SearchBookByKeyWord-Error]: %s", status.toString()));
                    mStatuses.put("search_book_by_keyword_error", status);
                }
                break;
            }
            case BookAppActions.SEARCH_BOOK_BY_KEYWORD_PAGE: {
                LogUtils.LOGD(TAG, "{HandleAction} - SEARCH_BOOK_BY_KEYWORD_PAGE");
                final BooksResult result = (BooksResult) action.getData().get(Keys.SHORT_BOOKS);
                final Status status = new Status();
                if (result.getBooks() != null && result.getBooks().size() > 0) {
                    LogUtils.LOGD(TAG, String.format("[SearchBookByKeywordPage]: %s", result.getBooks().toString()));
                    mShortBooks.put("search_book_by_keyword_page", result.getBooks());
                    mStatuses.put("search_book_by_keyword_page_status", status);
                } else {
                    LogUtils.LOGD(TAG, String.format("[SearchBookByKeyWordPage-Error]: %s", status.toString()));
                    mStatuses.put("search_book_by_keyword_page_error", status);
                }
                break;
            }
            case BookAppActions.GET_BOOK_DETAIL_BY_ID: {
                LogUtils.LOGD(TAG, "{HandleAction} - GET_BOOK_DETAIL_BY_ID");
                final BookResult result = (BookResult) action.getData().get(Keys.BOOK);
                final Status status = new Status();

                long bookId = result.getBookId();
                if (bookId >= 0) {
                    final Book book = new Book(result);
                    LogUtils.LOGD(TAG, String.format("GettingBookDetailById: %s", book.toString()));
                    if (mBooks.get("get_book_detail_by_id") != null) {
                        mBooks.get("get_book_detail_by_id").add(book);
                    } else {
                        final Books books = new Books();
                        books.add(book);
                        mBooks.put("get_book_detail_by_id", books);
                    }
                } else {
                    LogUtils.LOGD(TAG, String.format("GettingBookDetailById: %s", status.toString()));
                    mStatuses.put("get_book_detail_by_id_error", status);
                }
                break;
            }
        }
    }

    @Override
    public Book getBook(String key, long bookId) {
        return mBooks.get(key).findBookById(bookId);
    }

    @Override
    public Books getListBook(String key) {
        return mBooks.get(key);
    }

    @Override
    public Status geStatus(String key) {
        return mStatuses.get(key);
    }

    @Override
    public ArrayList<Status> getStatusList() {
        return CollectionsUtil.newArrayList();
    }

    @Override
    public ShortBooks getShortBooks(String key) {
        return mShortBooks.get(key);
    }
}
