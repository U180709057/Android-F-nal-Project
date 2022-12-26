package com.burakpar.fitnit;

import static com.burakpar.fitnit.RegisterActivity.userDataBase;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import android.support.v7.app.AppCompatActivity;
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




    }



    public void toLogin(View view){
        getAndPushData();
        Cursor cursor = userDataBase.rawQuery("SELECT * FROM users",null);

        int nameIndex = cursor.getColumnIndex("fullName");
        int userNameIndex = cursor.getColumnIndex("userName");
        int eMail = cursor.getColumnIndex("eMail");
        int password = cursor.getColumnIndex("password");
        int birthDay = cursor.getColumnIndex("birthDay");
        int phoneNumber = cursor.getColumnIndex("phoneNumber");
        int BMI = cursor.getColumnIndex("bmı");

        while (cursor.moveToNext()){
            System.out.println("Name : " + cursor.getString( nameIndex));
            System.out.println("User name : " + cursor.getString(userNameIndex));
            System.out.println("E-mail : " + cursor.getString(eMail));
            System.out.println("Password : " + cursor.getString( password));
            System.out.println("Birthday : " + cursor.getString(birthDay));
            System.out.println("Phone Number : " + cursor.getString(phoneNumber));
            System.out.println("Body mass ındex : " + cursor.getString(BMI));

        }
        cursor.close();
        Intent intent = new Intent(QuestionForSportProgram.this, MainLogin.class);
        startActivity(intent);
    }

    public int bodyMassIndex(){
        int weight = Integer.parseInt(binding.answer1Sport.getText().toString());
        int height = Integer.parseInt(binding.answer2Sport.getText().toString());

        return (int) (weight / Math.sqrt((height / 100)));
    }
    public void getAndPushData(){
        try {

            userDataBase.execSQL("ALTER TABLE users ADD COLUMN bmı VARCHAR(45) DEFAULT 0 ");
            String sqlString = "INSERT INTO users (fullName ,userName,eMail,password,birthDay,phoneNumber,bmı) VALUES (?,?,?,?,?,?) ";
            SQLiteStatement sqLiteStatement = userDataBase.compileStatement(sqlString);  // data base ile kullanıcının girdiği register değerlerinii bind ile data base attık.
            String bmı =String.valueOf(bodyMassIndex());
            sqLiteStatement.bindString(7,bmı);
            sqLiteStatement.execute();


        }catch (Exception e){
            e.printStackTrace();
        }

    }





}