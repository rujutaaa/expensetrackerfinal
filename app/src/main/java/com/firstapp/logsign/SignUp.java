package com.firstapp.logsign;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.vishnusivadas.advanced_httpurlconnection.PutData;
import com.google.android.material.textfield.TextInputEditText;


public class SignUp extends AppCompatActivity {
    TextView textInputEditTextFullname, textInputEditTextUsername, textInputEditTextEmail, textInputEditTextPassword;
    Button buttonSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        textInputEditTextFullname= findViewById(R.id.fullname);
        textInputEditTextUsername= findViewById(R.id.username);
        textInputEditTextEmail= findViewById(R.id.email);
        textInputEditTextPassword=findViewById(R.id.password);
        buttonSignup=findViewById(R.id.button2);

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname, username, password, email;
                fullname = String.valueOf(textInputEditTextFullname.getText());
                username = String.valueOf(textInputEditTextUsername.getText());
                password = String.valueOf(textInputEditTextPassword.getText());
                email = String.valueOf(textInputEditTextEmail.getText());

                if (TextUtils.isEmpty(textInputEditTextFullname.getText().toString())){
                    textInputEditTextFullname.setError("fullname is compulsory");
                }
                if (TextUtils.isEmpty(textInputEditTextUsername.getText().toString())){
                    textInputEditTextUsername.setError("username is compulsory");
                }
                if (TextUtils.isEmpty(textInputEditTextEmail.getText().toString())){
                    textInputEditTextEmail.setError("email is compulsory");
                }
                if (TextUtils.isEmpty(textInputEditTextPassword.getText().toString())){
                    textInputEditTextPassword.setError("password is compulsory");
                }

                if (!fullname.equals("") && !username.equals("") && !password.equals("") && !email.equals("")) {

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[4];
                            field[0] = "fullname";
                            field[1] = "username";
                            field[2] = "password";
                            field[3] = "email";

                            //Creating array for data
                            String[] data = new String[4];
                            data[0] = fullname;
                            data[1] = username;
                            data[2] = password;
                            data[3] = email;

                            PutData putData = new PutData("https://trackexpense123.000webhostapp.com/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.equals("Sign Up Success")){
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        Intent intent= new Intent(getApplicationContext(),Login.class);
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
            Intent intent=new Intent(getApplicationContext(),startpage.class);
            startActivity(intent);
        }
        else{
            super.onBackPressed();
        }
    }
}