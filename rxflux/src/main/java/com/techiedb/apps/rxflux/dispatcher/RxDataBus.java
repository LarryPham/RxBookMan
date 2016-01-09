package com.techiedb.apps.rxflux.dispatcher;


import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

public class RxDataBus {
    private static RxDataBus sInstance;
    private final Subject<Object, Object> mDataBus = new SerializedSubject<>(PublishSubject.create());

    private RxDataBus() {

    }

    public synchronized static RxDataBus getInstance() {
        if (sInstance == null) {
            sInstance = new RxDataBus();
        }
        return sInstance;
    }

    public void send(Object obj) {
        mDataBus.onNext(obj);
    }

    public void completed(Object obj) {
        mDataBus.onCompleted();
    }

    public Observable<Object> get() {
        return mDataBus;
    }

    public boolean hasObservers() {
        return mDataBus.hasObservers();
    }

    public boolean hasCompleted() {
        return mDataBus.hasCompleted();
    }
}
