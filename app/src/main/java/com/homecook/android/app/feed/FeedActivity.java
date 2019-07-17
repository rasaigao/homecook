package com.homecook.android.app.feed;

import android.os.Bundle;
import android.util.Log;

import com.homecook.android.app.R;
import com.homecook.android.app.common.LoggedInActivity;

/**
 * @author rohansaigaonkar
 */
public class FeedActivity extends LoggedInActivity {
    private static final String TAG = FeedActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        Log.d(TAG, "User logged in successfully");

    }
}
