package com.firstapp.logsign;


import androidx.appcompat.app.AppCompatActivity;

import androidx.cardview.widget.CardView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi=getMenuInflater();
        mi.inflate(R.menu.option_menu,menu);
        return true;
    }

    CardView expense;
    CardView reminder;
    CardView budget;
    CardView planner;
//    DrawerLayout drawerLayout;
//    Toolbar toolbar;
//    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Intent frmLogin=getIntent();
        String user_id=frmLogin.getStringExtra("user_id");

        Log.d("data_db","data=  "+user_id);

        expense=findViewById(R.id.cardviewExpense);
        reminder=findViewById(R.id.cardviewReminder);
        planner=findViewById(R.id.cardviewPlanner);
        budget=findViewById(R.id.cardviewBudget);

        expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),ExpenselogPage.class);
                intent.putExtras(frmLogin);
                startActivity(intent);
                finish();
            }
        });

        reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),reminder.class);
                startActivity(intent);
                finish();
            }
        });

        planner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Planner.class);
                startActivity(intent);
                finish();
            }
        });

        budget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Budget.class);
                startActivity(intent);
                finish();
            }
        });

//        toolbar=findViewById(R.id.toolbar);
//        drawerLayout=findViewById(R.id.drawer_lay);
//        navigationView=findViewById(R.id.nav_view);
//
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();

       // toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.home:
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        Toast.makeText(HomePage.this, "home page", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.settings:
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        Toast.makeText(HomePage.this, "settings", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.logout:
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        Toast.makeText(HomePage.this, "logout", Toast.LENGTH_SHORT).show();
//                        break;
//                }
//                return true;
//            }
//        });
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.home:
                Intent intent = new Intent(this,HomePage.class);
                startActivity(intent);
                finish();
                Toast.makeText(this,"Homepage",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.help:
                Intent intent2 = new Intent(this,help.class);
                startActivity(intent2);
                finish();
                Toast.makeText(this,"help",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.logout:
                Intent intent1 = new Intent(this,startpage.class);
                startActivity(intent1);
                finish();
                Toast.makeText(this,"logout successful",Toast.LENGTH_SHORT).show();
                return true;
            default:
                Toast.makeText(this,"Default",Toast.LENGTH_SHORT).show();
                return super.onOptionsItemSelected(item);
        }
    }
}
