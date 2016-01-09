package com.techiedb.apps.rxflux.dispatcher;

import com.techiedb.apps.rxflux.action.RxError;
import com.techiedb.apps.rxflux.store.RxStoreChange;

public interface RxViewDispatch {
    /**
     * All the stores will call this event so the view an react and request the needed data
     * @param change The changed signals for store
     */
    void onRxStoreChanged(RxStoreChange change);

    void onRxError(RxError error);

    /**
     * When an activity has been registered RxFlux will notify the activity so it can register
     * custom views of fragments.
     */
    void onRxViewRegistered();

    /**
     * When the activity goes to Pause RxFlux will unregister it and the call this method so the
     * activity can unregister custom views of fragments.
     */
    void onRxViewUnRegistered();

    /**
     * RxFlux method to let the view create the stores that need for this activity, this method is called
     * every time the activity is created. Normally you will instantiate the store with the singleton instance.
     */
    void onRxStoreRegister();
}
