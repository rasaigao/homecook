package com.homecook.android.app.common;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.homecook.android.app.R;

public abstract class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
    }

    public abstract void onInfoClicked();
}
