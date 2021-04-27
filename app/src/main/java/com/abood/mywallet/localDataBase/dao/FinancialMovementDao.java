package com.abood.mywallet.localDataBase.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.abood.mywallet.model.FinancialMovement;

import java.util.List;

@Dao
public interface FinancialMovementDao {

    @Insert
    void insert(FinancialMovement financialMovement);

    @Update
    void update(FinancialMovement financialMovement);

    @Delete
    void delete(FinancialMovement financialMovement);

    @Query("Select * from financialmovement order by id asc")
    LiveData<List<FinancialMovement>> getAllFinancialMovement();

    @Query("SELECT * FROM financialmovement WHERE currency Like (:currency) order by id asc")
    LiveData<List<FinancialMovement>> getFinancialMovementsByCurrency(String currency);

    @Query("SELECT SUM(value) FROM financialmovement WHERE currency Like (:currency)")
    LiveData<Integer> getSumFinancialMovementsByCurrency(String currency);
}
