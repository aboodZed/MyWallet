package com.abood.mywallet.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;

public class LocalStorage {

    private static final String PREF_NAME = "LocalStorage";
    private int PRIVATE_MODE = 0;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public LocalStorage(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public <T> void setArray(String key, ArrayList<T> arrayList) {
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        editor.putString(key, json);
        editor.apply();
    }

    public <T> ArrayList<T> getArray(String key) {
        Gson gson = new Gson();
        String json = preferences.getString(key, "");
        return gson.fromJson(json, ArrayList.class);
    }
}
