package com.burakpar.fitnit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class QuestionForNutritionProgram extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_for_nutrition_program);
    }

    public void toLogin(View view){
        Intent intent = new Intent(QuestionForNutritionProgram.this, MainLogin.class);
        startActivity(intent);
    }


}