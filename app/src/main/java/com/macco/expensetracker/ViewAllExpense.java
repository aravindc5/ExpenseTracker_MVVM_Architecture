package com.macco.expensetracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.macco.expensetracker.Adapters.AllTransactionAdapter;
import com.macco.expensetracker.Adapters.ExpenseAdapter;
import com.macco.expensetracker.Entity.ExpenseTable;
import com.macco.expensetracker.ViewModel.AllExpenseViewModel;

import java.util.List;

public class ViewAllExpense extends AppCompatActivity {
    RecyclerView expenseView;
    AllExpenseViewModel expenseViewModel;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_view_all_expense);
        expenseView = findViewById(R.id.recycler_view2);
        back = findViewById(R.id.back_all);
        expenseView.setLayoutManager(new LinearLayoutManager(this));
        final AllTransactionAdapter adapter = new AllTransactionAdapter();
        expenseView.setAdapter(adapter);
        expenseViewModel = new ViewModelProvider(ViewAllExpense.this).get(AllExpenseViewModel.class);
        expenseViewModel.getAllExpense().observe(this, new Observer<List<ExpenseTable>>() {
            @Override
            public void onChanged(List<ExpenseTable> expenseTables) {
                adapter.setNotes(expenseTables);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                expenseViewModel.delete(adapter.getPos(viewHolder.getAdapterPosition()));
                Toast.makeText(ViewAllExpense.this, "Expense removed", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(expenseView);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}