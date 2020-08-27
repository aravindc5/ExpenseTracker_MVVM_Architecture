package com.macco.expensetracker.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "expense_table")
public class ExpenseTable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String expenseName;
    private String amount;
    @NonNull
    private String date;
    private String description;
    private String category;

    public ExpenseTable(){}

    @Ignore
    public ExpenseTable(String expenseName, String amount, String date, String description,String category) {
        this.expenseName = expenseName;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.category=category;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
