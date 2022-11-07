/**
 * @author Student Ritesh Koirala
 * Assignment of second term of mobile app development-1
 * student ID: 21422051
 *
 * this is the AdminUser page which keeps record of all the user presented
 */
package com.myfirstapp.starmedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class AdminUser extends AppCompatActivity {
    //making the variable for the navigation button
    BottomNavigationView bottomNavigationView;
    // declaring the variable for the RecycleView
    RecyclerView recyclerView;
    // arraylist
    ArrayList<String> username;
    // DataKeeper
    DataKeeper DK;
    // adapter
    UserAdapter adapter;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user);

        // new object for the DataKeeper class
        DK = new DataKeeper(this);
        // new object for the name variable
        username = new ArrayList<>();
        // assigning the variable in the id of the layout
        recyclerView = findViewById(R.id.recyclerview);
        // creating new object of postAdapter passing the three variable
        adapter = new UserAdapter(this, username);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // running the void method created
        displaydata();
    }
    private void displaydata()
    {
        // cursor for checking the data if its present or not in the database
        Cursor cursor = DK.getuserdata();
        if(cursor.getCount()==0)
        {
            // toast these message if there is no data in the database
            Toast.makeText(AdminUser.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        // if the data in database remains then display these
        else
        {
            while(cursor.moveToNext())
            {
                // getting the column index from the database
                username.add(cursor.getString(1));
            }
        }
        // getting the variable from the previous page to this page for the further use
        String email1 = getIntent().getStringExtra("email");
        // navigation button uses
        bottomNavigationView = findViewById(R.id.button);
        bottomNavigationView.setSelectedItemId(R.id.userhome);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // check if its on the user home page or not
                if(item.getItemId()==R.id.userhome){
                    return  true;
                }
                else if(item.getItemId()==R.id.home){
                    // send it to the next page home where you will see all the post posted
                    Intent i = new Intent(AdminUser.this,AdminHome.class);
                    startActivity(i);
                    overridePendingTransition(0,0);
                    return  true;
                }
                else{
                    return  false;
                }
            }
        });
    }
}