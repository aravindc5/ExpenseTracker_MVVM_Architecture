package com.macco.expensetracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.developer.kalert.KAlertDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.macco.expensetracker.Adapters.ExpenseAdapter;
import com.macco.expensetracker.Entity.ExpenseTable;
import com.macco.expensetracker.ViewModel.ExpenseViewModel;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;
    ExpenseViewModel expenseViewModel;
    TextView deleteAll;
    TextView no_expense;
    TextView transactionText;
    TextView viewAllTransaction;
    LinearLayoutManager HorizontalLayout;
    Button category_button,pie_chart_button;
    MaterialDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton buttonAddNote = findViewById(R.id.button_add_note);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddExpense.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });
        getSupportActionBar().hide();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        deleteAll = findViewById(R.id.deleteAllExpense);
        no_expense = findViewById(R.id.no_expense);
        transactionText = findViewById(R.id.transaction);
        viewAllTransaction=findViewById(R.id.viewAllTransaction);
        category_button=findViewById(R.id.category_button);

//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final ExpenseAdapter adapter = new ExpenseAdapter();
        HorizontalLayout
                = new LinearLayoutManager(
                MainActivity.this,
                LinearLayoutManager.HORIZONTAL,
                false);

        recyclerView.setLayoutManager(HorizontalLayout);
        recyclerView.setAdapter(adapter);

        expenseViewModel = new ViewModelProvider(this).get(ExpenseViewModel.class);
        expenseViewModel.getAllExpense().observe(this, new Observer<List<ExpenseTable>>() {
            @Override
            public void onChanged(List<ExpenseTable> expenseTables) {
                if (expenseTables.isEmpty()) {
                    no_expense.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.INVISIBLE);
                    deleteAll.setVisibility(View.INVISIBLE);
                    transactionText.setVisibility(View.INVISIBLE);
                    viewAllTransaction.setVisibility(View.INVISIBLE);
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                    deleteAll.setVisibility(View.VISIBLE);
                    transactionText.setVisibility(View.VISIBLE);
                    no_expense.setVisibility(View.INVISIBLE);
                    viewAllTransaction.setVisibility(View.VISIBLE);
                }

                adapter.setNotes(expenseTables);
            }
        });

        //ViewAllTransaction
        viewAllTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ViewAllExpense.class);
                startActivity(intent);
            }
        });

        //On Expense CLick
        adapter.setOnItemClickListner(new ExpenseAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(ExpenseTable expense) {
                Intent intent = new Intent(MainActivity.this, AddExpense.class);
                intent.putExtra(AddExpense.EXTRA_TITLE, expense.getExpenseName());
                intent.putExtra(AddExpense.EXTRA_AMOUNT, expense.getAmount());
                intent.putExtra(AddExpense.EXTRA_DESCRIPTION, expense.getDescription());
                intent.putExtra(AddExpense.EXTRA_DATE, expense.getDate());
                intent.putExtra(AddExpense.EXTRA_ID, expense.getId());
                startActivityForResult(intent, EDIT_NOTE_REQUEST);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.UP ) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                expenseViewModel.delete(adapter.getPos(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Expense removed", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);


         //To delete all Expenses
        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.show();


            }
        });

        //Go to category page
        category_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,CategoryPage.class);
                startActivity(intent);
            }
        });

     //Material Dialog
         mDialog = new MaterialDialog.Builder(this)
                .setTitle("Delete?")
                .setMessage("Are you sure want to delete this Expense?")
                .setCancelable(false)
                .setPositiveButton("Delete", R.drawable.ic_delete, new MaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        // Delete Operation
                        expenseViewModel.deleteAllExpense();
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("Cancel", R.drawable.ic_close, new MaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                    }
                })
                .build();



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            String expenseName = data.getStringExtra(AddExpense.EXTRA_TITLE);
            String description = data.getStringExtra(AddExpense.EXTRA_DESCRIPTION);
            String amount = data.getStringExtra(AddExpense.EXTRA_AMOUNT);
            String date = data.getStringExtra(AddExpense.EXTRA_DATE);
            String category=data.getStringExtra(AddExpense.EXTRA_CATEGORY);
            ExpenseTable expenseTable = new ExpenseTable(expenseName, amount, date, description,category);
            expenseViewModel.insert(expenseTable);
            Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddExpense.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Cannot Update", Toast.LENGTH_SHORT).show();
                return;
            }
            String expenseName = data.getStringExtra(AddExpense.EXTRA_TITLE);
            String description = data.getStringExtra(AddExpense.EXTRA_DESCRIPTION);
            String amount = data.getStringExtra(AddExpense.EXTRA_AMOUNT);
            String date = data.getStringExtra(AddExpense.EXTRA_DATE);
            String category=data.getStringExtra(AddExpense.EXTRA_CATEGORY);
            ExpenseTable expense = new ExpenseTable(expenseName, amount, date, description,category);
            expense.setId(id);
            expenseViewModel.update(expense);
            Toast.makeText(this, "Updated your Expense!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Not Added", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_notes:
                expenseViewModel.deleteAllExpense();
                Toast.makeText(this, "All expenses deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}