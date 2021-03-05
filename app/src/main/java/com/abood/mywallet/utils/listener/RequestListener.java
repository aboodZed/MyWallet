package com.abood.mywallet.utils.listener;

public interface RequestListener<T> {

    void onSuccess(T t, String msg);
    void onFail(String msg);
}
