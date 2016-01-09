package com.techiedb.apps.rxflux.action;

import com.techiedb.apps.rxflux.dispatcher.Dispatcher;
import com.techiedb.apps.rxflux.utils.SubscriptionManager;

import android.support.annotation.NonNull;
import android.util.Log;

import rx.Subscription;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;

/**
 * This class must be extends in order to give useful functionality to create RxAction.
 */
public abstract class RxActionCreator  {
    private final Dispatcher mDispatcher;
    private final SubscriptionManager mSubscriptionManager;

    public RxActionCreator(Dispatcher dispatcher, SubscriptionManager manager) {
        this.mDispatcher = dispatcher;
        this.mSubscriptionManager = manager;
    }

    public void addRxAction(RxAction rxAction, Subscription subscription) {
        mSubscriptionManager.add(rxAction, subscription);
    }

    public boolean hasRxAction(RxAction rxAction) {
        return mSubscriptionManager.contains(rxAction);
    }

    public void removeRxAction(RxAction rxAction) {
        mSubscriptionManager.remove(rxAction);
    }

    public RxAction newRxAction(@NonNull String actionId, @NonNull Object... data) {
        if (actionId.isEmpty()) {
            throw new IllegalArgumentException("Type must not be empty");
        }

        if (data.length % 2 != 0) {
            throw new IllegalArgumentException("Data must be a valid list of key, value pairs");
        }

        RxAction.Builder actionBuilder = RxAction.type(actionId);
        int index = 0;
        while (index < data.length) {
            String key = (String) data[index++];
            Object value = data[index++];
            actionBuilder.bundle(key, value);
        }
        return actionBuilder.build();
    }

    public void postRxAction(@NonNull RxAction rxAction) {
        mDispatcher.postRxAction(rxAction);
    }

    public void postError(RxAction rxAction, Throwable throwable) {
        mDispatcher.postRxAction(RxError.newRxError(rxAction, throwable));
    }

}
