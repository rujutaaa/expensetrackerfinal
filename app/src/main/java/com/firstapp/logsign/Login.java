package com.firstapp.logsign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    TextView textInputEditTextUsername, textInputEditTextPassword;
    LinearLayout linearLayout;
    Button buttonLogin;
    ImageButton backbtn;
    JSONObject reader = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textInputEditTextUsername= findViewById(R.id.username);
        textInputEditTextPassword=findViewById(R.id.password);
        buttonLogin=findViewById(R.id.button);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username, password;
                username = String.valueOf(textInputEditTextUsername.getText());
                password = String.valueOf(textInputEditTextPassword.getText());

//                backbtn=findViewById(R.id.backbtn);
//                backbtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        onBackPressed();
//                    }
//                });

                if (TextUtils.isEmpty(textInputEditTextUsername.getText().toString())){
                    textInputEditTextUsername.setError("username is required");
                }
                if (TextUtils.isEmpty(textInputEditTextPassword.getText().toString())){
                    textInputEditTextPassword.setError("password is compulsory");
                }

                if (!username.equals("") && !password.equals("")) {


//                    progress.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[2];
                            field[0] = "username";
                            field[1] = "password";

                            //Creating array for data
                            String[] data = new String[2];
                            data[0] = username;
                            data[1] = password;

                            PutData putData = new PutData("https://trackexpense123.000webhostapp.com/login.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    Log.d("data_login","data= "+result);

                                    try {
                                        reader = new JSONObject(result);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    try {
                                        if(reader.getString("status").equals("Login Success")){

                                            Bundle bundle = new Bundle();
                                            String myMessage = reader.getString("user_id");
                                            bundle.putString("user_id",myMessage);

                                            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                            Intent intent= new Intent(getApplicationContext(),HomePage.class);
                                            intent.putExtras(bundle);
                                            startActivity(intent);
                                            finish();
                                        }
                                        else{
                                            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Error for Login from here : ");
                                        e.printStackTrace();
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

    public  String getUserId(){
        try {
            if (reader.getString("status").equals("Login Success")) {

                return reader.getString("user_id");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
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