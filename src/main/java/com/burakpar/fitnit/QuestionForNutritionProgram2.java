package com.burakpar.fitnit;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class QuestionForNutritionProgram2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_for_nutrition_program2);
    }


    public void toLogin(View view){
        Intent intent = new Intent(QuestionForNutritionProgram2.this, MainLogin.class);
        startActivity(intent);
    }
}