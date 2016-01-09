package com.techiedb.apps.rxflux.store;

import com.techiedb.apps.rxflux.dispatcher.Dispatcher;
import com.techiedb.apps.rxflux.dispatcher.RxActionDispatch;

public abstract class RxStore implements RxActionDispatch {
    private final Dispatcher mDispatcher;

    public RxStore(Dispatcher dispatcher) {
        this.mDispatcher = dispatcher;
    }

    public void register() {
        mDispatcher.registerRxAction(this);
    }

    public void postChange(RxStoreChange change) {
        mDispatcher.postRxStoreChange(change);
    }
}