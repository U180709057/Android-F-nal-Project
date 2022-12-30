package com.burakpar.fitnit;

import static com.burakpar.fitnit.MainLogin.onlineUserIndex;
import static com.burakpar.fitnit.RegisterActivity.takeFromDataBaseToArrayList;
import static com.burakpar.fitnit.RegisterActivity.usersArrayList;
import static com.burakpar.fitnit.DecideWhatYouWantActivity.*;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.burakpar.fitnit.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {


    private ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        takeFromDataBaseToArrayList();
        binding.nameText.setText(usersArrayList.get(onlineUserIndex).getFull_name());
        try {
            Bitmap bitmap = BitmapFactory.decodeByteArray(usersArrayList.get(onlineUserIndex).getByteArrayImage(),0,usersArrayList.get(onlineUserIndex).getByteArrayImage().length);
            binding.homePicture.setImageBitmap(bitmap);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(usersArrayList.get(onlineUserIndex).getSport() && (usersArrayList.get(onlineUserIndex).getNutrition())){
            // ikiside visible
        }else if(usersArrayList.get(onlineUserIndex).getSport()){
            binding.nutritionLayout.setVisibility(View.GONE);

        }else {
            binding.sportLayout.setVisibility((View.GONE));
        }

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