package com.burakpar.fitnit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MyNutritionProgramActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_nutrition_program);
    }

    public void toHome(View view){
        Intent intent = new Intent(MyNutritionProgramActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}