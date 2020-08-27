package com.macco.expensetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.macco.expensetracker.Adapters.AllTransactionAdapter;
import com.macco.expensetracker.Entity.ExpenseTable;
import com.macco.expensetracker.ViewModel.AllExpenseViewModel;
import com.macco.expensetracker.ViewModel.CategoryViewModel;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

import java.util.List;

public class Category_details extends AppCompatActivity {
    RecyclerView category_view;
    CategoryViewModel categoryViewModel;
    float totalSpending=0;
    TickerView totalSpendingtext;
    TextView categoryTitle;
    ImageButton backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_details);
        getSupportActionBar().hide();
        backbtn=findViewById(R.id.back);
        category_view=findViewById(R.id.recycler_view3);
        totalSpendingtext=findViewById(R.id.totalspending);
        categoryTitle=findViewById(R.id.category_title);
        totalSpendingtext.setCharacterLists(TickerUtils.provideNumberList());
        category_view.setLayoutManager(new LinearLayoutManager(this));
        String getkey=getIntent().getStringExtra(CategoryPage.EXTRA_CATEGORY);
        Toast.makeText(this, "selected key"+getkey, Toast.LENGTH_SHORT).show();
        final AllTransactionAdapter adapter = new AllTransactionAdapter();
        category_view.setAdapter(adapter);
        categoryViewModel = new ViewModelProvider(Category_details.this).get(CategoryViewModel.class);
        assert getkey != null;
        switch (getkey){
            case "Food":
            categoryTitle.setText("Food Expenses");
                categoryViewModel.getFoodCategory().observe(this, new Observer<List<ExpenseTable>>() {
                @Override
                public void onChanged(List<ExpenseTable> expenseTables) {
                    adapter.setNotes(expenseTables);
                    for(int i=0;i<expenseTables.size();i++) {
                        totalSpending = totalSpending + Float.parseFloat(expenseTables.get(i).getAmount());
                    }
                    totalSpendingtext.setText("Rs "+totalSpending);
                }
            });
            break;
            case "Travel":    categoryTitle.setText("Travel Expenses");
                categoryViewModel.getTravelCategory().observe(this, new Observer<List<ExpenseTable>>() {
                @Override
                public void onChanged(List<ExpenseTable> expenseTables) {
                    adapter.setNotes(expenseTables);
                    for(int i=0;i<expenseTables.size();i++) {
                        totalSpending = totalSpending + Float.parseFloat(expenseTables.get(i).getAmount());
                    }
                    totalSpendingtext.setText("Rs "+totalSpending);
                }
            });
                break;
            case "Utilities":  categoryTitle.setText("Utilities Expenses");
                categoryViewModel.getUtilityCategory().observe(this, new Observer<List<ExpenseTable>>() {
                @Override
                public void onChanged(List<ExpenseTable> expenseTables) {
                    adapter.setNotes(expenseTables);
                    for(int i=0;i<expenseTables.size();i++) {
                        totalSpending = totalSpending + Float.parseFloat(expenseTables.get(i).getAmount());
                    }
                    totalSpendingtext.setText("Rs "+totalSpending);
                }
            });
                break;
            case "Health": categoryTitle.setText("Health Expenses");
                categoryViewModel.getHealthCategory().observe(this, new Observer<List<ExpenseTable>>() {
                @Override
                public void onChanged(List<ExpenseTable> expenseTables) {
                    adapter.setNotes(expenseTables);
                    for(int i=0;i<expenseTables.size();i++) {
                        totalSpending = totalSpending + Float.parseFloat(expenseTables.get(i).getAmount());
                    }
                    totalSpendingtext.setText("Rs "+totalSpending);
                }
            });
                break;
            case "Shopping":  categoryTitle.setText("Shopping Expenses");
                categoryViewModel.getShoppingCategory().observe(this, new Observer<List<ExpenseTable>>() {
                @Override
                public void onChanged(List<ExpenseTable> expenseTables) {
                    adapter.setNotes(expenseTables);
                    for(int i=0;i<expenseTables.size();i++) {
                        totalSpending = totalSpending + Float.parseFloat(expenseTables.get(i).getAmount());
                    }
                    totalSpendingtext.setText("Rs "+totalSpending);
                }
            });
                break;
            case "Others":   categoryTitle.setText("Others Expenses");
                categoryViewModel.getOthersCategory().observe(this, new Observer<List<ExpenseTable>>() {
                @Override
                public void onChanged(List<ExpenseTable> expenseTables) {
                    adapter.setNotes(expenseTables);
                    for(int i=0;i<expenseTables.size();i++) {
                        totalSpending = totalSpending + Float.parseFloat(expenseTables.get(i).getAmount());
                    }
                    totalSpendingtext.setText("Rs "+totalSpending);
                }
            });
                break;




        }
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

    }
}