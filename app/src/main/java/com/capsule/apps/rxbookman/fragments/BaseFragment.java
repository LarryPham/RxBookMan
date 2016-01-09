package com.capsule.apps.rxbookman.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.ViewGroup;

import com.capsule.apps.rxbookman.BookApp;
import com.capsule.apps.rxbookman.activities.BaseActivity;
import com.capsule.apps.rxbookman.models.AppDataModel;
import com.capsule.apps.rxbookman.utils.LogUtils;

import java.util.HashMap;

public abstract class BaseFragment extends Fragment {

    protected BaseActivity mActivity;
    protected BookApp mBookApp;

    protected Bundle mBundle = new Bundle();
    protected HashMap<String, Object> mTags;
    protected String mFragmentKey;

    protected AppDataModel mAppDataModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (BaseActivity) this.getActivity();
        mBookApp = (BookApp) this.getContext();
        mAppDataModel = mBookApp.getAppDataModel();
        setRetainInstance(true);
    }

    /**
     * Sets the Extra Bundle to an instance of {@link BaseFragment} class, which will be assigned by something variables
     * that instance used to handle something into this screen
     * @param extras The Bundle object.
     */
    public void setBundle(Bundle extras) {
        this.mBundle = extras;
    }

    /**
     * Gets the Extras Bundle object which was integrated into the current Fragment object.
     * @return The Bundle object. See {@link Bundle}
     */
    public Bundle getBundle() {
        return this.mBundle;
    }

    /**
     * Sets the FragmentKey which has interacted with key that <code>BaseActivity</code>'s instance used to call the BaseFragment's instance
     * by them.
     * @param fragmentKey The FragmentKey String type.
     */
    public void setFragmentKey(String fragmentKey) {
        this.mFragmentKey = fragmentKey;
    }

    /**
     * Gets the FragmentKey which has been integrated into the current fragment object, so that we can handle the fragment's instance with this
     * key
     * @return Fragment's key-hash, String type.
     */
    public String getFragmentKey() {
        return mFragmentKey;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * When the Action's instance has been handled into the Dispatcher's instance, The View component should be invalidate the changes from the updated store
     * and then must be invalidating the current fragment again after calling them.
     */
    public abstract void invalidate();

    /**
     * Also same meaning with method above, but it will be used the couple of objects that used to validate the information from store and actions.
     * @param params The object params.
     */
    public abstract void invalidate(Object... params);
}
