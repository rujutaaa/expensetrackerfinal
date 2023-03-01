package com.firstapp.logsign;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.Calendar;
import java.util.Locale;

public class Expense extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView textInputEditTextamount, textInputEditTextdate, textInputEditTextnote;
    Spinner spinnerexp;
    Button saveexpense;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        textInputEditTextamount= findViewById(R.id.amount);
        textInputEditTextdate=findViewById(R.id.date);
        textInputEditTextnote=findViewById(R.id.note);
        saveexpense=findViewById(R.id.add_expense);
        spinnerexp=findViewById(R.id.expense_type);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.expense_name, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerexp.setAdapter(adapter);
        spinnerexp.setOnItemSelectedListener(this);

        textInputEditTextdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Expense.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        textInputEditTextdate.setText(year + "/" + (month + 1) + "/" + day);

                    }
                }, year, month, day);
                datePickerDialog.show();


            }});

        saveexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amount, date, note, expense_type;
                amount = String.valueOf(textInputEditTextamount.getText());
                date = String.valueOf(textInputEditTextdate.getText());
                note = String.valueOf(textInputEditTextnote.getText());
                expense_type = spinnerexp.getSelectedItem().toString();

                if (TextUtils.isEmpty(textInputEditTextamount.getText().toString())){
                    textInputEditTextamount.setError("Adding amount is necessary");
                }
                if (TextUtils.isEmpty(textInputEditTextdate.getText().toString())){
                    textInputEditTextdate.setError("Add date of the expense");
                }
                if (TextUtils.isEmpty(textInputEditTextnote.getText().toString())){
                    textInputEditTextnote.setError("note should be of 300 words only");
                }

                if (!amount.equals("") && !date.equals("") && !note.equals("")) {


//                    progress.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[5];
                            field[0] = "amount";
                            field[1] = "date";
                            field[2] = "note";
                            field[3] = "expense_type";
                            field[4]="user_id";

                            //Creating array for data
                            String[] data = new String[5];
                            data[0] = amount;
                            data[1] = date;
                            data[2] = note;
                            data[3] = expense_type;
                            data[4]= getIntent().getStringExtra("user_id");

                            PutData putData = new PutData("https://trackexpense123.000webhostapp.com/expense.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.equals("Expense Added Successfully")){
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Expense.this, ExpenselogPage.class);
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        
    }
    int counter=0;
    @Override
    public void onBackPressed() {
        counter++;
        if(counter==1){
            Intent intent=new Intent(getApplicationContext(),ExpenselogPage.class);
            startActivity(intent);
        }
        else{
            super.onBackPressed();
        }
    }
}
