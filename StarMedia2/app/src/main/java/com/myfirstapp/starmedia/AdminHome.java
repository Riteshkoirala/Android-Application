/**
 * @author Student Ritesh Koirala
 * Assignment of second term of mobile app development-1
 * student ID: 21422051
 *
 * this is the AdminHome page of the app where you can see every post the user have posted.
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
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class AdminHome extends AppCompatActivity {
    //making the variable for the navigation button
    BottomNavigationView bottomNavigationView;
    // declaring the variable for the RecycleView
    RecyclerView recyclerView;
    // arraylist
    ArrayList<String> name, post, date;
    // DataKeeper
    DataKeeper DK;
    // postAdapter
    PostAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        // new object for the DataKeeper class
        DK = new DataKeeper(this);
        // new object for the name variable
        name = new ArrayList<>();
        // new object for the post variable
        post = new ArrayList<>();
        // new object for the date variable
        date = new ArrayList<>();
        // assigning the variable in the id of the layout
        recyclerView = findViewById(R.id.recyclerview);
        // creating new object of postAdapter passing the three variable
        adapter = new PostAdapter(this, name, post, date);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // running the void method created
        displaydata();
    }
    private void displaydata()
    {
        // cursor for checking the data if its present or not in the database
        Cursor cursor = DK.getdata();
        if(cursor.getCount()==0)
        {
            // toast these message if there is no data in the database
            return;
        }
        // if the data in database remains then display these
        else
        {
            while(cursor.moveToNext())
            {
                // getting the column index from the database
                name.add(cursor.getString(1));
                post.add(cursor.getString(2));
                date.add(cursor.getString(3));
            }
        }
        // getting the variable from the previous page to this page for the further use
        String email1 = getIntent().getStringExtra("email");

        bottomNavigationView = findViewById(R.id.button);
        bottomNavigationView.setSelectedItemId(R.id.home);
        // navigation button uses
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.home){
                    // check whether i am in home page or not
                    return  true;
                }
                else if(item.getItemId()==R.id.userhome){
                    // if clicked on the user admin sends me there
                    Intent i = new Intent(AdminHome.this,AdminUser.class);
                    startActivity(i);
                    overridePendingTransition(0,0);
                    return  true;
                }
                // if i am not in the admin home page
                else{
                    return  false;
                }
            }
        });
    }
}