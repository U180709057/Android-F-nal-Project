package com.burakpar.fitnit;

import static com.burakpar.fitnit.MainLogin.onlineUserIndex;
import static com.burakpar.fitnit.RegisterActivity.userDataBase;
import static com.burakpar.fitnit.RegisterActivity.usersArrayList;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import static com.burakpar.fitnit.RegisterActivity.*;


import androidx.appcompat.app.AppCompatActivity;

public class DecideWhatYouWantActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decide_what_you_want);
        userDataBase = this.openOrCreateDatabase("Users",MODE_PRIVATE,null);

    }



    public void toSportProgramQuestions(View view){
        Intent intent = new Intent(DecideWhatYouWantActivity.this, QuestionForSportProgram.class);


        for(int i = 0 ; i <usersArrayList.size();i++){
            if(usersArrayList.get(i).getUser_name().matches(LoginUserName)){
                usersArrayList.get(i).setSport(true);
                usersArrayList.get(i).setNutrition(false);
            }
        }
        ContentValues value = new ContentValues();
        try {
            value.put("sport", 1);
            value.put("nutrition",0 );
            userDataBase.update("users", value, "userName = ?", new String[]{LoginUserName});
            takeFromDataBaseToArrayList();
        }catch (Exception e){
            e.printStackTrace();
        }
        startActivity(intent);

    }


    public void toNutritionProgramQuestions(View view){
        Intent intent = new Intent(DecideWhatYouWantActivity.this,QuestionForNutritionProgram.class);


        for(int i = 0 ; i <usersArrayList.size();i++){
            if(usersArrayList.get(i).getUser_name().matches(LoginUserName)){
                usersArrayList.get(i).setSport(false);
                usersArrayList.get(i).setNutrition(true);
            }
        }
        ContentValues value = new ContentValues();
        try {
            value.put("sport", 0);
            value.put("nutrition",1 );
            userDataBase.update("users", value, "userName = ?", new String[]{LoginUserName});
            takeFromDataBaseToArrayList();
        }catch (Exception e){
            e.printStackTrace();
        }
        startActivity(intent);


    }

    public void toSportQuestions2(View view){
        Intent intent = new Intent(DecideWhatYouWantActivity.this,QuestionForSportProgram2.class);
        for(int i = 0 ; i <usersArrayList.size();i++){
            if(usersArrayList.get(i).getUser_name().matches(LoginUserName)){
                usersArrayList.get(i).setSport(true);
                usersArrayList.get(i).setNutrition(true);
            }
        }

        ContentValues value = new ContentValues();
        try {
            value.put("sport", 1);
            value.put("nutrition",1 );
            userDataBase.update("users", value, "userName = ?", new String[]{LoginUserName});
            takeFromDataBaseToArrayList();
        }catch (Exception e){
            e.printStackTrace();
        }


        startActivity(intent);

    }




}