package com.burakpar.fitnit;

import static com.burakpar.fitnit.RegisterActivity.LoginUserName;
import static com.burakpar.fitnit.RegisterActivity.userDataBase;

import android.content.ContentValues;

import android.database.Cursor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class QuestionForSportProgram2 extends Fragment {
    EditText weightt;
    EditText heightt;

    ViewGroup viewGroup;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        viewGroup = (ViewGroup) inflater.inflate(R.layout.activity_question_for_sport_program2,container,false);
        System.out.println(userDataBase.isOpen());
        weightt = viewGroup.findViewById(R.id.answer1Sport2);
        heightt = viewGroup.findViewById(R.id.answer2Sport2);
        Button button = viewGroup.findViewById(R.id.nextButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Informations Saved",Toast.LENGTH_SHORT).show();
                getAndPushData();
                Cursor cursor = userDataBase.rawQuery("SELECT * FROM users",null);


                while (cursor.moveToNext()){
                    System.out.println("Name : " + cursor.getString( 0));
                    System.out.println("User name : " + cursor.getString(1));
                    System.out.println("E-mail : " + cursor.getString(2));
                    System.out.println("Password : " + cursor.getString( 3));
                    System.out.println("Birthday : " + cursor.getString(4));
                    System.out.println("Phone Number : " + cursor.getString(5));
                    System.out.println("Body mass ındex : " + cursor.getString(6));

                }
                cursor.close();
            }
        });

        return viewGroup;
    }



    public double bodyMassIndex(){
        double weight =  Double.parseDouble(weightt.getText().toString());
        double height = Double.parseDouble(heightt.getText().toString());
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