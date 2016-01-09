package com.techiedb.apps.rxflux.utils;

import com.techiedb.apps.rxflux.action.RxAction;

import android.support.v4.util.Pair;
import android.util.ArrayMap;

import rx.Subscription;

public class SubscriptionManager {

    private static SubscriptionManager sInstance;
    private ArrayMap<String, Pair<Integer, Subscription>> mPairArrayMap;

    private SubscriptionManager() {
        mPairArrayMap = new ArrayMap<>();
    }

    public static synchronized SubscriptionManager getInstance() {
        if (sInstance == null) {
            sInstance = new SubscriptionManager();
        }
        return sInstance;
    }

    public void add(RxAction action, Subscription subscription) {
        Pair<Integer, Subscription> old = mPairArrayMap.put(action.getType(), getPair(action, subscription));
        if (old != null && !old.second.isUnsubscribed()) {
            old.second.unsubscribe();
        }
    }

    public void remove(RxAction action) {
        Pair<Integer, Subscription> old = mPairArrayMap.get(action.getType());
        if (old != null && !old.second.isUnsubscribed()) {
            old.second.unsubscribe();
        }
    }

    public boolean contains(RxAction action) {
        Pair<Integer, Subscription> old = mPairArrayMap.get(action.getType());
        return (old != null && old.first == action.hashCode() && !old.second.isUnsubscribed());
    }

    public synchronized void clear() {
        if (mPairArrayMap.isEmpty()) {
            return;
        }

        for (Pair<Integer, Subscription> pair: mPairArrayMap.values()) {
            if (!pair.second.isUnsubscribed()) {
                pair.second.unsubscribe();
            }
        }
    }

    private Pair<Integer, Subscription> getPair(RxAction action, Subscription subscription) {
        return new Pair<>(action.hashCode(), subscription);
    }
}
