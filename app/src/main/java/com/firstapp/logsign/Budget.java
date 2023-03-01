package com.firstapp.logsign;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.Calendar;
import java.util.Locale;

public class Budget extends AppCompatActivity {

    TextView textInputEditTextMonth, textInputEditTextYear, textInputEditTextBudgetLimit;
    Button setBudget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        textInputEditTextMonth=findViewById(R.id.budgetMonth);
        textInputEditTextYear=findViewById(R.id.budgetYear);
        textInputEditTextBudgetLimit=findViewById(R.id.budgetLimit);
        setBudget=findViewById(R.id.add_budget);

        setBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String month, year, budgetLimit;
                month = String.valueOf(textInputEditTextMonth.getText());
                year = String.valueOf(textInputEditTextYear.getText());
                budgetLimit = String.valueOf(textInputEditTextBudgetLimit.getText());

                if (!month.equals("") && !year.equals("") && !budgetLimit.equals("")) {

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[3];
                            field[0] = "month";
                            field[1] = "year";
                            field[2] = "budgetLimit";

                            //Creating array for data
                            String[] data = new String[3];
                            data[0] = month;
                            data[1] = year;
                            data[2] = budgetLimit;

                            PutData putData = new PutData("https://trackexpense123.000webhostapp.com/budget.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.equals("Budget Added Successfully")){
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        //Intent intent= new Intent(getApplicationContext(),reminder.class);
                                        Intent intent = new Intent(Budget.this, HomePage.class);
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

    public void setTxtdummy(View view) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(Budget.this);
        View mView = getLayoutInflater().inflate(R.layout.customdialog,null);

        //months
        Button bt1_jan = (Button) mView.findViewById(R.id.bt1jan);
        Button bt2_feb = (Button) mView.findViewById(R.id.bt2feb);
        Button bt3_mar = (Button) mView.findViewById(R.id.bt3mar);
        Button bt4_apr = (Button) mView.findViewById(R.id.bt4apr);
        Button bt5_may = (Button) mView.findViewById(R.id.bt5may);
        Button bt6_jun = (Button) mView.findViewById(R.id.bt6jun);
        Button bt7_jul = (Button) mView.findViewById(R.id.bt7jul);
        Button bt8_aug = (Button) mView.findViewById(R.id.bt8aug);
        Button bt9_sep = (Button) mView.findViewById(R.id.bt9sep);
        Button bt10_oct = (Button) mView.findViewById(R.id.bt10oct);
        Button bt11_nov = (Button) mView.findViewById(R.id.bt11nov);
        Button bt12_dec = (Button) mView.findViewById(R.id.bt12dec);

        Button btn1 =(Button)mView.findViewById(R.id.btn1);

        alert.setView(mView);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        bt1_jan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textInputEditTextMonth.setText(bt1_jan.getText().toString());
            }
        });

        bt2_feb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textInputEditTextMonth.setText(bt2_feb.getText().toString());
            }
        });

        bt3_mar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textInputEditTextMonth.setText(bt3_mar.getText().toString());
            }
        });

        bt4_apr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textInputEditTextMonth.setText(bt4_apr.getText().toString());
            }
        });

        bt5_may.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textInputEditTextMonth.setText(bt5_may.getText().toString());
            }
        });

        bt6_jun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textInputEditTextMonth.setText(bt6_jun.getText().toString());
            }
        });

        bt7_jul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textInputEditTextMonth.setText(bt7_jul.getText().toString());
            }
        });

        bt8_aug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textInputEditTextMonth.setText(bt8_aug.getText().toString());
            }
        });

        bt9_sep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textInputEditTextMonth.setText(bt9_sep.getText().toString());
            }
        });

        bt10_oct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textInputEditTextMonth.setText(bt10_oct.getText().toString());
            }
        });

        bt11_nov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textInputEditTextMonth.setText(bt11_nov.getText().toString());
            }
        });

        bt12_dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textInputEditTextMonth.setText(bt12_dec.getText().toString());
            }
        });
        alertDialog.show();
    }

    //backbtn
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