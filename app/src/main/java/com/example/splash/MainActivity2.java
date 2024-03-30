package com.example.splash;

import android.content.Intent;
import android.graphics.Color;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity2 extends AppCompatActivity {

    private ArrayList<FoodItem> foodItems;
    private ArrayList<FoodItem> current;
    private int sort;
    private int time = 0;
    private int textSize = 20;
    private int calculateExpiryHealth(Date expiryDate) {
        // Your logic to calculate expiry health
        // Return a value between 0 and 100 based on the expiry status
        // For example, return 0 for expired, 50 for normal, 100 for fresh

        // For simplicity, let's assume a basic calculation (you can replace this with your logic)
        long daysBetween = daysBetween(expiryDate, new Date());
        if (daysBetween < 0) {
            // Item is expired
            return 0;
        } else if (daysBetween == 0) {
            // Item expires today
            return 50;
        } else {
            // Item is fresh
            // You can customize this part based on your actual logic
            return 100;
        }
    }

    public class DateUtil {
        public int daysBetween(LocalDate d1, LocalDate d2) {

            return (int) ChronoUnit.DAYS.between(d1, d2);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        this.time = 0;

        current = new ArrayList<>();
        foodItems = new ArrayList<>();
        sort = 0;//alphabetical

        //access the database and store everything in an ArrayList
        Database db = new Database(MainActivity2.this);

        foodItems = new ArrayList<>(db.getAllData());


        //iterate through foodItems and store items expiring today in current
        Calendar todayDate = Calendar.getInstance();
        todayDate.clear(Calendar.HOUR); todayDate.clear(Calendar.MINUTE); todayDate.clear(Calendar.SECOND);
        Date today = todayDate.getTime();
        for(FoodItem f:foodItems){
            if(daysBetween(f.getDate(), today)==0){
                current.add(f);
            }
        }

        //sort current alphabetically
        Collections.sort(current, new AlphaComparator());

        //iterate through current and display
        //clear list
        LinearLayout items = (LinearLayout)findViewById(R.id.item);
        items.removeAllViews();

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        param.setMargins(30, 30, 30, 30);
        //iterate through current and create an item for every element and ad it to items
        for(FoodItem f:current){
            items.addView(createFoodItem(f.getName(), f.getDate(), f.getId(), 0),param);
        }

    }

    public void addElement(View view){
        Intent intent = new Intent(this, AddElementActivity.class);
        startActivity(intent);
    }

    public void viewExpired(View view){
        this.time = -1;
        //change border color
        LinearLayout border = (LinearLayout)findViewById(R.id.border);
        border.setBackgroundColor(Color.RED);

        //clear list
        LinearLayout items = (LinearLayout)findViewById(R.id.item);
        items.removeAllViews();

        //change the value of current
        current = new ArrayList<>();
        Calendar todayDate = Calendar.getInstance();
        todayDate.clear(Calendar.HOUR); todayDate.clear(Calendar.MINUTE); todayDate.clear(Calendar.SECOND);
        Date today = todayDate.getTime();
        for(FoodItem f:foodItems){
            if(daysBetween(f.getDate(), today)<0){
                current.add(f);
            }
        }
        //sort
        if(sort==0){
            Collections.sort(current,new AlphaComparator());
        }
        else{
            Collections.sort(current, new DateComparator());
        }

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        param.setMargins(30, 30, 30, 30);
        //iterate through current and create an item for every element and ad it to items
        for(FoodItem f:current){
            items.addView(createFoodItem(f.getName(), f.getDate(), f.getId(), -1),param);
        }

        //just for testing
//        LinearLayout Lx = (LinearLayout)findViewById(R.id.testLinearLayout);
//        Lx.setBackgroundColor(Color.parseColor("#ff0000"));
//        Button Tx = (Button)findViewById(R.id.testButton);
//        Tx.setTextColor(Color.parseColor("#ff0000"));
    }

    public void viewToday(View view){
        this.time = 0;

        //change border color
        LinearLayout border = (LinearLayout)findViewById(R.id.border);
        border.setBackgroundColor(Color.parseColor("#ff6103"));

        //clear list
        LinearLayout items = (LinearLayout)findViewById(R.id.item);
        items.removeAllViews();

        //change the value of current
        current = new ArrayList<>();
        Calendar todayDate = Calendar.getInstance();
        todayDate.clear(Calendar.HOUR); todayDate.clear(Calendar.MINUTE); todayDate.clear(Calendar.SECOND);
        Date today = todayDate.getTime();
        for(FoodItem f:foodItems){
            if(daysBetween(f.getDate(), today)==0){
                current.add(f);
            }
        }
        //sort
        if(sort==0){
            Collections.sort(current,new AlphaComparator());
        }
        else{
            Collections.sort(current, new DateComparator());
        }

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        param.setMargins(30, 30, 30, 30);
        //iterate through current and create an item for every element and ad it to items
        for(FoodItem f:current){
            items.addView(createFoodItem(f.getName(), f.getDate(), f.getId(), 0),param);
        }

        //just for testing
//        LinearLayout Lx = (LinearLayout)findViewById(R.id.testLinearLayout);
//        Lx.setBackgroundColor(Color.parseColor("#ff6103"));
//        Button Tx = (Button)findViewById(R.id.testButton);
//        Tx.setTextColor(Color.parseColor("#ff6103"));
    }

    public void viewTmrw(View view){
        this.time = 1;

        //change border color
        LinearLayout border = (LinearLayout)findViewById(R.id.border);
        border.setBackgroundColor(Color.parseColor("#1e90ff"));

        //clear list
        LinearLayout items = (LinearLayout)findViewById(R.id.item);
        items.removeAllViews();

        //change the value of current
        current = new ArrayList<>();
        Calendar todayDate = Calendar.getInstance();
        todayDate.clear(Calendar.HOUR); todayDate.clear(Calendar.MINUTE); todayDate.clear(Calendar.SECOND);
        Date today = todayDate.getTime();
        for(FoodItem f:foodItems){
            if(daysBetween(f.getDate(), today)==1){
                current.add(f);
            }
        }
        //sort
        if(sort==0){
            Collections.sort(current,new AlphaComparator());
        }
        else{
            Collections.sort(current, new DateComparator());
        }

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        param.setMargins(30, 30, 30, 30);
        //iterate through current and create an item for every element and ad it to items
        for(FoodItem f:current){
            items.addView(createFoodItem(f.getName(), f.getDate(), f.getId(), 1),param);
        }

        //just for testing
//        LinearLayout Lx = (LinearLayout)findViewById(R.id.testLinearLayout);
//        Lx.setBackgroundColor(Color.parseColor("#1e90ff"));
//        Button Tx = (Button)findViewById(R.id.testButton);
//        Tx.setTextColor(Color.parseColor("#1e90ff"));
    }

    public void viewFuture(View view){
        this.time = 2;

        //change border color
        LinearLayout border = (LinearLayout)findViewById(R.id.border);
        border.setBackgroundColor(Color.GREEN);

        //clear list
        LinearLayout items = (LinearLayout)findViewById(R.id.item);
        items.removeAllViews();

        //change the value of current
        current = new ArrayList<>();
        Calendar todayDate = Calendar.getInstance();
        todayDate.clear(Calendar.HOUR); todayDate.clear(Calendar.MINUTE); todayDate.clear(Calendar.SECOND);
        Date today = todayDate.getTime();
        for(FoodItem f:foodItems){
            if(daysBetween(f.getDate(), today)>1){
                current.add(f);
            }
        }
        //sort
        if(sort==0){
            Collections.sort(current,new AlphaComparator());
        }
        else{
            Collections.sort(current, new DateComparator());
        }

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        param.setMargins(30, 30, 30, 30);
        //iterate through current and create an item for every element and ad it to items
        for(FoodItem f:current){
            items.addView(createFoodItem(f.getName(), f.getDate(), f.getId(), 2),param);
        }

        //just for testing
//        LinearLayout Lx = (LinearLayout)findViewById(R.id.testLinearLayout);
//        Lx.setBackgroundColor(Color.parseColor("#00ff00"));
//        Button Tx = (Button)findViewById(R.id.testButton);
//        Tx.setTextColor(Color.parseColor("#00ff00"));
    }

    public void sortAlpha(View view){
        Button alpha = (Button)findViewById(R.id.alpha);
        alpha.setBackgroundColor(Color.parseColor("#303030"));

        Button date = (Button)findViewById(R.id.date);
        date.setBackgroundColor(Color.parseColor("#000000"));

        Collections.sort(current, new AlphaComparator());
        //clear list
        LinearLayout items = (LinearLayout)findViewById(R.id.item);
        items.removeAllViews();
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        param.setMargins(30, 30, 30, 30);
        //iterate through current and create an item for every element and ad it to items
        for(FoodItem f:current){
            items.addView(createFoodItem(f.getName(), f.getDate(), f.getId(), time),param);
        }
        sort = 0;
    }

    public void sortDate(View view){
        Button alpha = (Button)findViewById(R.id.alpha);
        alpha.setBackgroundColor(Color.parseColor("#000000"));

        Button date = (Button)findViewById(R.id.date);
        date.setBackgroundColor(Color.parseColor("#303030"));

        Collections.sort(current, new DateComparator());
        //clear list
        LinearLayout items = (LinearLayout)findViewById(R.id.item);
        items.removeAllViews();
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        param.setMargins(30, 30, 30, 30);
        //iterate through current and create an item for every element and ad it to items
        for(FoodItem f:current){
            items.addView(createFoodItem(f.getName(), f.getDate(), f.getId(), time),param);
        }
        sort = 1;
    }

    /**
     * statuses:
     *          -1 : expired
     *          0  : expires today
     *          1  : expires tomorrow
     *          2  : expires later
     * @param name
     * @param date
     * @param status
     * @return
     */
    public LinearLayout createFoodItem(String name, Date date, int id, int status){
        LinearLayout L  = new LinearLayout(this);
        L.setOrientation(LinearLayout.VERTICAL);
        L.setPadding(15,5,5,5);
        if(status == -1) {
            L.setBackgroundColor(Color.parseColor("#ff0000"));
        }
        else if(status == 0){
            L.setBackgroundColor(Color.parseColor("#ff6103"));
        }
        else if(status == 1){
            L.setBackgroundColor(Color.parseColor("#1e90ff"));
        }
        else { //status = 2
            L.setBackgroundColor(Color.parseColor("#00ff00"));
        }
        SeekBar seekBar = new SeekBar(this);
        seekBar.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        seekBar.setMax(100);
        seekBar.setProgress(calculateExpiryHealth(date));

        // ... Existing code ...

        L.addView(seekBar);



        TextView textName = new TextView(this);
        textName.setText(name);
        textName.setTextColor(Color.parseColor("#ffffff"));
        textName.setTextSize(textSize);
        TextView textDate = new TextView(this);
        String monthname=(String)android.text.format.DateFormat.format("MMMM", date);
        textDate.setText("Expiry date: "+monthname+" "+date.getDate()+", "+date.getYear());
        textDate.setTextColor(Color.parseColor("#ffffff"));
        textDate.setTextSize(textSize);
        TextView textId = new TextView(this);
        textId.setText("ID: " + Integer.toString(id));
        textId.setTextColor(Color.parseColor("#ffffff"));
        textId.setTextSize(textSize);

        LinearLayout L2 = new LinearLayout(this);
        L2.setOrientation(LinearLayout.HORIZONTAL);
        L2.setPadding(10, 10, 10, 10);
        TextView daysLeft = new TextView(this);
        Calendar todayDate = Calendar.getInstance();
        todayDate.clear(Calendar.HOUR); todayDate.clear(Calendar.MINUTE); todayDate.clear(Calendar.SECOND);
        Date today = todayDate.getTime();
        if(this.time == -1) {
            daysLeft.setText("Expired "+Integer.toString((-1)*daysBetween(date, today)) + " d ago.");
        }
        else if(this.time == 0) {
            daysLeft.setText("Expires today.");
        }
        else if(this.time == 1) {
            daysLeft.setText("Expires tomorrow.");
        }
        else { //this.time = 2
            daysLeft.setText(Integer.toString(daysBetween(date, today)) + " d left.");
        }
        daysLeft.setTextColor(Color.parseColor("#000000"));
        daysLeft.setTextSize(textSize);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
        L2.addView(daysLeft,param);

        Button delButton = new Button(this);
        delButton.setBackgroundColor(Color.parseColor("#000000"));
        delButton.setText("Delete");
        if (status == -1) {
            delButton.setTextColor(Color.parseColor("#ff0000"));
        }
        else if(status == 0){
            delButton.setTextColor(Color.parseColor("#ff6103"));
        }
        else if(status == 1){
            delButton.setTextColor(Color.parseColor("#1e90ff"));
        }
        else { //status = 2
            delButton.setTextColor(Color.parseColor("#00ff00"));
        }
        //delButton.setGravity(Gravity.RIGHT);
        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(v);
            }
        });
        L2.addView(delButton);

        L.addView(textName);
        L.addView(textDate);
        L.addView(textId);
        L.addView(L2);

        return L;
    }

    public void delete(View view){
        //remove the item from the display
        LinearLayout L = (LinearLayout)view.getParent().getParent();
        LinearLayout items = (LinearLayout)findViewById(R.id.item);
        items.removeView(L);

        //remove the item from the database
        Database db = new Database(this);
        int id = Integer.parseInt(((TextView) L.getChildAt(2)).getText().toString().split(" ")[1]);
        db.delete(id);

        FoodItem foodItem = new FoodItem();
        for(FoodItem f:foodItems){
            if(f.getId() == id){
                foodItem = f;
                break;
            }
        }
        foodItems.remove(foodItem);
    }

    public int daysBetween(Date d1, Date d2) {
        //d2 is today
        d2.setYear(2024);
        //I NEED TO UPDATE THE LINE ABOVE NEXT YEAR
        d2.setHours(0);
        d2.setMinutes(1);
        d1.setHours(0);
        d1.setMinutes(1);
        System.out.println(d1.toString()+"           "+(d1.getTime() - d2.getTime()));
        System.out.println("DAYS: "+(( (float)(d1.getTime() - d2.getTime()) / (1000 * 60 * 60 * 24))));
        return (int) Math.round(((float) (d1.getTime() - d2.getTime()) / (1000 * 60 * 60 * 24 ))) ;
    }
}

/*
XML CODE FOR A FOOD ITEM DISPLAY LAYOUT

<LinearLayout
                    android:orientation="vertical"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:layout_gravity="center_horizontal"
                    android:layout_margin="10dp"
                    android:background="#ff6103"
                    android:padding="5dp"
                    android:id="@+id/testLinearLayout">

                    <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:textAppearance="?android:attr/textAppearanceLarge"
                        android:text="Name"
                        android:id="@+id/textView3"
                        android:textColor="#ffffff" />

                    <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:textAppearance="?android:attr/textAppearanceLarge"
                        android:text="Expiry Date"
                        android:id="@+id/textView4"
                        android:textColor="#ffffff" />

                    <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:textAppearance="?android:attr/textAppearanceLarge"
                        android:text="ID#"
                        android:id="@+id/textView5"
                        android:textColor="#ffffff" />

                    <LinearLayout
                        android:orientation="horizontal"
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:layout_gravity="center_horizontal">

                        <TextView
                            android:layout_width="wrap_content"
                            android:layout_height="wrap_content"
                            android:textAppearance="?android:attr/textAppearanceLarge"
                            android:text="Days Left"
                            android:id="@+id/textView6"
                            android:layout_margin="10dp"
                            android:layout_weight="1" />

                        <Button
                            android:layout_width="wrap_content"
                            android:layout_height="wrap_content"
                            android:text="Delete"
                            android:id="@+id/testButton"
                            android:layout_margin="5dp"
                            android:textColor="#ff6103"
                            android:background="#000000"
                            android:layout_gravity="right" />
                    </LinearLayout>

                </LinearLayout>
 */