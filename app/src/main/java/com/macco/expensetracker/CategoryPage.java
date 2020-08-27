package com.macco.expensetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class CategoryPage extends AppCompatActivity {
    public static String EXTRA_CATEGORY="Category";


    CardView food_Cat,travel_cat,utilities_cat,health_cat,shopping_cat,others_cat;
    Intent navigation_Intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_page);
        getSupportActionBar().hide();
        init();
        food_Cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigate("Food");
            }
        });
        travel_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigate("Travel");
            }
        });
        health_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigate("Health");
            }
        });
        utilities_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigate("Utilities");
            }
        });
        shopping_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigate("Shopping");
            }
        });
        others_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigate("Others");
            }
        });
    }

    private void navigate(String val){
        navigation_Intent=new Intent(CategoryPage.this,Category_details.class);
        navigation_Intent.putExtra(EXTRA_CATEGORY,val);
        startActivity(navigation_Intent);
    }

    private void init() {
        food_Cat=findViewById(R.id.food_cat);
        travel_cat=findViewById(R.id.travel_cat);
        utilities_cat=findViewById(R.id.Utilities_cat);
        health_cat=findViewById(R.id.Health_cat);
        shopping_cat=findViewById(R.id.Shopping_cat);
        others_cat=findViewById(R.id.Others_cat);
    }
}