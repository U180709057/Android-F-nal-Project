package com.burakpar.fitnit;

import static com.burakpar.fitnit.RegisterActivity.userDataBase;
import static com.burakpar.fitnit.RegisterActivity.LoginUserName;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.burakpar.fitnit.databinding.ActivityQuestionForSportProgramBinding;

public class QuestionForSportProgram extends AppCompatActivity {

    private ActivityQuestionForSportProgramBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionForSportProgramBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        userDataBase = this.openOrCreateDatabase("Users",MODE_PRIVATE,null);



    }



    public void toLogin(View view){
        getAndPushData();
        Cursor cursor = userDataBase.rawQuery("SELECT * FROM users",null);

        /*while (cursor.moveToNext()){
            System.out.println("Name : " + cursor.getString( 0));
            System.out.println("User name : " + cursor.getString(1));
            System.out.println("E-mail : " + cursor.getString(2));
            System.out.println("Password : " + cursor.getString( 3));
            System.out.println("Birthday : " + cursor.getString(4));
            System.out.println("Phone Number : " + cursor.getString(5));
            System.out.println("Body mass ındex : " + cursor.getString(6));

        }
        cursor.close();*/
        Intent intent = new Intent(QuestionForSportProgram.this, MainLogin.class);
        startActivity(intent);
    }

    public double bodyMassIndex(){
        double weight =  Double.parseDouble(binding.answer1Sport.getText().toString());

        double height = Double.parseDouble(binding.answer2Sport.getText().toString());
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