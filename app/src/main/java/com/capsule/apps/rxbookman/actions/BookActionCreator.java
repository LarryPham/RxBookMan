package com.capsule.apps.rxbookman.actions;

import com.capsule.apps.rxbookman.core.BookManService;
import com.capsule.apps.rxbookman.utils.LogUtils;
import com.techiedb.apps.rxflux.action.RxAction;
import com.techiedb.apps.rxflux.action.RxActionCreator;
import com.techiedb.apps.rxflux.dispatcher.Dispatcher;
import com.techiedb.apps.rxflux.utils.SubscriptionManager;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BookActionCreator extends RxActionCreator implements BookAppActions{

    private static final String TAG = LogUtils.makeLogTag(BookActionCreator.class);

    public BookActionCreator(Dispatcher dispatcher, SubscriptionManager manager) {
        super(dispatcher, manager);
    }

    @Override
    public void searchBooksByKeyword(String keyword) {
        final RxAction rxAction = newRxAction(SEARCH_BOOK_BY_KEYWORD);
        if (hasRxAction(rxAction)) {
            return;
        }

        addRxAction(rxAction, BookManService.getAppApi().searchBooksByKeyword(keyword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(booksResult -> {
                rxAction.getData().put(Keys.SHORT_BOOKS, booksResult);
                postRxAction(rxAction);
            }, throwable -> postError(rxAction, throwable)));
    }

    @Override
    public void setSearchBookByKeywordPage(String keyword, int page) {
        final RxAction rxAction = newRxAction(SEARCH_BOOK_BY_KEYWORD_PAGE);
        if (hasRxAction(rxAction)) {
            return;
        }

        addRxAction(rxAction, BookManService.getAppApi().searchBooksByKeywordAndPage(keyword, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(booksResult -> {
                rxAction.getData().put(Keys.SHORT_BOOKS, booksResult);
                postRxAction(rxAction);
            }, throwable -> postError(rxAction, throwable)));
    }

    @Override
    public void getBookDetailById(long bookId) {
        final RxAction rxAction = newRxAction(GET_BOOK_DETAIL_BY_ID);
        if (hasRxAction(rxAction)) {
            return;
        }

        addRxAction(rxAction, BookManService.getAppApi().getBookDetail(bookId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(result -> {
                rxAction.getData().put(Keys.BOOK, result);
                postRxAction(rxAction);
            }, throwable -> postError(rxAction, throwable)));
    }

    @Override
    public void getBooks() {
        final RxAction rxAction = newRxAction(GET_LIST_BOOKS);
        if (hasRxAction(rxAction)) {
            return;
        }
        postRxAction(rxAction);
    }

    @Override
    public void getRecentBooks() {
        final RxAction rxAction = newRxAction(GET_RECENT_BOOKS);
        if (hasRxAction(rxAction)) {
            return;
        }
        postRxAction(rxAction);
    }

    @Override
    public void getDownloadedBooks() {
        final RxAction rxAction = newRxAction(GET_DOWNLOADED_BOOKS);
        if (hasRxAction(rxAction)) {
            return;
        }
        postRxAction(rxAction);
    }

    @Override
    public void getDownloadingBooks() {
        final RxAction rxAction = newRxAction(GET_DOWNLOADING_BOOKS);
        if (hasRxAction(rxAction)) {
            return;
        }
        postRxAction(rxAction);
    }
}
