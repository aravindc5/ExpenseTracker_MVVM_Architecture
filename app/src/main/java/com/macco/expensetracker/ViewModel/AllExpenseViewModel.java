package com.macco.expensetracker.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.macco.expensetracker.Entity.ExpenseTable;
import com.macco.expensetracker.Repository.ExpenseRepository;

import java.util.List;

public class AllExpenseViewModel extends AndroidViewModel {
    private ExpenseRepository expenseRepository;
    private LiveData<List<ExpenseTable>> getAllExpense;

    public AllExpenseViewModel(@NonNull Application application) {
        super(application);
        expenseRepository = new ExpenseRepository(application);
        getAllExpense = expenseRepository.getAllExpense();
    }
    public LiveData<List<ExpenseTable>> getAllExpense(){
        return getAllExpense;
    }
    public void delete(ExpenseTable expense) {
        expenseRepository.delete(expense);
    }
}
