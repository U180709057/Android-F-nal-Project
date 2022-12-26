package com.burakpar.fitnit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DecideWhatYouWantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decide_what_you_want);
    }



    public void toSportProgramQuestions(View view){
        Intent intent = new Intent(DecideWhatYouWantActivity.this, QuestionForSportProgram.class);
        startActivity(intent);



    }
    public void toNutritionProgramQuestions(View view){
        Intent intent = new Intent(DecideWhatYouWantActivity.this,QuestionForNutritionProgram.class);
        startActivity(intent);

    }




}