package com.homecook.android.app.feed;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

/**
 * @author rohansaigaonkar
 */
public class FeedActivityContract {
    public interface View extends FragmentManager.OnBackStackChangedListener {
        void setPresenter(@NonNull FeedActivityContract.Presenter presenter);
        void finish();
        void showNewItems();
    }
    public interface Presenter {
        void start();
        void onNavigationItemSelected(@NonNull MenuItem menuItem);
        void onBackPressed();
    }
}
