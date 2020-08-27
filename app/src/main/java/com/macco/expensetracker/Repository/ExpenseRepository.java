package com.macco.expensetracker.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.macco.expensetracker.Dao.ExpenseDao;
import com.macco.expensetracker.Database.ExpenseDatabase;
import com.macco.expensetracker.Entity.ExpenseTable;

import java.util.List;

public class ExpenseRepository {

    private ExpenseDao expenseDao;
    private LiveData<List<ExpenseTable>> getAllExpense;
    private LiveData<List<ExpenseTable>> getFoodCategory;
    private LiveData<List<ExpenseTable>> getTravelCategory;
    private LiveData<List<ExpenseTable>> getUtilitiesCategory;
    private LiveData<List<ExpenseTable>> getHealthCategory;
    private LiveData<List<ExpenseTable>> getShoppingCategory;
    private LiveData<List<ExpenseTable>> getOthersCategory;

    public ExpenseRepository(Application application){
        ExpenseDatabase expenseDatabase=ExpenseDatabase.getInstance(application);
        expenseDao=expenseDatabase.expenseDao();
        getAllExpense=expenseDao.selectAllExpense();
        getFoodCategory=expenseDao.selectFoodCategory();
        getTravelCategory=expenseDao.selectTravelCategory();
        getUtilitiesCategory=expenseDao.selectUtilitiesCategory();
        getHealthCategory=expenseDao.selectHealthCategory();
        getShoppingCategory=expenseDao.selectShoppingCategory();
        getOthersCategory=expenseDao.selectOthersCategory();
    }

    public void add(ExpenseTable expense){
        new InsertNoteAsyncTask(expenseDao).execute(expense);
    }

    public void update(ExpenseTable expense) {
        new UpdateNoteAsyncTask(expenseDao).execute(expense);
    }

    public void delete(ExpenseTable expense) {
        new DeleteNoteAsyncTask(expenseDao).execute(expense);
    }

    public void deleteAllNotes() {
        new DeleteAllNotesAsyncTask(expenseDao).execute();
    }

    public  LiveData<List<ExpenseTable>> getAllExpense(){
        return getAllExpense;
    }
    public  LiveData<List<ExpenseTable>> getFoodCategory(){
        return getFoodCategory;
    }
    public  LiveData<List<ExpenseTable>> getUtilitiesCategory(){
        return getUtilitiesCategory;
    }
    public  LiveData<List<ExpenseTable>> getTravelCategory(){
        return getTravelCategory;
    }
    public  LiveData<List<ExpenseTable>> getHealthCategory(){
        return getHealthCategory;
    }
    public  LiveData<List<ExpenseTable>> getShoppingCategory(){
        return getShoppingCategory;
    }
    public  LiveData<List<ExpenseTable>> getOthersCategory(){
        return getOthersCategory;
    }


    public static class InsertNoteAsyncTask extends AsyncTask<ExpenseTable, Void, Void> {
        private ExpenseDao expenseDao;
        private InsertNoteAsyncTask(ExpenseDao expenseDao) {
            this.expenseDao = expenseDao;
        }
        @Override
        protected Void doInBackground(ExpenseTable... expenses) {
            expenseDao.addExpense(expenses[0]);
            return null;
        }
    }
    public static class UpdateNoteAsyncTask extends AsyncTask<ExpenseTable, Void, Void> {
        private ExpenseDao expenseDao;
        private UpdateNoteAsyncTask(ExpenseDao expenseDao) {
            this.expenseDao = expenseDao;
        }
        @Override
        protected Void doInBackground(ExpenseTable... expenses) {
            expenseDao.updateExpense(expenses[0]);
            return null;
        }
    }

    public static class DeleteNoteAsyncTask extends AsyncTask<ExpenseTable, Void, Void> {
        private ExpenseDao expenseDao;
        public DeleteNoteAsyncTask(ExpenseDao expenseDao) {
            this.expenseDao=expenseDao;
        }
        @Override
        protected Void doInBackground(ExpenseTable... expenses) {
            expenseDao.deleteExpense(expenses[0]);
            return null;
        }
    }

    public static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private ExpenseDao expenseDao;
        private DeleteAllNotesAsyncTask(ExpenseDao expenseDao) {
            this.expenseDao = expenseDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            expenseDao.deleteAllExpense();
            return null;
        }
    }


}
