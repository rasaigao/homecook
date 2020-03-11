package com.homecook.android.app.common;

public interface ServerCallback<T> {
    void onSuccess(T response);
    void onFailure(Throwable t);
}
