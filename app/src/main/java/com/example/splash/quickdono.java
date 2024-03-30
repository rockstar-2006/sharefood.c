package com.example.splash;

import android.app.Dialog;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

public class quickdono extends AppCompatActivity {

    private SeekBar seekBar;
    private TextView textView;
    private Button btnSelectTime;
    private CheckBox chkFetchLocation;
    private Button btnStart;
    private ALodingDialog aLodingDialog;

    private LocationManager locationManager;
    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            // Handle location updates if needed
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // Handle status changes if needed
        }

        @Override
        public void onProviderEnabled(String provider) {
            // Handle provider enabled if needed
        }

        @Override
        public void onProviderDisabled(String provider) {
            // Handle provider disabled if needed
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quickdono);

        seekBar = findViewById(R.id.seekBarID);
        textView = findViewById(R.id.textviewID);
        btnSelectTime = findViewById(R.id.btnSelectTime);
        chkFetchLocation = findViewById(R.id.chkFetchLocation);
        btnStart = findViewById(R.id.btnStart);

        aLodingDialog = new ALodingDialog(this);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show loading screen
                aLodingDialog.show();

                // Simulate a delay and dismiss the loading dialog
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Dismiss the loading dialog
                        aLodingDialog.dismiss();

                        // Show the custom dialog
                        showCustomDialog();
                    }
                }, 3000); // Adjust the delay time as needed
            }
        });

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(progress + " Person");
                setMarginForTextView(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        btnSelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog();
            }
        });

        chkFetchLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFetchLocationCheckboxClicked(v);
            }
        });
    }

    private void setMarginForTextView(int progress) {
        int margin = (int) (16 + 1.5 * progress);
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) textView.getLayoutParams();
        layoutParams.setMargins(margin, 0, 0, 0);
        textView.setLayoutParams(layoutParams);
    }

    private void showTimePickerDialog() {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Format the selected time
                        String am_pm = (hourOfDay < 12) ? "AM" : "PM";
                        hourOfDay = (hourOfDay < 12) ? hourOfDay : hourOfDay - 12;
                        String selectedTime = String.format(Locale.getDefault(), "%02d:%02d %s", hourOfDay, minute, am_pm);

                        // Set the selected time in the EditText
                        EditText editTextTime = findViewById(R.id.editTextTime);
                        editTextTime.setText(selectedTime);

                        showToast("Selected Time: " + selectedTime);
                    }

                    private void showToast(String s) {
                        Toast.makeText(quickdono.this, s, Toast.LENGTH_SHORT).show();
                    }
                },
                hour,
                minute,
                false
        );

        timePickerDialog.show();
    }

    public void onFetchLocationCheckboxClicked(View view) {
        boolean isChecked = ((CheckBox) view).isChecked();

        if (isChecked) {
            // Check and request permissions if necessary
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                    checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                // Permission already granted, start fetching location
                startLocationUpdates();

                // Open Google Maps with the current location
                openGoogleMaps();
            }
        } else {
            // Unchecked, stop location updates if needed
            stopLocationUpdates();
        }
    }

    private void startLocationUpdates() {
        // Register the listener with the Location Manager to receive location updates
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            }
        }
    }

    private void stopLocationUpdates() {
        // Unregister the location listener
        locationManager.removeUpdates(locationListener);
    }

    private void openGoogleMaps() {
        // Check if location permission is granted
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            // Create an intent to launch Google Maps with the current location
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="));
            mapIntent.setPackage("com.google.android.apps.maps");

            // Check if there is an app to handle the intent
            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(mapIntent);
            } else {
                Toast.makeText(this, "Google Maps app not installed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showCustomDialog() {
        // Create a ShapeDrawable with rounded corners
        int cornerRadius = 20; // Set your desired corner radius
        float[] corners = new float[]{cornerRadius, cornerRadius, cornerRadius, cornerRadius, 0, 0, 0, 0};
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(corners, null, null));
        shapeDrawable.getPaint().setColor(getResources().getColor(android.R.color.white)); // Set your desired background color

        // Create a Dialog and set its background
        Dialog customDialogWithShape = new Dialog(this);  // Rename the variable
        customDialogWithShape.requestWindowFeature(Window.FEATURE_NO_TITLE);
        customDialogWithShape.setContentView(R.layout.activity_custom_dialog);
        customDialogWithShape.getWindow().setBackgroundDrawable(shapeDrawable);
        customDialogWithShape.show();

        // Simulate a task that takes some time (e.g., 3 seconds)
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Dismiss the custom dialog after the task is completed
                customDialogWithShape.dismiss();
            }
        }, 3500); // Adjust the delay time as needed
    }
    public void onPostButtonClick(View view) {
        // Retrieve values from UI elements
        String foodDetails = ((EditText) findViewById(R.id.editTextFoodDetails)).getText().toString();
        String foodQuantity = ((TextView) findViewById(R.id.textviewID)).getText().toString();
        // Add more variables as needed

        // Create an Intent to start the PostActivity
        Intent intent = new Intent(this, PostActivity.class);

        // Pass data to the intent
        intent.putExtra("FOOD_DETAILS", foodDetails);
        intent.putExtra("FOOD_QUANTITY", foodQuantity);
        // Add more data as needed

        // Start the PostActivity
        startActivity(intent);
    }

    // ... (other methods)
}