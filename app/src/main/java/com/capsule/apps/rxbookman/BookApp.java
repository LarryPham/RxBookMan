package com.capsule.apps.rxbookman;

import android.app.Application;
import android.content.Context;

import com.capsule.apps.rxbookman.actions.BookActionCreator;
import com.capsule.apps.rxbookman.models.AppDataModel;
import com.capsule.apps.rxbookman.stores.BookStore;
import com.techiedb.apps.rxflux.RxFlux;

/**
 * @Author: larry
 * History: 12/16/15.
 */
public class BookApp extends Application {

    protected AppDataModel mAppDataModel = new AppDataModel();
    protected BookStore mBookStore;
    protected BookActionCreator mActionCreator;

    public static Context sContext;
    protected RxFlux mFlux;

    public AppDataModel getAppDataModel() {
        return mAppDataModel;
    }

    public void setAppDataModel(AppDataModel appDataModel) {
        mAppDataModel = appDataModel;
    }

    public BookStore getBookStore() {
        return mBookStore;
    }

    public void setBookStore(BookStore bookStore) {
        mBookStore = bookStore;
    }

    public BookActionCreator getActionCreator() {
        return mActionCreator;
    }

    public RxFlux getFlux() {
        return mFlux;
    }

    public static BookApp get(Context context) {
        return (BookApp) context.getApplicationContext();
    }

    public static Context getContext() {
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this.getApplicationContext();
        mFlux = RxFlux.init(this);
        mActionCreator = new BookActionCreator(mFlux.getDispatcher(), mFlux.getSubscriptionManager());
    }
}
