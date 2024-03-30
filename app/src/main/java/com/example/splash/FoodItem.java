package com.example.splash;

import java.util.Date;

/**
 * Created by Lord Daniel on 7/27/2016.
 */
public class FoodItem {
    private String name;
    private int id;
    private Date date;

    public FoodItem(){
        //empty constructor
    }

    public FoodItem(String name, int id, Date date){
        this.name = name;
        this.id = id;
        this.date = date;
    }

    public String getName(){
        return this.name;
    }

    public int getId(){
        return this.id;
    }

    public Date getDate(){
        return this.date;
    }
}