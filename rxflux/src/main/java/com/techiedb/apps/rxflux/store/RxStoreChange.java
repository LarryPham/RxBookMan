package com.techiedb.apps.rxflux.store;

import com.techiedb.apps.rxflux.action.RxAction;

public class RxStoreChange {
    public String mStoreId;
    public RxAction mRxAction;

    public RxStoreChange(String storeId, RxAction rxAction) {
        this.mStoreId = storeId;
        this.mRxAction = rxAction;
    }

    public RxAction getRxAction() {
        return mRxAction;
    }

    public String getStoreId() {
        return mStoreId;
    }
}
