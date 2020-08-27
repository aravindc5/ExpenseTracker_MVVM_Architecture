package com.macco.expensetracker;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.macco.expensetracker.Entity.ExpenseTable;
import com.macco.expensetracker.ViewModel.CategoryViewModel;

import java.util.List;

public class GetTotalAmount extends AppCompatActivity {
    CategoryViewModel categoryViewModel;
    float foodTotal=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryViewModel = new ViewModelProvider(GetTotalAmount.this).get(CategoryViewModel.class);

    }




}
