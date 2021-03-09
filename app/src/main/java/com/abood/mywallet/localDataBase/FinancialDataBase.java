package com.abood.mywallet.localDataBase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.abood.mywallet.localDataBase.dao.FinancialMovementDao;
import com.abood.mywallet.model.FinancialMovement;
import com.abood.mywallet.utils.AppContent;

@Database(entities = {FinancialMovement.class}, version = 2)
public abstract class FinancialDataBase extends RoomDatabase {

    private static FinancialDataBase dataBase;

    public abstract FinancialMovementDao financialMovementDao();

    public static synchronized FinancialDataBase getInstance(Context context) {
        if (dataBase == null) {
            dataBase = Room.databaseBuilder(context.getApplicationContext(), FinancialDataBase.class
                    , AppContent.FINANCIAL_DATABASE).fallbackToDestructiveMigration()
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
            return null;
        }
    }
}
