package com.burakpar.fitnit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.burakpar.fitnit.databinding.ActivityProfileBinding;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;
    ArrayList<String> userInformations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        userInformations = new ArrayList<>();
    }


    /*public ArrayList<String> getData(){       /* Data ları bu fonksiyonla id lerine göre alıyoruz */
        /*String fullName = binding.answer1.getText().toString();
        String userName = binding.answer2.getText().toString();
        String eMAil = binding.answer3.getText().toString();
        String password = binding.answer4.getText().toString();
        String birthday = binding.getBirthDayProfile.getText().toString();
        String phoneNumber = binding.getPhoneProfile.getText().toString();


        userDataBase.execSQL("UPDATE users SET  ");
        return userInformations;
    }*/




    public void toHome(View view){
        Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
        startActivity(intent);
    }

}