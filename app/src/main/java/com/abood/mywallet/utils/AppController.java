package com.abood.mywallet.utils;

import android.app.Application;

public class AppController extends Application {

    private static AppController mInstance;

    private LocalStorage localStorage;

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        localStorage = new LocalStorage(this);
    }

    public LocalStorage getLocalStorage() {
        return localStorage;
    }
}
