package com.homecook.android.app.feed;

import androidx.annotation.NonNull;

import com.homecook.android.app.common.User;

import java.util.Date;

/**
 * @author rohansaigaonkar
 */
public class FeedListItem {
    @NonNull private String mTitle;
    @NonNull private User mAuthor;
    @NonNull private Date mPublishingDate;

}
