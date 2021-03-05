package com.abood.mywallet.feature.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.abood.mywallet.model.FinancialMovement;
import com.abood.mywallet.utils.localDataBase.repository.FinancialMovementRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private FinancialMovementRepository repository;
    private LiveData<List<FinancialMovement>> allFinancialMovement;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new FinancialMovementRepository(application);
        allFinancialMovement = repository.getAllFinancialMovement();
    }

    public void insert(FinancialMovement financialMovement) {
        repository.insert(financialMovement);
    }

    public void update(FinancialMovement financialMovement) {
        repository.update(financialMovement);
    }

    public void delete(FinancialMovement financialMovement) {
        repository.delete(financialMovement);
    }

    public LiveData<List<FinancialMovement>> getAllFinancialMovement() {
        return allFinancialMovement;
    }
}
