package com.capsule.apps.rxbookman.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.capsule.apps.rxbookman.models.AppDataModel;
import com.capsule.apps.rxbookman.utils.LogUtils;

import org.lucasr.twowayview.widget.TwoWayView;

public class BooksFragment extends BaseFragment {
    private static final String TAG = LogUtils.makeLogTag(BooksFragment.class);

    private static final String ARG_LAYOUT_ID = "layout_id";

    private TwoWayView mRecyclerView;

    private int mLayoutId;
    protected View mRootView;

    public static BooksFragment newInstance(int layoutId) {
        final BooksFragment fragment = new BooksFragment();

        final Bundle args = new Bundle();
        args.putInt(ARG_LAYOUT_ID, layoutId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLayoutId = getArguments().getInt(ARG_LAYOUT_ID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(mLayoutId, container, false);
        return mRootView;
    }

    @Override
    public void invalidate() {

    }

    @Override
    public void invalidate(Object... params) {

    }

    public int getLayoutId() {
        return getArguments().getInt(ARG_LAYOUT_ID);
    }
}
