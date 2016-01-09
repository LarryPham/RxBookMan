package com.techiedb.apps.rxflux;

import com.techiedb.apps.rxflux.dispatcher.Dispatcher;
import com.techiedb.apps.rxflux.dispatcher.RxBus;
import com.techiedb.apps.rxflux.dispatcher.RxViewDispatch;
import com.techiedb.apps.rxflux.utils.SubscriptionManager;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

public class RxFlux implements Application.ActivityLifecycleCallbacks {
    private static RxFlux sInstance;
    private final RxBus mRxBus;
    private final Dispatcher mDispatcher;
    private final SubscriptionManager mSubscriptionManager;
    private int mActivityCounter;

    private RxFlux(Application app) {
        this.mRxBus = RxBus.getInstance();
        this.mDispatcher = Dispatcher.getInstance(mRxBus);
        this.mSubscriptionManager = SubscriptionManager.getInstance();
        mActivityCounter =  0;
        app.registerActivityLifecycleCallbacks(this);
    }

    public static RxFlux init(Application app) {
        if (sInstance != null) {
            throw new IllegalStateException("Init was already called");
        }
        return sInstance = new RxFlux(app);
    }

    public static void shutDown() {
        if (sInstance == null) {
            return;
        }

        sInstance.mSubscriptionManager.clear();
        sInstance.mDispatcher.unregisterAll();
    }

    public RxBus getRxBus() {
        return mRxBus;
    }

    public Dispatcher getDispatcher() {
        return mDispatcher;
    }

    public SubscriptionManager getSubscriptionManager() {
        return  mSubscriptionManager;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        mActivityCounter++;
        if (activity instanceof RxViewDispatch) {
            ((RxViewDispatch) activity).onRxStoreRegister();
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {
        if (activity instanceof RxViewDispatch) {
            ((RxViewDispatch) activity).onRxViewRegistered();
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {
        if (activity instanceof RxViewDispatch) {
            mDispatcher.registerRxStore((RxViewDispatch) activity);
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
        if (activity instanceof RxViewDispatch) {
            mDispatcher.unregisterRxStore((RxViewDispatch) activity);
        }
    }

    @Override
    public void onActivityStopped(Activity activity) {
        if (activity instanceof RxViewDispatch) {
            ((RxViewDispatch) activity).onRxViewUnRegistered();
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        mActivityCounter--;

        if (mActivityCounter == 0) {
            RxFlux.shutDown();
        }
    }
}
