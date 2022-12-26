package com.burakpar.fitnit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import static com.burakpar.fitnit.RegisterActivity.userDataBase;

import com.burakpar.fitnit.databinding.ActivityMainBinding;


public class MainLogin extends AppCompatActivity {

    private ActivityMainBinding binding;
    public String onlineUserID;

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
            int onlineUserId = cursor.getColumnIndex("id");
            int password = cursor.getColumnIndex("password");

            while (cursor.moveToNext()){
                if (cursor.getString(userNameIndex).matches(userNameWhoWantoBeOnline)){
                    if(cursor.getString(password).matches(passwordWhoWanttoBeOnline)) {
                        onlineUserID = cursor.getString(onlineUserId);
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