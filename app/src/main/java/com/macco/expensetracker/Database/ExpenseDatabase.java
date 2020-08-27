package com.macco.expensetracker.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.macco.expensetracker.Dao.ExpenseDao;
import com.macco.expensetracker.Entity.ExpenseTable;


@Database(entities = {ExpenseTable.class},version = 1,exportSchema = false )
public abstract class ExpenseDatabase extends RoomDatabase {
    public static ExpenseDatabase instance;

    public abstract ExpenseDao expenseDao();

    public static synchronized ExpenseDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), ExpenseDatabase.class, "expense_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    public static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    public static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ExpenseDao expenseDao;
        private PopulateDbAsyncTask(ExpenseDatabase db) {
            expenseDao = db.expenseDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
//            expenseDao.addExpense(new ExpenseTable("Shoes","1000","Monday","Adidas"));
//            expenseDao.addExpense(new ExpenseTable("Shoes","1000","Monday","Adidas"));
//            expenseDao.addExpense(new ExpenseTable("Shoes","1000","Monday","Adidas"));
//            expenseDao.addExpense(new ExpenseTable("Shoes","1000","Monday","Adidas"));
            return null;
        }
    }
}
