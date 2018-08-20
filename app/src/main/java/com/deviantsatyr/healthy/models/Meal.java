package com.deviantsatyr.healthy.models;

public class Meal {
    public String name;
    public String calories;
    public String barcode;

    public Meal(){
        // Empty needed for firebase
    }
    public Meal(String name, String calories){
        this.name=name;
        this.calories=calories;
    }
}
