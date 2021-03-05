package com.abood.mywallet.utils.localDataBase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.abood.mywallet.model.Account;
import com.abood.mywallet.model.FinancialMovement;
import com.abood.mywallet.utils.localDataBase.dao.AccountDao;
import com.abood.mywallet.utils.localDataBase.dao.FinancialMovementDao;

@Database(entities = {FinancialMovement.class, Account.class}, version = 1)
public abstract class FinancialDataBase extends RoomDatabase {

    private static FinancialDataBase dataBase;

    public abstract FinancialMovementDao financialMovementDao();

    public abstract AccountDao accountDao();

    public static synchronized FinancialDataBase getInstance(Context context) {
        if (dataBase == null) {
            dataBase = Room.databaseBuilder(context.getApplicationContext(), FinancialDataBase.class
                    , "financial_database").fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return dataBase;
    }

    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateFbAsyncTask(dataBase).execute();
        }
    };

    private static class PopulateFbAsyncTask extends AsyncTask<Void, Void, Void> {
        private FinancialMovementDao financialMovementDao;

        private PopulateFbAsyncTask(FinancialDataBase financialDataBase) {
            financialMovementDao = financialDataBase.financialMovementDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            financialMovementDao.insert(new FinancialMovement(14, 1, 124332442));
            financialMovementDao.insert(new FinancialMovement(25, 2, 124332442));
            financialMovementDao.insert(new FinancialMovement(533, 1, 124332442));
            return null;
        }
    }
}
