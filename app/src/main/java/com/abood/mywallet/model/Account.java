package com.abood.mywallet.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"name", "currency"})
public class Account {

    @NonNull
    private String name;
    @NonNull
    private String currency;

    public Account(String name, String currency) {
        this.name = name;
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public String getCurrency() {
        return currency;
    }
}
