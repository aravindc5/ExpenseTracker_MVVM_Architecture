package com.macco.expensetracker.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.macco.expensetracker.Entity.ExpenseTable;
import com.macco.expensetracker.Repository.ExpenseRepository;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {
    private ExpenseRepository expenseRepository;
    private LiveData<List<ExpenseTable>> getFoodCategory;
    private LiveData<List<ExpenseTable>> getTravelCategory;
    private LiveData<List<ExpenseTable>> getUtilityCategory;
    private LiveData<List<ExpenseTable>> getHealthCategory;
    private LiveData<List<ExpenseTable>> getShoppingCategory;
    private LiveData<List<ExpenseTable>> getOthersCategory;

    public CategoryViewModel(@NonNull Application application) {
        super(application);
        expenseRepository = new ExpenseRepository(application);
        getFoodCategory = expenseRepository.getFoodCategory();
        getTravelCategory = expenseRepository.getTravelCategory();
        getUtilityCategory = expenseRepository.getUtilitiesCategory();
        getHealthCategory = expenseRepository.getHealthCategory();
        getShoppingCategory = expenseRepository.getShoppingCategory();
        getOthersCategory = expenseRepository.getOthersCategory();
    }
    public LiveData<List<ExpenseTable>> getFoodCategory(){
        return getFoodCategory;
    }
    public LiveData<List<ExpenseTable>> getTravelCategory(){
        return getTravelCategory;
    }
    public LiveData<List<ExpenseTable>> getUtilityCategory(){
        return getUtilityCategory;
    }
    public LiveData<List<ExpenseTable>> getHealthCategory(){
        return getHealthCategory;
    }
    public LiveData<List<ExpenseTable>> getShoppingCategory(){
        return getShoppingCategory;
    }
    public LiveData<List<ExpenseTable>> getOthersCategory(){
        return getOthersCategory;
    }
}
