package com.burakpar.fitnit;

import static com.burakpar.fitnit.RegisterActivity.takeFromDataBaseToArrayList;
import static com.burakpar.fitnit.RegisterActivity.userDataBase;
import static com.burakpar.fitnit.RegisterActivity.usersArrayList;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.burakpar.fitnit.databinding.ActivityMainBinding;


public class MainLogin extends AppCompatActivity {


    public static int onlineUserIndex ;
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        RememberMeCheckBox(binding.checkBox);

        SharedPreferences preferences = getSharedPreferences("checkBox",MODE_PRIVATE);
        String checkbox = preferences.getString("remember","");
        if(checkbox.equals("true")){
            Intent intent = new Intent(MainLogin.this,HomeActivity.class);
            startActivity(intent);
        }else if(checkbox.equals("false")){
            Toast.makeText(this,"Please sing ın", Toast.LENGTH_SHORT).show();
        }

    }

    public void toRegister(View view){
        Intent intent = new Intent(MainLogin.this,RegisterActivity.class);
        startActivity(intent);

    }

    public void toHome(View view){
        Intent intent = new Intent(MainLogin.this,HomeActivity.class);
        String userNameWhoWantoBeOnline =binding.takeUserName.getText().toString();
        String passwordWhoWanttoBeOnline = binding.takePassword.getText().toString();
        userDataBase = this.openOrCreateDatabase("Users",MODE_PRIVATE,null);

        boolean isTrueInfo = true;
        try {
            Cursor cursor = userDataBase.rawQuery("SELECT * FROM users",null);
            int userNameIndex = cursor.getColumnIndex("userName");
            int password = cursor.getColumnIndex("password");
            takeFromDataBaseToArrayList();
            while (cursor.moveToNext()){
                if (cursor.getString(userNameIndex).matches(userNameWhoWantoBeOnline)){
                    if(cursor.getString(password).matches(passwordWhoWanttoBeOnline)) {
                            onlineUserIndex = 0;

                        for(int i = 0 ; i< usersArrayList.size() ; i++){
                            if(userNameWhoWantoBeOnline.matches(usersArrayList.get(i).user_name) ){
                                onlineUserIndex = i;
                                break;
                            }
                        }
                        startActivity(intent);
                        isTrueInfo = false;
                        break;
                    }
                }
            }
            if (isTrueInfo) {
                Toast.makeText(MainLogin.this, "Invalid Username or Password", Toast.LENGTH_LONG).show();
            }
            cursor.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void RememberMeCheckBox(CheckBox checkBox){

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkBox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","true");
                    editor.apply();
                    Toast.makeText(MainLogin.this,"Checked",Toast.LENGTH_SHORT).show();
                }else if(!compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkBox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();
                    Toast.makeText(MainLogin.this,"Unchecked",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void exitFromApp(View view){
        System.exit(0);
    }



}