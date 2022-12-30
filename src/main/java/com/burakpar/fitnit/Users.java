package com.burakpar.fitnit;

public class Users {

    String full_name ;
    String user_name ;
    String email ;
    String password ;
    String birthday ;
    String phone_number ;
    String bmı ;
    byte[] byteArrayImage;
    Boolean sport;
    Boolean nutrition;


    public Users(String full_name, String user_name, String email, String password, String birthday, String phone_number) {
        this.full_name = full_name;
        this.user_name = user_name;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.phone_number = phone_number;
        this.bmı = "0";
    }

    public Boolean getSport() {
        return sport;
    }

    public void setSport(Boolean sport) {
        this.sport = sport;
    }

    public Boolean getNutrition() {
        return nutrition;
    }

    public void setNutrition(Boolean nutrition) {
        this.nutrition = nutrition;
    }

    public byte[] getByteArrayImage() {
        return byteArrayImage;
    }

    public void setByteArrayImage(byte[] byteArrayImage) {
        this.byteArrayImage = byteArrayImage;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getBmı() {
        return bmı;
    }

    public void setBmı(String bmı) {
        this.bmı = bmı;
    }






}
