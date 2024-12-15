package com.example.realtime_database;

public class User {

    public String name;
    public String email;
    public String password;
    public String number;

    public User(){

    }

    public User(String name, String email, String password, String number){
        this.name = name;
        this.password = password;
        this.email = email;
        this.number = number;
    }
    public String getName(){
        return name;
        }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    public String getNumber(){
        return number;
    }
    }



