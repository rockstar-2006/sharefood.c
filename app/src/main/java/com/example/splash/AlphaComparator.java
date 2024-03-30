package com.example.splash;

import java.util.Comparator;

/**
 * Created by Lord Daniel on 7/27/2016.
 */
public class AlphaComparator implements Comparator<FoodItem> {
    public int compare(FoodItem f1, FoodItem f2){
        return f1.getName().compareTo(f2.getName());
    }
}
