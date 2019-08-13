package com.homecook.android.app.feed;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.homecook.android.app.R;
import com.homecook.android.app.common.LoggedInActivity;

/**
 * @author rohansaigaonkar
 */
public class FeedActivity extends LoggedInActivity {
    private static final String TAG = FeedActivity.class.getSimpleName();
    private BottomNavigationView bottomNavigationView;
    private FloatingActionButton newPostButton;
    private CoordinatorLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        Log.d(TAG, "User logged in successfully");
        navigateToFragment(new NearbyFragment());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    @Override
    public void navigateToFragment(@NonNull Fragment fragment) {

    }
}
