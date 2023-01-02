package com.burakpar.fitnit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FragmentForQuestions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_for_questions);


    }


    public void  toSportQuestions(View view){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        QuestionForSportProgram2 sportQuestions = new QuestionForSportProgram2();
        fragmentTransaction.replace(R.id.frame_layout,sportQuestions).commit();

    }
    public void  toNutritionQuestions(View view){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        QuestionForNutritionProgram2 nutritionQuestions = new QuestionForNutritionProgram2();
        fragmentTransaction.replace(R.id.frame_layout,nutritionQuestions).commit();
    }

    public void toLogin(View view){
        Intent intent = new Intent(FragmentForQuestions.this,MainLogin.class);
        startActivity(intent);
    }


}