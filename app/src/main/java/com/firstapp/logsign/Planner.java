package com.firstapp.logsign;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.Calendar;

public class Planner extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView textInputEditTextplndate, textInputEditTextplnamount;;
    Button plnbutonn;
    Spinner spinnerpln;
   private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planner);

        textInputEditTextplndate= findViewById(R.id.plannerDate);
        textInputEditTextplnamount=findViewById(R.id.plannerAmount);
        plnbutonn=findViewById(R.id.add_planner);
        spinnerpln=findViewById(R.id.expense_type);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.expense_name, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerpln.setAdapter(adapter);
        spinnerpln.setOnItemSelectedListener(this);

        textInputEditTextplndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Planner.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        textInputEditTextplndate.setText(year + "/" + (month + 1) + "/" + day);

                    }
                }, year, month, day);
                datePickerDialog.show();


            }});

        plnbutonn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date, amount;
                date = String.valueOf(textInputEditTextplndate.getText());
                amount = String.valueOf(textInputEditTextplnamount.getText());

                if (!date.equals("") && !amount.equals("")) {

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[2];
                            field[0] = "date";
                            field[1] = "amount";

                            //Creating array for data
                            String[] data = new String[2];
                            data[0] = date;
                            data[1] = amount;

                            PutData putData = new PutData("https://trackexpense123.000webhostapp.com/planner.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.equals("Planner Added Successfully")){
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Planner.this, HomePage.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(),"All fields required", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}