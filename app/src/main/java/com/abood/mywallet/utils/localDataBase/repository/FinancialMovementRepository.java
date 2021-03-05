package com.abood.mywallet.utils.localDataBase.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.abood.mywallet.model.FinancialMovement;
import com.abood.mywallet.utils.localDataBase.FinancialDataBase;
import com.abood.mywallet.utils.localDataBase.dao.FinancialMovementDao;

import java.util.List;

public class FinancialMovementRepository {

    private FinancialMovementDao dao;
    private LiveData<List<FinancialMovement>> allFinancialMovement;

    public FinancialMovementRepository(Application application) {

        FinancialDataBase financialDataBase = FinancialDataBase.getInstance(application);
        dao = financialDataBase.financialMovementDao();
        allFinancialMovement = dao.getAllFinancailMovement();
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
        return allFinancialMovement;
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
