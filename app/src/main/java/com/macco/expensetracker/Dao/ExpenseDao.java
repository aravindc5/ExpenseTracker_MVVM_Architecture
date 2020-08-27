package com.macco.expensetracker.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.macco.expensetracker.Entity.ExpenseTable;

import java.util.List;

@Dao
public interface ExpenseDao {
    @Insert
    void addExpense(ExpenseTable expense);
    @Update
    void updateExpense(ExpenseTable expense);
    @Delete
    void deleteExpense(ExpenseTable expense);
    @Query("DELETE FROM expense_table")
    void deleteAllExpense();
    @Query("SELECT * FROM expense_table")
    LiveData<List<ExpenseTable>> selectAllExpense();
    @Query("SELECT * FROM expense_table WHERE category='Food'")
    LiveData<List<ExpenseTable>> selectFoodCategory();
    @Query("SELECT * FROM expense_table WHERE category='Travel'")
    LiveData<List<ExpenseTable>> selectTravelCategory();
    @Query("SELECT * FROM expense_table WHERE category='Utilities'")
    LiveData<List<ExpenseTable>> selectUtilitiesCategory();
    @Query("SELECT * FROM expense_table WHERE category='Health'")
    LiveData<List<ExpenseTable>> selectHealthCategory();
    @Query("SELECT * FROM expense_table WHERE category='Shopping'")
    LiveData<List<ExpenseTable>> selectShoppingCategory();
    @Query("SELECT * FROM expense_table WHERE category='Others'")
    LiveData<List<ExpenseTable>> selectOthersCategory();

    @Query("SELECT * FROM expense_table WHERE category=:categoryName")
    LiveData<List<ExpenseTable>> selectCategory(String categoryName);
}
