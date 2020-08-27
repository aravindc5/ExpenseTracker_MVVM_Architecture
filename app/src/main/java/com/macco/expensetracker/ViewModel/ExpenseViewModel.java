package com.macco.expensetracker.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.macco.expensetracker.Entity.ExpenseTable;
import com.macco.expensetracker.Repository.ExpenseRepository;

import java.util.List;

 public class ExpenseViewModel extends AndroidViewModel {

    private ExpenseRepository expenseRepository;
    private LiveData<List<ExpenseTable>> getAllExpense;

    public ExpenseViewModel(@NonNull Application application) {
        super(application);
        expenseRepository = new ExpenseRepository(application);
        getAllExpense = expenseRepository.getAllExpense();
    }
    public void insert(ExpenseTable expense) {
        expenseRepository.add(expense);
    }
    public void update(ExpenseTable expense) {
        expenseRepository.update(expense);
    }
    public void delete(ExpenseTable expense) {
        expenseRepository.delete(expense);
    }
    public void deleteAllExpense() {
        expenseRepository.deleteAllNotes();
    }
    public LiveData<List<ExpenseTable>> getAllExpense(){
        return getAllExpense;
    }

}
