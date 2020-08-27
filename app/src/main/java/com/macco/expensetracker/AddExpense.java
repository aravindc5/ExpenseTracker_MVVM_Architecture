package com.macco.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.util.Calendar;

public class AddExpense extends AppCompatActivity {
    public static final String EXTRA_ID = "id_input";
    public static final String EXTRA_TITLE = "name_input";
    public static final String EXTRA_AMOUNT = "amount_input";
    public static final String EXTRA_DATE = "date_input";
    public static final String EXTRA_DESCRIPTION = "desc_input";
    public static final String EXTRA_CATEGORY = "category_input";
    private TextInputEditText name;
    private TextInputEditText amount;
    private MaterialTextView date;
    private TextInputEditText desc;
    private MaterialTextView selectdate;
    private MaterialTextView title;
    private MaterialButton save;
    private int mYear, mMonth, mDay;
    private ChipGroup chipGroup;
    private String category;
    Boolean isSelected=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        getSupportActionBar().hide();
        name = findViewById(R.id.name_input);
        amount = findViewById(R.id.amount_input);
        date = findViewById(R.id.date_input);
        desc = findViewById(R.id.description_input);
        selectdate = findViewById(R.id.selectDate);
        save=findViewById(R.id.addButton);
        title=findViewById(R.id.titleID);
        chipGroup=findViewById(R.id.categoryChipGroup);
        selectdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(AddExpense.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent data = getIntent();
        if(data.hasExtra(EXTRA_ID)){
            setTitle("Edit Expense");
            title.setText("Update Expense");
            name.setText(data.getStringExtra(EXTRA_TITLE));
            amount.setText(data.getStringExtra(EXTRA_AMOUNT));
            date.setText(data.getStringExtra(EXTRA_DATE));
            desc.setText(data.getStringExtra(EXTRA_DESCRIPTION));
        }
        else{
            setTitle("Add Expense");
            title.setText("Add Expense");
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
        //ChipGroup
        chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                Chip chip = chipGroup.findViewById(checkedId);
                if(chip!=null){
                    category=chip.getText().toString();
                    isSelected=true;
                    Toast.makeText(AddExpense.this, ""+chip.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    private void saveNote() {
        String nameText = name.getText().toString();
        String descText = desc.getText().toString();
        String amountText = amount.getText().toString();
        String dateText = date.getText().toString();
        String categoryData=category;
        if (nameText.trim().isEmpty() || descText.trim().isEmpty() || amountText.trim().isEmpty() || dateText.trim().isEmpty() || !isSelected) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, nameText);
        data.putExtra(EXTRA_DESCRIPTION, descText);
        data.putExtra(EXTRA_AMOUNT, amountText);
        data.putExtra(EXTRA_DATE, dateText);
        data.putExtra(EXTRA_CATEGORY,categoryData);
        int id=getIntent().getIntExtra(EXTRA_ID,-1);
        if (id != -1) {
            data.putExtra(EXTRA_ID,id);
        }
        setResult(RESULT_OK, data);
        finish();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_expense:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}