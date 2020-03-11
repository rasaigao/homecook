package com.homecook.android.app.common;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;

/**
 * @author rohansaigaonkar
 */
public class UserDetails {
    @NonNull private String mFirstName;
    @NonNull private String mLastName;
    @NonNull private String mUsername;
    @NonNull private String mId;
    @NonNull private String mEmail;
    @Nullable private String mPhoneNumber;

    private UserDetails(@NonNull String firstName,
                        @NonNull String lastName,
                        @NonNull String username,
                        @NonNull String email,
                        @NonNull String id,
                        @Nullable String phoneNumber) {
        this.mFirstName = firstName;
        this.mLastName = lastName;
        this.mUsername = username;
        this.mEmail = email;
        this.mId = id;
        this.mPhoneNumber = phoneNumber;
    }

    /**
     * Returns the unique identifier of the associated user.
     */
    @NonNull
    public String getId() {
        return mId;
    }

    /**
     * Returns the username of the associated user.
     */
    @NonNull
    public String getUsername() {
        return mUsername;
    }

    /**
     * Returns the first name of the associated user, if available.
     */
    @NonNull
    public String getFirstName() {
        return mFirstName;
    }

    /**
     * Returns the last name of the associated user, if available.
     */
    @NonNull
    public String getLastName() {
        return mLastName;
    }

    /**
     * Returns the email of the associated user.
     */
    @NonNull
    public String getEmail() {
        return mEmail;
    }

    /**
     * Returns the phone number of the associated user, if available.
     */
    @Nullable
    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof UserDetails)) {
            return false;
        }

        UserDetails d = (UserDetails) o;

        return mId.equals(d.mId)
                && mUsername.equals(d.mUsername)
                && ObjectsCompat.equals(mFirstName, d.mFirstName)
                && ObjectsCompat.equals(mLastName, d.mLastName)
                && mEmail.equals(d.mEmail)
                && ObjectsCompat.equals(mPhoneNumber, d.mPhoneNumber);
    }
}
