package com.example.splash;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddElementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_element);
    }

    public void add(View view){
        EditText txt = (EditText)findViewById(R.id.editText);
        String name = txt.getText().toString();

        DatePicker datePicker = (DatePicker)findViewById(R.id.datePicker);

        Database db = new Database(this);
        int year = datePicker.getYear();
        int month = datePicker.getMonth();
        int date = datePicker.getDayOfMonth();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@2  "+year);
        db.add(name, year, month, date);

        //go back to main page
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}