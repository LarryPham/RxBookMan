package com.capsule.apps.rxbookman.activities;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.capsule.apps.rxbookman.BookApp;
import com.capsule.apps.rxbookman.R;
import com.capsule.apps.rxbookman.fragments.BaseFragment;
import com.capsule.apps.rxbookman.models.AppDataModel;
import com.capsule.apps.rxbookman.models.Book;
import com.capsule.apps.rxbookman.utils.LogUtils;
import com.capsule.apps.rxbookman.widgets.CustomTypefaceSpan;

public abstract class BaseActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener,
        FragmentManager.OnBackStackChangedListener{

    private static final String TAG = LogUtils.makeLogTag(BaseActivity.class);
    // delay to launch nav drawer item, to allow chose animation to play
    private static final int NAV_DRAWER_LAUNCH_DELAY = 250;

    // fade in and fade out durations for the main content when switching between
    // different activities of the app through the Nav Drawer
    private static final int MAIN_CONTENT_FADEOUT_DURATION = 150;
    private static final int MAIN_CONTENT_FADEIN_DURATION = 250;

    private ObjectAnimator mStatusBarColorAnimator;

    // Primary toolbar and drawer toggle.
    private Toolbar mActionBarToolbar;
    protected DrawerLayout mDrawerLayout;
    protected NavigationView mNavigationView;

    // variables that control the Action Bar auto hide behavior(aka "Quick Recall")
    private boolean mActionBarAutoHideEnabled = false;
    private int mActionBarAutoHideSensivity = 0;
    private int mActionBarAutoHideMinY = 0;
    private int mActionBarAutoHideSignal = 0;

    // Variable flagged the condition of shown actionbar or not.
    private boolean mActionBarShown = true;
    // A Runnable that we should execute when the navigation drawer finishes its closing animation
    private Runnable mDeferredOnDrawerClosedRunnable;
    protected Handler mHandler;

    protected int mThemedStatusBarColor;
    protected int mNormalStatusBarColor;

    private int mProgressBarTopWhenActionBarShown;
    private static final TypeEvaluator ARGB_EVALUATOR = new ArgbEvaluator();

    protected BaseFragment mContentFragment;
    protected boolean mShouldAddToBackStack = true;

    protected BookApp mApp;
    protected AppDataModel mDataModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApp = (BookApp) this.getApplication();
        mDataModel = (AppDataModel) mApp.getAppDataModel();
        mHandler = new Handler();

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        sp.registerOnSharedPreferenceChangeListener(this);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mThemedStatusBarColor = getResources().getColor(R.color.color_primary_dark, getTheme());
        } else {
            mThemedStatusBarColor = getResources().getColor(R.color.color_primary_dark);
        }
        mNormalStatusBarColor = mThemedStatusBarColor;
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        getActionBarToolbar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        sp.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }

    public void setupNavDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer);
        if (mDrawerLayout == null) {
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mDrawerLayout.setStatusBarBackgroundColor(mThemedStatusBarColor);
        } else {
            mDrawerLayout.setStatusBarBackgroundColor(mNormalStatusBarColor);
        }

        mNavigationView = (NavigationView) findViewById(R.id.main_navigation_view);
        if (mNavigationView == null) {
            return;
        }

        applyFontToNavigationView();

        if (mNavigationView.isShown()) {
            final Window window = getWindow();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(getResources().getColor(R.color.color_primary_dark));
            }
        }

        mNavigationView.setNavigationItemSelectedListener(menuItem -> {
            menuItem.setChecked(true);
            mDrawerLayout.closeDrawers();
            Toast.makeText(BaseActivity.this, menuItem.getTitle(), Toast.LENGTH_SHORT).show();
            final int menuItemId = menuItem.getItemId();

            switch (menuItemId) {
                case R.id.nav_drawer_downloads_books: {
                    return true;
                }
                case R.id.nav_drawer_explorer: {
                    return true;
                }
                case R.id.nav_drawer_recent_books: {
                    return true;
                }
                default: {
                    return false;
                }
            }
        });

        if (mActionBarToolbar != null) {
            mActionBarToolbar.setNavigationIcon(R.drawable.ic_menu);
            mActionBarToolbar.setNavigationOnClickListener(view -> {
                mDrawerLayout.openDrawer(GravityCompat.START);
            });
        }
    }
    protected Toolbar getActionBarToolbar() {
        if (mActionBarToolbar == null) {
            mActionBarToolbar = (Toolbar) findViewById(R.id.main_toolbar);
            if (mActionBarToolbar != null) {
                mActionBarToolbar.setNavigationContentDescription(getResources().getString(R.string.navdrawer_description));
                setSupportActionBar(mActionBarToolbar);
            }
        }
        return mActionBarToolbar;
    }

    protected void onActionBarAutoShowOrHide(boolean shown) {
        if (mStatusBarColorAnimator != null) {
            mStatusBarColorAnimator.cancel();
        }

    }

    protected void applyFontToNavigationView() {
        if (mNavigationView == null) {
            mNavigationView = (NavigationView) findViewById(R.id.main_navigation_view);
        }

        Menu menu = mNavigationView.getMenu();
        for (int index = 0; index < menu.size(); index++) {
            final MenuItem menuItem = menu.getItem(index);

            SubMenu subMenu = menuItem.getSubMenu();
            if (subMenu != null && subMenu.size() > 0) {
                for (int jIndex = 0; jIndex < subMenu.size(); jIndex++) {
                    MenuItem subMenuItem = subMenu.getItem(jIndex);
                    applyFontToMenuItem(subMenuItem);
                }
            }
            applyFontToMenuItem(menuItem);
        }
    }

    private void applyFontToMenuItem(MenuItem menuItem) {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "Fonts/FiraSans-Medium.ttf");
        SpannableString mNewTitle = new SpannableString(menuItem.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("", typeface), 0, mNewTitle.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        menuItem.setTitle(mNewTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bookman, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setupNavDrawer();

        View mainContent = findViewById(R.id.main_content);
        if (mainContent != null) {
            mainContent.setAlpha(0);
            mainContent.animate().alpha(1).setDuration(MAIN_CONTENT_FADEIN_DURATION);
        } else {
            LogUtils.LOGW(TAG, "No View with ID main_content to fade in");
        }
    }

    protected void addContent(BaseFragment fragment, String fragmentKey, Bundle extras) {
        if (fragment.equals(mContentFragment)) {
            return;
        }

        mContentFragment = fragment;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        if (shouldAddToBackStack(manager, fragmentKey, extras)) {
            transaction.addToBackStack(String.valueOf(fragmentKey));
        }
        transaction.add(R.id.main_content, fragment, fragmentKey);
        transaction.commit();
        fragment.setBundle(extras);
    }

    protected void switchContent(BaseFragment fragment, String fragmentKey, Bundle extras) {
        if (fragment.equals(mContentFragment)) {
            return;
        }
        mContentFragment = fragment;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        if (shouldAddToBackStack(manager, fragmentKey, extras)) {
            transaction.addToBackStack(String.valueOf(fragmentKey));
        }
        transaction.replace(R.id.main_content, fragment, fragmentKey);
        transaction.commit();
        fragment.setBundle(extras);
    }

    protected boolean shouldAddToBackStack(FragmentManager fragmentManager, String fragmentKey, Bundle extras) {
        return mShouldAddToBackStack;
    }

    /**
     * Configure this Activity as a floating Window, with the given {@code width}, {@code height},
     * and {@code alpha} and dimming the background with the given
     * {@code dim} value.
     */
    protected void setupFloatingWindow(int width, int height, int alpha, float dim) {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = getResources().getDimensionPixelSize(width);
        params.height = getResources().getDimensionPixelSize(height);
        params.alpha = alpha;
        params.dimAmount = dim;
        params.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        getWindow().setAttributes(params);
    }

    /**
     * Returns true if the theme set the {@code R.attr.isFloatingWindow} flag to true
     */
    protected boolean shouldBeFloatingWindow() {
        Resources.Theme theme = getTheme();
        TypedValue floatingWindowFlag = new TypedValue();
        // Check isFloatingWindow flag is defined in theme.
        if (theme ==  null || !theme.resolveAttribute(R.attr.isFloatingWindow, floatingWindowFlag, true)) {
            return false;
        }
        return (floatingWindowFlag.data != 0);
    }

    @Override
    public void onBackStackChanged() {
        BaseFragment contentFragment = (BaseFragment) getSupportFragmentManager().findFragmentById(R.id.main_content);
        if (contentFragment == null) {
            return;
        }
        mContentFragment = contentFragment;
        handleBackStackChanged();
    }

    protected abstract void handleBackStackChanged();

    protected abstract void invalidate();
    protected abstract void invalidate(Object...params);
}