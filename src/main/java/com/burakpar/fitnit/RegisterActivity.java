package com.burakpar.fitnit;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.burakpar.fitnit.databinding.ActivityRegisterBinding;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    static String LoginUserName;
    public static SQLiteDatabase userDataBase ;  // SQLite data baseini tanımladık
    static ArrayList<Users> usersArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        userDataBase = this.openOrCreateDatabase("Users",MODE_PRIVATE,null);  // Data Base oluşturduk.
        userDataBase.execSQL("CREATE TABLE IF NOT EXISTS users ( fullName VARCHAR, userName VARCHAR , eMail VARCHAR , password VARCHAR , birthDay VARCHAR , phoneNumber VARCHAR,bmı VARCHAR, image BLOB , sport BOOLEAN, nutrition BOOLEAN )");

        /*Cursor cursor = userDataBase.rawQuery("SELECT * FROM users",null);
        int nameIndex = cursor.getColumnIndex("fullName");
        int userNameIndex = cursor.getColumnIndex("userName");
        int eMail = cursor.getColumnIndex("eMail");
        int password = cursor.getColumnIndex("password");
        int birthDay = cursor.getColumnIndex("birthDay");
        int phoneNumber = cursor.getColumnIndex("phoneNumber");
        while (cursor.moveToNext()){
            System.out.println("Name : " + cursor.getString( nameIndex));
            System.out.println("User name : " + cursor.getString(userNameIndex));
            System.out.println("E-mail : " + cursor.getString(eMail));
            System.out.println("Password : " + cursor.getString( password));
            System.out.println("Birthday : " + cursor.getString(birthDay));
            System.out.println("Phone Number : " + cursor.getString(phoneNumber));
        }
        cursor.close();*/
    }


    public void toLogin(View view){
        Intent intent = new Intent(RegisterActivity.this, MainLogin.class);
        startActivity(intent);
    }

    public Users getData(){       /* Data ları bu fonksiyonla id lerine göre alıyoruz */
        String fullName = binding.answer1.getText().toString();
        String userName = binding.answer2.getText().toString();
        String eMAil = binding.answer3.getText().toString();
        String password = binding.answer4.getText().toString();
        String birthday = binding.getBirthDayProfile.getText().toString();
        String phoneNumber = binding.getPhoneProfile.getText().toString();
        String bmı = "0";
        Users newUser = new Users(fullName,userName,eMAil,password,birthday,phoneNumber);
        newUser.setBmı(bmı);
        return newUser;
    }

    public void pushToData(Users newUser){
        try {
            ContentValues values = new ContentValues();
            values.put("fullName",newUser.getFull_name());
            values.put("userName",newUser.getUser_name());
            values.put("eMail",newUser.getEmail());
            values.put("password",newUser.getPassword());
            values.put("birthDay",newUser.getBirthday());
            values.put("phoneNumber",newUser.getPhone_number());
            values.put("bmı",newUser.getBmı());

            userDataBase.insert("users",null,values);

            takeFromDataBaseToArrayList();

        }catch (Exception e){
            e.printStackTrace();
        }


    }
    public static void takeFromDataBaseToArrayList(){
        Cursor cursor = userDataBase.rawQuery("SELECT * FROM users",null);
        usersArrayList.clear();

        while (cursor.moveToNext()){
            boolean sport = false ;
            boolean nutrition = false ;
            String name = cursor.getString(0);
            String user_name = cursor.getString(1);
            String email = cursor.getString(2);
            String password = cursor.getString(3);
            String birthday = cursor.getString(4);
            String phoneNumber = cursor.getString(5);
            byte[] image = cursor.getBlob(7);
            if(cursor.getInt(8) == 0){
                sport = false;
            }else
                sport = true;

            if(cursor.getInt(9) == 0){
                nutrition = false;
            }else
                nutrition = true;



            Users newUser = new Users(name, user_name, email, password, birthday, phoneNumber);
            newUser.setByteArrayImage(image);
            newUser.setSport(sport);
            newUser.setNutrition(nutrition);
            usersArrayList.add(newUser);
        }

    }


    public void toWhatYouDesire(View view){
        Intent intent = new Intent(RegisterActivity.this, DecideWhatYouWantActivity.class);
        if(binding.registerCheckBox.isChecked()) {
            String[] checkEmailArray = binding.answer3.getText().toString().split("");
            boolean isTrueEmail = false;
            for(int i = 0 ; i< checkEmailArray.length; i++){
                if(checkEmailArray[i].matches("@")){
                    isTrueEmail = true;
                    break;
                }
                else
                    isTrueEmail = false;
            }
            if (isTrueEmail ){
                try {
                    boolean isCheckUsername = false;
                    Cursor cursor = userDataBase.rawQuery("SELECT * FROM users",null);
                    int userNameIndex = cursor.getColumnIndex("userName");
                    int isThereRow = 0;
                    while (cursor.moveToNext()){
                        isThereRow = 1;
                        if(cursor.getString(userNameIndex).matches(binding.answer2.getText().toString())){
                            isCheckUsername = false;
                            break;
                        }else{
                            isCheckUsername = true;
                        }
                    }
                    cursor.close();
                    if(isThereRow == 0){
                        isCheckUsername = true;
                    }
                    if(isCheckUsername) {
                        LoginUserName = binding.answer2.getText().toString();;
                        pushToData(getData());
                        startActivity(intent);
                    }else{
                        Toast.makeText(RegisterActivity.this, "This Username is taken",Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                Toast.makeText(RegisterActivity.this,"Please enter the correct e-mail",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(RegisterActivity.this,"Please accept the conditions.",Toast.LENGTH_LONG).show();
        }
    }


    public void showTerms(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Conditions");
        alert.setMessage("1. Lorem Ipsum is simply dummy text of the printing and typesetting industry.\n " +
                "2. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.\n" +
                "3. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.|n" +
                "4. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software.\n" +
                "5. Like Aldus PageMaker including versions of Lorem Ipsum.");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(RegisterActivity.this,"Conditions Accepted",Toast.LENGTH_LONG).show();
                binding.registerCheckBox.setChecked(true);
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(RegisterActivity.this,"You should accept conditions",Toast.LENGTH_LONG).show();
                binding.registerCheckBox.setChecked(false);
            }
        });
        alert.show();

    }


}