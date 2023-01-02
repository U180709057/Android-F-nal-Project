package com.burakpar.fitnit;


import static com.burakpar.fitnit.MainLogin.onlineUserIndex;
import static com.burakpar.fitnit.RegisterActivity.takeFromDataBaseToArrayList;
import static com.burakpar.fitnit.RegisterActivity.userDataBase;
import static com.burakpar.fitnit.RegisterActivity.usersArrayList;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.burakpar.fitnit.databinding.ActivityProfileBinding;
import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class ProfileActivity extends AppCompatActivity {
    Bitmap selectedImage;
    private ActivityProfileBinding binding;
    ActivityResultLauncher<Intent> activityResultLauncher;
    ActivityResultLauncher<String> permissionLauncher;
    boolean isPhotoSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        userDataBase = this.openOrCreateDatabase("Users",MODE_PRIVATE,null);
        takeFromDataBaseToArrayList();

        registerLauncher();

        try {
            Bitmap bitmap = BitmapFactory.decodeByteArray(usersArrayList.get(onlineUserIndex).getByteArrayImage(),0,usersArrayList.get(onlineUserIndex).getByteArrayImage().length);
            binding.profileImage.setImageBitmap(bitmap);
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            Cursor cursor3 = userDataBase.rawQuery("SELECT * FROM users",null);
            while (cursor3.moveToNext()){
                if(cursor3.getString(1).matches(usersArrayList.get(onlineUserIndex).user_name)){
                    binding.answer1.setHint(cursor3.getString(0));
                    binding.answer2.setHint(cursor3.getString(1));
                    binding.answer3.setHint(cursor3.getString(2));
                    binding.answer4.setHint(cursor3.getString(3));
                    binding.getBirthDayProfile.setHint(cursor3.getString(4));
                    binding.getPhoneProfile.setHint(cursor3.getString(5));
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }


    }




    public void save(View view) {
        boolean isCheckEmail = true;
        boolean isCheckUserName = true;



        String full_name = binding.answer1.getText().toString();
        String user_name = binding.answer2.getText().toString();
        String email = binding.answer3.getText().toString();
        String password = binding.answer4.getText().toString();
        String birthday = binding.getBirthDayProfile.getText().toString();
        String phone_number = binding.getPhoneProfile.getText().toString();
        userDataBase = this.openOrCreateDatabase("Users",MODE_PRIVATE,null);
        Cursor cursor = userDataBase.rawQuery("SELECT * FROM users",null);



        while (cursor.moveToNext()){
            if(cursor.getString(1).matches(user_name)  ) {
                isCheckUserName = false;
                break;
            }else
                isCheckUserName = true;
        }

        if(user_name.isEmpty()){
            user_name = binding.answer2.getHint().toString();
            isCheckUserName = true;
        }

        if(full_name.isEmpty())
            full_name = binding.answer1.getHint().toString();

        if(password.isEmpty())
            password = binding.answer4.getHint().toString();




        if(email.contains("@")){
            isCheckEmail = true;
        }else
            isCheckEmail = false;

        if(email.isEmpty()){
            email = binding.answer3.getHint().toString();
            isCheckEmail = true;
        }
        if(phone_number.isEmpty()){
            phone_number = binding.getPhoneProfile.getHint().toString();
        }

        if(birthday.isEmpty()){
            birthday = binding.getBirthDayProfile.getHint().toString();
        }



        if (isCheckUserName){

            if(isCheckEmail) {

                try {

                    ContentValues value = new ContentValues();

                    value.put("fullName", full_name);
                    value.put("userName", user_name);

                    value.put("eMail", email);
                    value.put("password", password);
                    value.put("birthDay", birthday);
                    value.put("phoneNumber", phone_number);

                    if(isPhotoSelected){
                        byte[] byteImage = getBitmapAsByteArray(selectedImage);
                        value.put("image", byteImage);
                    }
                    userDataBase.update("users", value, "userName = ?", new String[]{usersArrayList.get(onlineUserIndex).getUser_name()});
                    takeFromDataBaseToArrayList();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }else{
                Toast.makeText(ProfileActivity.this, "Please enter the correct email!", Toast.LENGTH_SHORT).show();
            }
        }else
            Toast.makeText(ProfileActivity.this, "This username is taken!", Toast.LENGTH_SHORT).show();

        showDataBase();

    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, outputStream);
        return outputStream.toByteArray();
    }

    public void showDataBase(){
        Cursor cursor2 = userDataBase.rawQuery("SELECT * FROM users",null);
        while (cursor2.moveToNext()){
            System.out.println("Name : " + cursor2.getString( 0));
            System.out.println("User name : " + cursor2.getString(1));
            System.out.println("E-mail : " + cursor2.getString(2));
            System.out.println("Password : " + cursor2.getString( 3));
            System.out.println("Birthday : " + cursor2.getString(4));
            System.out.println("Phone Number : " + cursor2.getString(5));

        }
        System.out.println("-----------------------------------------------------");
        System.out.println("-----------------------------------------------------");
        System.out.println("-----------------------------------------------------");
        System.out.println("-----------------------------------------------------");
        cursor2.close();
    }

    public void selectImage(View view) {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Snackbar.make(view,"Permission needed for gallery", Snackbar.LENGTH_INDEFINITE).setAction("Give Permission", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
                    }
                }).show();
            } else {
                permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        } else {
            Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            activityResultLauncher.launch(intentToGallery);
            isPhotoSelected = true;
        }

    }

    public void registerLauncher() {
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent intentFromResult = result.getData();
                            if (intentFromResult != null) {
                                Uri imageData = intentFromResult.getData();
                                try {

                                    if (Build.VERSION.SDK_INT >= 28) {
                                        ImageDecoder.Source source = ImageDecoder.createSource(ProfileActivity.this.getContentResolver(),imageData);
                                        selectedImage = ImageDecoder.decodeBitmap(source);
                                        isPhotoSelected = true;
                                        binding.profileImage.setImageBitmap(selectedImage);

                                    } else {
                                        selectedImage = MediaStore.Images.Media.getBitmap(ProfileActivity.this.getContentResolver(),imageData);
                                        isPhotoSelected = true;
                                        binding.profileImage.setImageBitmap(selectedImage);
                                    }


                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                    }
                });


        permissionLauncher =
                registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
                    @Override
                    public void onActivityResult(Boolean result) {
                        if(result) {
                            //permission granted
                            Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            activityResultLauncher.launch(intentToGallery);

                        } else {
                            //permission denied
                            Toast.makeText(ProfileActivity.this,"Permisson needed!",Toast.LENGTH_LONG).show();
                        }
                    }

                });

    }




    public void toHome(View view){
        Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
        startActivity(intent);
    }




}