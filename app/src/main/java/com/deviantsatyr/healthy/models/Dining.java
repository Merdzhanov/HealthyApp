package com.deviantsatyr.healthy.models;

import com.google.firebase.auth.FirebaseAuth;

import java.sql.Date;
import java.sql.Timestamp;

public class Dining {
    public String user;
    public String date;
    public String meal_name;

    public Dining(){
        //Empty needed for firebase
    }
    public Dining(String date, String meal_name){
       this.user= FirebaseAuth.getInstance().getUid();
       this.date=date;
       this.meal_name=meal_name;
    }
}
