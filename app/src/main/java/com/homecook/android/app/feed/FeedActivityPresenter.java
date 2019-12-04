package com.homecook.android.app.feed;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.homecook.android.app.R;
import com.homecook.android.app.following.FollowingFragment;
import com.homecook.android.app.nearby.NearbyFragment;
import com.homecook.android.app.notifications.NotificationsFragment;
import com.homecook.android.app.profile.ProfileFragment;

/**
 * @author rohansaigaonkar
 */
public class FeedActivityPresenter implements FeedActivityContract.Presenter {
    private FeedActivityContract.View view;
    public FeedActivityPresenter(FeedActivityContract.View view) {
        this.view = view;
        start();
    }
    @Override
    public void start() {
        view.setPresenter(this);
    }

    @Override
    public void onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (view instanceof FeedActivity) {
            Fragment fragment;
            switch (menuItem.getItemId()) {
                case R.id.bottom_menu_nearby:
                    fragment = new NearbyFragment();
                    break;
                case R.id.bottom_menu_following:
                    fragment = new FollowingFragment();
                    break;
                case R.id.bottom_menu_notifications:
                    fragment = new NotificationsFragment();
                    break;
                case R.id.bottom_menu_profile:
                    fragment = new ProfileFragment();
                    break;
                default:
                    fragment = new NearbyFragment();

            }
            ((FeedActivity) view).navigateToFragment(fragment);
        }
    }

    @Override
    public void onBackPressed() {

    }
}
