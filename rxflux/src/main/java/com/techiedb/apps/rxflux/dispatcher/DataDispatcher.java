package com.techiedb.apps.rxflux.dispatcher;

import android.util.ArrayMap;

import rx.Subscription;

public class DataDispatcher {
    private static DataDispatcher sInstance;
    private final RxDataBus mDataBus;

    private ArrayMap<String, Subscription> mRxDataActionMap;
    private ArrayMap<String, Subscription> mRxDataStoreMap;

    private DataDispatcher(RxDataBus dataBus) {
        this.mDataBus = dataBus;
        this.mRxDataActionMap = new ArrayMap<>();
        this.mRxDataStoreMap = new ArrayMap<>();
    }

    public static synchronized DataDispatcher getInstace(RxDataBus dataBus) {
        if (sInstance == null) {
            sInstance = new DataDispatcher(dataBus);
        }
        return sInstance;
    }
}
