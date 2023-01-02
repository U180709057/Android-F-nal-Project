package com.burakpar.fitnit;

import static com.burakpar.fitnit.RegisterActivity.LoginUserName;
import static com.burakpar.fitnit.RegisterActivity.userDataBase;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.burakpar.fitnit.databinding.ActivityQuestionForNutritionProgramBinding;

public class QuestionForNutritionProgram extends AppCompatActivity {
    private ActivityQuestionForNutritionProgramBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionForNutritionProgramBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        userDataBase = this.openOrCreateDatabase("Users",MODE_PRIVATE,null);
    }

    public void toLogin(View view){
        getAndPushData();
        Intent intent = new Intent(QuestionForNutritionProgram.this, MainLogin.class);
        startActivity(intent);
    }



    public double bodyMassIndex(){
        double weight =  Double.parseDouble(binding.answer1Nutrition.getText().toString());

        double height = Double.parseDouble(binding.answer2Nutrition.getText().toString());
        double sonuc = (weight / ((height /100) * height/100) );
        return sonuc;
    }


    public void getAndPushData(){
        try {
            ContentValues value = new ContentValues();
            value.put("bmı",String.valueOf(bodyMassIndex()));
            userDataBase.update("users",value,"userName = ?" ,new String[] {LoginUserName});

        }catch (Exception e){
            e.printStackTrace();
        }

    }


}