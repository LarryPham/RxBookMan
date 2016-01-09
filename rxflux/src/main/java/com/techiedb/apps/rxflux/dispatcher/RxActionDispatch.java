package com.techiedb.apps.rxflux.dispatcher;

import com.techiedb.apps.rxflux.action.RxAction;

public interface RxActionDispatch {
    void onRxAction(RxAction action);
}