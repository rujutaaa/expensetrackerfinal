package com.firstapp.logsign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenselogPage extends AppCompatActivity{

    ImageButton imageButton;
    RecyclerView recyclerView;
    String url = "https://trackexpense123.000webhostapp.com/getexpense.php";
    List<ExpModelClass> imagelist;
    ExpModelClass expModelClass;
    LinearLayoutManager linearLayoutManager;
    Expenseadapter adapter;
    ProgressDialog mProgressDialog;
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenselog_page);

        Intent frmhp=getIntent();
        String userid=frmhp.getStringExtra("user_id");
        Log.d("data_expense","data= "+userid);

        recyclerView = findViewById(R.id.recycle);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        imagelist = new ArrayList<>();

        adapter = new Expenseadapter(this,imagelist);
        recyclerView.setAdapter(adapter);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                mProgressDialog.dismiss();

                imagelist.clear();
                try {
                    Log.i("tagconvertstr", "["+response+"]");

                    JSONObject jsonObject = new JSONObject(response);
                    Log.i("tagconvertstr", "["+response+"]");
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    if (success.equals("1")) {

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);

                            String id=object.getString("id");
                            String amount = object.getString("amount");
                            String note = object.getString("note");
                            String date = object.getString("date");
                            String expense_type = object.getString("expense_type");
                            String user_id = object.getString("user_id");

                            expModelClass = new ExpModelClass(id,amount,date,note,expense_type,user_id);
                            imagelist.add(expModelClass);
                            adapter.notifyDataSetChanged();
                        }
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ExpenselogPage.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("user_id", userid);
                return map;
            }
        };

        //mProgressDialog = new ProgressDialog(ExpenselogPage.this);
        //mProgressDialog.setTitle("Please Wait!");
        //mProgressDialog.setMax(100);
        //mProgressDialog.setMessage("Fetching...");
        //mProgressDialog.setProgressStyle(mProgressDialog.STYLE_SPINNER);
        //mProgressDialog.show();
        //mProgressDialog.setCancelable(false);

        RequestQueue requestQueue = Volley.newRequestQueue(ExpenselogPage.this);
        requestQueue.add(request);

        imageButton=(ImageButton) findViewById(R.id.ImageBtn);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Expense.class);
                startActivity(intent);
                finish();
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
}