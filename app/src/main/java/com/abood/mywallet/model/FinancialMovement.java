package com.abood.mywallet.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FinancialMovement {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private double value;

    private int account_id;

    private long create_at;

    public FinancialMovement(double value, int account_id, long create_at) {
        this.value = value;
        this.account_id = account_id;
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

    public int getAccount_id() {
        return account_id;
    }

    public long getCreate_at() {
        return create_at;
    }

    @Override
    public String toString() {
        return "FinancialMovement{" +
                "id=" + id +
                ", value=" + value +
                ", account_id=" + account_id +
                ", create_at=" + create_at +
                '}';
    }
}
