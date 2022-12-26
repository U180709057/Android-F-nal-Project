package com.burakpar.fitnit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MySportProgramActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sport_program);
    }

    public void toHome(View view){
        Intent intent = new Intent(MySportProgramActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}