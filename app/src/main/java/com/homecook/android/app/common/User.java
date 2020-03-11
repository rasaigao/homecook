package com.homecook.android.app.common;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Set;
import java.util.UUID;

/**
 * @author rohansaigaonkar
 */
public class User {

    @NonNull private UserDetails userDetails;
    @NonNull private Set<UUID> upvoted;
    @NonNull private Set<UUID> downvoted;


}
