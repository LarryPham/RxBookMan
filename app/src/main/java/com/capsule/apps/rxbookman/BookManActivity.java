package com.capsule.apps.rxbookman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.capsule.apps.rxbookman.actions.BookAppActions;
import com.capsule.apps.rxbookman.activities.BaseActivity;
import com.capsule.apps.rxbookman.fragments.BaseFragment;
import com.capsule.apps.rxbookman.models.AppDataModel;
import com.capsule.apps.rxbookman.models.Book;
import com.capsule.apps.rxbookman.stores.BookStore;
import com.capsule.apps.rxbookman.utils.LogUtils;
import com.techiedb.apps.rxflux.action.RxError;
import com.techiedb.apps.rxflux.dispatcher.RxViewDispatch;
import com.techiedb.apps.rxflux.store.RxStoreChange;

public class BookManActivity extends BaseActivity implements RxViewDispatch {
    private static final String TAG = LogUtils.makeLogTag(BookManActivity.class);

    public BaseFragment mContentFragment;
    public BookStore mBookStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_book_man);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void handleBackStackChanged() {

    }

    @Override
    protected void invalidate() {

    }

    @Override
    protected void invalidate(Object... params) {

    }

    @Override
    public void onRxStoreChanged(RxStoreChange change) {
        final String storeId = change.getStoreId();
        switch (storeId) {
            case BookStore.ID: {
                switch (change.getRxAction().getType()) {
                    case BookAppActions.GET_LIST_BOOKS: {
                        break;
                    }
                }
                break;
            }
        }
    }

    @Override
    public void onRxError(RxError error) {
        if (error == null) {
            return;
        }

        if (error.getThrowable() != null) {
            Toast.makeText(this, "Error: " + error.getThrowable().getMessage(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Unknown Error", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRxViewRegistered() {

    }

    @Override
    public void onRxViewUnRegistered() {

    }

    @Override
    public void onRxStoreRegister() {
        LogUtils.LOGD(TAG, "Registering The BookStore");
        mBookStore = BookStore.get(BookApp.get(this).getFlux().getDispatcher());
        mBookStore.register();

        mApp = BookApp.get(BookManActivity.this);
    }
}
