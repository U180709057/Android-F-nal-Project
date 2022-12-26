package com.burakpar.fitnit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }


    public void toProfile(View view){
        Intent intent = new Intent(HomeActivity.this,ProfileActivity.class);
        startActivity(intent);
    }
    public void toMySportProgram(View view){
        Intent intent = new Intent(HomeActivity.this,MySportProgramActivity.class);
        startActivity(intent);
    }

    public void toMyNutritionProgram(View view){
        Intent intent = new Intent(HomeActivity.this,MyNutritionProgramActivity.class);
        startActivity(intent);
    }

    public void toLogin(View view){
        Intent intent = new Intent(HomeActivity.this, MainLogin.class);
        SharedPreferences preferences = getSharedPreferences("checkBox",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("remember","false");
        editor.apply();
        finish();
        startActivity(intent);

    }


}