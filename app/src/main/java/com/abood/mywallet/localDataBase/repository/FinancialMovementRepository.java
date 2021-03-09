package com.abood.mywallet.localDataBase.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.abood.mywallet.localDataBase.dao.FinancialMovementDao;
import com.abood.mywallet.model.FinancialMovement;
import com.abood.mywallet.localDataBase.FinancialDataBase;

import java.util.List;

public class FinancialMovementRepository {

    private FinancialMovementDao dao;

    public FinancialMovementRepository(Application application) {

        FinancialDataBase financialDataBase = FinancialDataBase.getInstance(application);
        dao = financialDataBase.financialMovementDao();
    }

    public void insert(FinancialMovement financialMovement) {
        new InsertFinancialAsyncTask(dao).execute(financialMovement);
    }

    public void update(FinancialMovement financialMovement) {
        new UpdateFinancialAsyncTask(dao).execute(financialMovement);
    }

    public void delete(FinancialMovement financialMovement) {
        new DeleteFinancialAsyncTask(dao).execute(financialMovement);
    }

    public LiveData<List<FinancialMovement>> getAllFinancialMovement() {
        return  dao.getAllFinancialMovement();
    }

    public LiveData<List<FinancialMovement>> getFinancialMovementsByCurrency(String currency){
        return dao.getFinancialMovementsByCurrency(currency);
    }

    public LiveData<Integer> getSumFinancialMovementsByCurrency(String currency){
        return dao.getSumFinancialMovementsByCurrency(currency);
    }


    private static class InsertFinancialAsyncTask extends AsyncTask<FinancialMovement, Void, Void> {

        private FinancialMovementDao financialMovementDao;

        private InsertFinancialAsyncTask(FinancialMovementDao financialMovementDao){
            this.financialMovementDao = financialMovementDao;
        }

        @Override
        protected Void doInBackground(FinancialMovement... financialMovements) {
            financialMovementDao.insert(financialMovements[0]);
            return null;
        }
    }

    private static class UpdateFinancialAsyncTask extends AsyncTask<FinancialMovement, Void, Void> {

        private FinancialMovementDao financialMovementDao;

        private UpdateFinancialAsyncTask(FinancialMovementDao financialMovementDao){
            this.financialMovementDao = financialMovementDao;
        }

        @Override
        protected Void doInBackground(FinancialMovement... financialMovements) {
            financialMovementDao.insert(financialMovements[0]);
            return null;
        }
    }

    private static class DeleteFinancialAsyncTask extends AsyncTask<FinancialMovement, Void, Void> {

        private FinancialMovementDao financialMovementDao;

        private DeleteFinancialAsyncTask(FinancialMovementDao financialMovementDao){
            this.financialMovementDao = financialMovementDao;
        }

        @Override
        protected Void doInBackground(FinancialMovement... financialMovements) {
            financialMovementDao.insert(financialMovements[0]);
            return null;
        }
    }
}
