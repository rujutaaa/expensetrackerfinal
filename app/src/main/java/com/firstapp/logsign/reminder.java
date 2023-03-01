package com.firstapp.logsign;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.Build;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.text.BreakIterator;
import java.util.Calendar;
import java.util.Locale;

public class reminder extends AppCompatActivity {

    TextView textInputEditTextAmount, textInputEditTextDate, textInputEditTextTime ;
    Button setreminder;
    private int hour, minute;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        textInputEditTextAmount= findViewById(R.id.amount);
        textInputEditTextDate=findViewById(R.id.date);
        textInputEditTextTime=findViewById(R.id.time);
        setreminder=findViewById(R.id.add_reminder);

        textInputEditTextTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker();
            }
        });

        textInputEditTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(reminder.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        textInputEditTextDate.setText(year + "/" + (month + 1) + "/" + day);
                    }
                    }, year, month, day);
                datePickerDialog.show();
            }});

        setreminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //notification
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    String description = "This is description";
//                    int importance = NotificationManager.IMPORTANCE_DEFAULT;
//                    NotificationChannel notificationChannel = new NotificationChannel("My Notification", "Simple Notification", importance);
//                    notificationChannel.setDescription(description);
//                    NotificationManager
//                            notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//                    notificationManager.createNotificationChannel(notificationChannel);
//                }
//                NotificationCompat.Builder builder = new NotificationCompat.Builder(reminder.this, "My Notification");
//                builder.setContentTitle("Reminder Set");
//                builder.setSmallIcon(R.drawable.ic_launcher_background);
//                builder.setAutoCancel(true);
//                builder.setContentText("check application for details");
//                NotificationManagerCompat manager = NotificationManagerCompat.from(getApplicationContext());
//                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_NOTIFICATION_POLICY) != PackageManager.PERMISSION_GRANTED) {
//                    // TODO: Consider calling
//                    //    ActivityCompat#requestPermissions
//                    // here to request the missing permissions, and then overriding
//                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                    //                                          int[] grantResults)
//                    // to handle the case where the user grants the permission. See the documentation
//                    // for ActivityCompat#requestPermissions for more details.
//                    return;
//                }
//                manager.notify(999, builder.build());

                //main
                String amount, date, time;
                amount = String.valueOf(textInputEditTextAmount.getText());
                date = String.valueOf(textInputEditTextDate.getText());
                time = String.valueOf(textInputEditTextTime.getText());

                if (!amount.equals("") && !date.equals("") && !time.equals("")) {

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[3];
                            field[0] = "amount";
                            field[1] = "date";
                            field[2] = "time";

                            //Creating array for data
                            String[] data = new String[3];
                            data[0] = amount;
                            data[1] = date;
                            data[2] = time;

                            PutData putData = new PutData("https://trackexpense123.000webhostapp.com/reminder.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.equals("Reminder Added Successfully")){
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                       // Intent intent= new Intent(getApplicationContext(),reminder.class);
                                        Intent intent = new Intent(getApplicationContext(),HomePage.class);
                                        startActivity(intent);
                                        finish();
                                      //  reminder.super.onBackPressed();
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                    }

//                                    progress.setVisibility(View.GONE);
                                    //  Log.i("PutData", result);
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(),"All fields required", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //TIME
    private void showTimePicker() {
        Calendar mCurrentTime = Calendar.getInstance();
        hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
        minute = mCurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(reminder.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                textInputEditTextTime.setText(String.format(Locale.getDefault(), "%d:%d", selectedHour, selectedMinute));
            }
        }, hour, minute, false);
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    //Backbutton
    int counter=0;
    @Override
    public void onBackPressed() {
        counter++;
        if(counter==1){
            Intent intent=new Intent(getApplicationContext(),HomePage.class);
            startActivity(intent);
        }
        else{
            super.onBackPressed();
        }
    }
}
