package com.example.splash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Lord Daniel on 7/27/2016.
 */

public class Database extends SQLiteOpenHelper {

    private static final String DB_Name = "food";
    private static final int DB_Version = 1;

    public Database(Context context) {
        super(context, DB_Name, null, DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE FOOD ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "NAME TEXT,"
                + "YEAR INTEGER,"
                + "MONTH INTEGER,"
                + "DAY INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + DB_Name);

        // Create tables again
        onCreate(db);
    }

    public static void insert(SQLiteDatabase db, String name, int year, int month, int date) {
        ContentValues foodValues = new ContentValues();
        foodValues.put("NAME", name);
        foodValues.put("YEAR", year);
        foodValues.put("MONTH", month);
        foodValues.put("DAY", date);
        db.insert("FOOD", null, foodValues);
    }

    public void add(String name, int year, int month, int date) {
        SQLiteDatabase db = this.getWritableDatabase();
        insert(db, name, year, month, date);
    }

    public List<FoodItem> getAllData() {
        List<FoodItem> foodList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + DB_Name;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Date date = new Date();
                date.setYear(Integer.parseInt(cursor.getString(2)));
                date.setMonth(Integer.parseInt(cursor.getString(3)));
                date.setDate(Integer.parseInt(cursor.getString(4)));
                FoodItem foodItem = new FoodItem(cursor.getString(1), Integer.parseInt(cursor.getString(0)), date);
                // Adding foodItem to list
                foodList.add(foodItem);
            } while (cursor.moveToNext());
        }

        // return contact list
        return foodList;
    }

    public void delete(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_Name, "id = ?", new String[] { String.valueOf(id) });
        db.close();
    }

}