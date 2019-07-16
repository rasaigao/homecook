package com.homecook.android.app.common;

import androidx.annotation.NonNull;

/**
 * @author rohansaigaonkar
 */
public interface MvpView<T extends MvpPresenter> {

    void setPresenter(@NonNull T presenter);
}
