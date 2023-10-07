package com.example.ca3.Entity;

import androidx.annotation.Nullable;

public class User {
    private String name;
    private String password;
    private String email;
    private String phoneNum;

    public User() {
        this.name="";
        this.password ="";
        this.email="";
        this.phoneNum ="";
    }

    public User(String name, String password, String email, String phoneNum) {
        this.name=name;
        this.password = password;
        this.email=email;
        this.phoneNum = phoneNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }
}
