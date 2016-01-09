package com.techiedb.apps.rxflux.dispatcher;

import com.techiedb.apps.rxflux.action.RxAction;
import com.techiedb.apps.rxflux.action.RxError;
import com.techiedb.apps.rxflux.store.RxStoreChange;

import android.util.ArrayMap;

import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subjects.Subject;

/**
 * RxFlux dispatcher, contains the registered actions, stores and the instance of the RxBus responsible to send events to the stores.
 * This class is used as a singleton.
 */
public class Dispatcher {

    private static Dispatcher sInstance;
    private final RxBus mBus;
    private ArrayMap<String, Subscription> mRxActionMap;
    private ArrayMap<String, Subscription> mRxStoreMap;

    public Dispatcher(RxBus bus) {
        this.mBus = bus;
        this.mRxActionMap = new ArrayMap<>();
        this.mRxStoreMap = new ArrayMap<>();
    }

    public static synchronized Dispatcher getInstance(RxBus rxBus) {
        if (sInstance == null) {
            sInstance = new Dispatcher(rxBus);
        }
        return sInstance;
    }

    public <T extends RxActionDispatch> void registerRxAction(final T object) {
        String tag = object.getClass().getSimpleName();
        Subscription subscription = mRxActionMap.get(tag);
        if (subscription == null || subscription.isUnsubscribed()) {
            mRxActionMap.put(tag, mBus.get().filter(new Func1<Object, Boolean>() {
                @Override
                public Boolean call(Object obj) {
                    return obj instanceof RxAction;
                }
            }).subscribe(new Action1<Object>() {
                @Override
                public void call(Object obj) {
                    object.onRxAction((RxAction) obj);
                }
            }));
        }
    }

    public <T extends RxViewDispatch> void registerRxError(final T object) {
        String tag = object.getClass().getSimpleName() + "_error";
        Subscription subscription = mRxActionMap.get(tag);
        if (subscription == null || subscription.isUnsubscribed()) {
            mRxActionMap.put(tag, mBus.get().filter(new Func1<Object, Boolean>() {
                @Override
                public Boolean call(Object obj) {
                    return obj instanceof RxError;
                }
            }).subscribe(new Action1<Object>() {
                @Override
                public void call(Object obj) {
                    object.onRxError((RxError) obj);
                }
            }));
        }
    }

    public <T extends RxViewDispatch> void registerRxStore(final T object) {
        String tag = object.getClass().getSimpleName();
        Subscription subscription = mRxStoreMap.get(tag);
        if (subscription == null || subscription.isUnsubscribed()) {
            mRxStoreMap.put(tag, mBus.get().filter(new Func1<Object, Boolean>() {
                @Override
                public Boolean call(Object o) {
                    return o instanceof RxStoreChange;
                }
            }).subscribe(new Action1<Object>() {
                @Override
                public void call(Object o) {
                    object.onRxStoreChanged((RxStoreChange) o);
                }
            }));
        }
        registerRxError(object);
    }

    public <T extends RxViewDispatch> void unregisterRxAction(final T object) {
        String tag = object.getClass().getSimpleName();
        Subscription subscription = mRxActionMap.get(tag);
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
            mRxActionMap.remove(tag);
        }
    }

    public <T extends RxViewDispatch> void unregisterRxError(final T object) {
        String tag = object.getClass().getSimpleName() + "_error";
        Subscription subscription = mRxActionMap.get(tag);
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
            mRxActionMap.remove(tag);
        }
    }

    public <T extends RxViewDispatch> void unregisterRxStore(final T object) {
        String tag = object.getClass().getSimpleName();
        Subscription subscription = mRxStoreMap.get(tag);
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
            mRxStoreMap.remove(tag);
        }
        unregisterRxError(object);
    }

    public synchronized void unregisterAll() {
        for (Subscription subscription : mRxActionMap.values()) {
            subscription.unsubscribe();
        }

        for (Subscription subscription : mRxStoreMap.values()) {
            subscription.unsubscribe();
        }

        mRxActionMap.clear();
        mRxStoreMap.clear();
    }

    public void postRxAction(final RxAction action) {
        mBus.send(action);
    }

    public void postRxStoreChange(final RxStoreChange storeChange) {
        mBus.send(storeChange);
    }

}
