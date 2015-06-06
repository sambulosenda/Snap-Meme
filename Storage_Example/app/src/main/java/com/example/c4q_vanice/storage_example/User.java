package com.example.c4q_vanice.storage_example;

/**
 * Created by c4q-vanice on 6/6/15.
 */
public class User {
    private String username;
    private int userage;

    public void setUsername(String name) {
        username = name;
    }
    public String getUsername(){
        return username;
    }

    public void setUserage(int age){
        userage = age;
    }

    public int getUserage() {
        return userage;
    }

    @Override

}
