package com.abood.mywallet.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class FinancialMovement {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private double value;

    private String currency;

    private long create_at;

    public FinancialMovement(double value, String currency, long create_at) {
        this.value = value;
        this.currency = currency;
        this.create_at = create_at;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public double getValue() {
        return value;
    }


    public String getCurrency() {
        return currency;
    }

    public long getCreate_at() {
        return create_at;
    }

    @Override
    public String toString() {
        return "FinancialMovement{" +
                "id=" + id +
                ", value=" + value +
                ", currency='" + currency + '\'' +
                ", create_at=" + create_at +
                '}';
    }
}
