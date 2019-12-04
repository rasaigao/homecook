package com.homecook.android.app.feed;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.crashlytics.android.Crashlytics;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.homecook.android.app.R;
import com.homecook.android.app.common.LoggedInActivity;
import com.homecook.android.app.nearby.NearbyFragment;

/**
 * @author rohansaigaonkar
 */
public class FeedActivity extends LoggedInActivity implements FeedActivityContract.View {
    private static final String TAG = FeedActivity.class.getSimpleName();
    private FeedActivityContract.Presenter presenter;
    private BottomNavigationView bottomNavigationView;
    private FloatingActionButton newPostButton;
    private FrameLayout fragmentContainer;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        presenter = new FeedActivityPresenter(this);
        fragmentManager = getSupportFragmentManager();
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        newPostButton = findViewById(R.id.button_new_item);
        fragmentContainer = findViewById(R.id.feed_fragment_container);
        navigateToFragment(new NearbyFragment());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                presenter.onNavigationItemSelected(menuItem);
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
            super.onBackPressed();
            finishAffinity();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void navigateToFragment(@NonNull Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.feed_fragment_container, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void setPresenter(@NonNull FeedActivityContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showNewItems() {

    }

    @Override
    public void onBackStackChanged() {
        try {

        } catch (Exception e) {
            Crashlytics.logException(e);
        }
    }
}
