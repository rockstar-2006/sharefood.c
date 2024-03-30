package com.example.splash;


import java.util.Comparator;

/**
 * Created by Lord Daniel on 7/27/2016.
 */
public class DateComparator implements Comparator<FoodItem> {
    public int compare(FoodItem f1, FoodItem f2){
        return f1.getDate().compareTo(f2.getDate());
    }
}